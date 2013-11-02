package com.github.stkurilin.calculator.core.token;

import com.github.stkurilin.calculator.core.exception.CalculatorException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class RuntimeLoadTokenFactory extends MapBasedTokenFactory.AutoSelectedTokens {
    public void addTokenInfo(String lexeme, String uri) throws CalculatorException {
        try {
            addTokenInfo(lexeme, createInstance(uri));
        } catch (Exception e) {
            throw new CalculatorException(e);
        }
    }

    private Token createInstance(String path)
            throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = provideClassLoader(parseDirectoryName(path));
        Class<?> clazz = loader.loadClass(parseFileName(path));
        return Token.class.cast(clazz.newInstance());
    }

    private ClassLoader provideClassLoader(String dirPath) throws MalformedURLException {
        return new URLClassLoader(new URL[]{new File(dirPath).toURI().toURL()});
    }

    private String parseFileName(String path) {
        int start = path.lastIndexOf(File.separator) + 1;
        int end = path.length() - ".class".length();
        return path.substring(start, end);
    }

    private String parseDirectoryName(String path) {
        return path.substring(0, path.lastIndexOf(File.separator) + 1);
    }
}
