State of logging in Java: https://stackify.com/logging-java/

log4j page: https://logging.apache.org/log4j/1.2/manual.html

Java Util Logging = JUL
Java Commons Logging = Apache Commons Logging = JCL

Log4j2 article: https://stackify.com/log4j2-java/

logback stuff: https://stackify.com/logging-logback/


=============================================================================

TODO:

Spring boot - use log4j2

Spring boot - standard setup - show mvn dependency tree

Show excluding JCL - why does that need to be done?

List dependencies that must be included when bridging various libraries

Show log4j2 benefits

Show what's bad about string concatenation, what to do w/ slf4j api

Write history or whatevs in README

Make the README a source of info on how to learn about this stuff, including
links to library docs, etc.

-SINCE 1.5.11 SLF4J software preempts the inevitable stack overflow error by throwing an exception with details about the actual cause of the problem. This is deemed to be better than leaving the user wondering about the reasons of the StackOverflowError.

talk about optional dependencies and other strategies for library writers