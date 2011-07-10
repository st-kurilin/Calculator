package com.calc.tokenfactory;

import com.calc.exception.CalculatorRuntimeException;
import com.calc.token.MutableToken;
import com.calc.token.Token;

import java.text.ParsePosition;
import java.util.*;

/*
* token factory that creates token by Lexeme (String)
* There is two way of using: inheritance ang  aggregation.
*/

public abstract class MapBasedTokenFactory<T extends Token> implements TokenFactory {
    protected Map<Key, T> dict;

    protected MapBasedTokenFactory() {
        dict = new HashMap<Key, T>();
    }

    public void addTokenInfo(String lexeme, T token) {
        dict.put(new Key(lexeme), token);
    }

    /*provides token from dict by lexeme from corresponding positions of source or null
    *increment position by length of lexeme
     */
    protected T getToken(String source, ParsePosition position) {
        int maxLength = 0;
        Key bestKey = null;
        for (Key key : dict.keySet()) {
            int current = key.placedAtStartOf(source, position.getIndex());
            if (current > maxLength) {
                bestKey = key;
                maxLength = current;
            }
        }
        position.setIndex(position.getIndex() + maxLength);
        return dict.get(bestKey);
    }


    public Token createToken(String source, ParsePosition position) {
        T token = getToken(source, position);
        if (token != null) {
            return extraPrepare(token);
        } else {
            return null;
        }
    }

    protected Token extraPrepare(T token) {
        return token;
    }

    public boolean addAlias(String old, String... aliases) {
        for (Key key : dict.keySet()) {
            if (key.contains(old)) {
                key.addAlias(aliases);
                return true;
            }
        }

        return false;
    }

    public static class ImmutableTokens extends MapBasedTokenFactory<Token> {
    }

    public static class MutableTokens extends MapBasedTokenFactory<MutableToken> {
        @Override
        protected Token extraPrepare(MutableToken token) {
            return clone(token);
        }

        private Token clone(MutableToken t) {
            try {
                return t.clone();
            } catch (CloneNotSupportedException e) {
                throw new CalculatorRuntimeException(e.getMessage());
            }
        }
    }


    public static class AutoSelectedTokens extends CompositeTokenFactory {
        MutableTokens mutable;
        ImmutableTokens immutable;

        {
            mutable = new MutableTokens();
            immutable = new ImmutableTokens();
            addTokenFactory(mutable);
            addTokenFactory(immutable);
        }

        public void addTokenInfo(String lexeme, Token token) {
            if (MutableToken.class.isAssignableFrom(token.getClass())) {
                mutable.addTokenInfo(lexeme, (MutableToken) token);
            } else {
                immutable.addTokenInfo(lexeme, token);
            }
        }
    }

    private static class Key {
        private Set<String> keys;

        Key(String key) {
            keys = new TreeSet<String>(new Comparator<String>() {
                //max->min (by lenght)
                public int compare(String o1, String o2) {
                    if (o1.length() == o2.length()) {
                        return o1.compareTo(o2);
                    } else {
                        return o2.length() - o1.length();
                    }
                }
            });
            keys.add(key);
        }

        void addAlias(String... aliases) {
            keys.addAll(Arrays.asList(aliases));
        }

        /*return max lexeme (by size) that finding in source*/
        int placedAtStartOf(String source, int index) {
            for (String k : keys) {
                if (source.startsWith(k, index)) {
                    return k.length();
                }
            }
            return 0;
        }

        boolean contains(String str) {
            return keys.contains(str);
        }
    }
}
