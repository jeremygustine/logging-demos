State of logging in Java: https://stackify.com/logging-java/

log4j page: https://logging.apache.org/log4j/1.2/manual.html

Java Util Logging = JUL
Java Commons Logging = Apache Commons Logging = JCL

Log4j2 article: https://stackify.com/log4j2-java/

logback stuff: https://stackify.com/logging-logback/


=============================================================================

TODO:

slf4j with no binding - noop implementation

multiple bindings - mention that this could happen due to transitive dependencies
  - check out the hadoop example https://www.sderosiaux.com/articles/2016/09/07/why-it-s-important-to-log-using-slf4j/

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

librarires can accidentally screw slf4j things up: https://stackoverflow.com/questions/3519978/logging-framework-incompatibility?rq=1

performance penalty

describes conflicts with bridges and implementations: https://vanwilgenburg.wordpress.com/2017/02/13/sl4j-setup/

maven enforcer plugin

binding vs bridge

This page says this:
https://www.slf4j.org/legacy.html
To use log4j-over-slf4j in your own application, the first step is to locate and then to replace log4j.jar with log4j-over-slf4j.jar. Note that you still need an SLF4J binding and its dependencies for log4j-over-slf4j to work properly.