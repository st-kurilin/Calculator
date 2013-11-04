Calculator
==========
This program is simple illustration of implementing math expression evaluator in OO way.

BUILD
To build you need JDK and maven be installed in your system.  To build jar run "mvn package".  Artifact will be putted into ...calculator/target/calculator-1.0-SNAPSHOT-jar-with-dependencies.jar file.
jdk - http://www.oracle.com/technetwork/java/javase/downloads/index.html
maven - http://maven.apache.org/

RUN
To run program JRE needed to be installed. To run GUI type 
  java -jar calculator-1.0-SNAPSHOT-jar-with-dependencies.jar gui
To run program in console interactive mode run  
  java -jar calculator-1.0-SNAPSHOT-jar-with-dependencies.jar console
jre - http://www.oracle.com/technetwork/java/javase/downloads/index.html

FEATURES
Calculator supports 
* arithmetic expressions, like -2 + 4.5 * (2 + 3) 
* factorial, like 2!
* power, like 2 ^ 3 ^ 4
* increment and decrement, like (2 + 3)++
* basic functions, like sqrt( max( 4, 9, 6))
Only with gui currently (could be easily implemented for console too):
* creating aliases defining s = sqrt, and then s(9)
* adding more functionality on runtime (in next session)
See tests for details https://github.com/st-kurilin/Calculator/tree/master/src/test/java/com/github/stkurilin/calculator

ADDING FUNCTIONALITY ON RUNTIME
You can add more function by providing additional classes. To do this you need
* create your own class
* implement com.github.stkurilin.calculator.core.token.Token interface
* compile your class to get .class file
* load it after program started
Be caryful: this is experimenting feature with very naive impl. Also now we have several limitations on classes provided by user 
* should be placed in on single .class file. (No inner classes are supported)
* should be placed in default package
You can see details in ExtraTokenTests (https://github.com/st-kurilin/Calculator/blob/master/src/test/java/com/github/stkurilin/calculator/ExtraTokenTests.java)

THINKS TO DO
* add missing functionality to console mode
* separate console and interactive console mods
* be better on adding functionality on runtime

CONTACTS
Please fill free to contact me on any question using skype st.kurilin, email st.kurilin@gmail.com or linkedin id=113714774
