//package org.slf4j.impl;
//
//import org.slf4j.ILoggerFactory;
//import org.slf4j.spi.LoggerFactoryBinder;

/*
Last answer here: https://stackoverflow.com/questions/11433926/force-slf4j-to-use-logback
Rad's answer here is also helpful: https://stackoverflow.com/questions/17829995/how-does-slf4j-bind-to-implementation-does-it-really-do-so-during-compile-time
Only one StaticLoggerBinder class is used. Since this is MY code, it is first in the classpath, and chosen first.
*/


/**
 * Force use of Log4j binding for logging.
 */
//@SuppressWarnings("UnusedDeclaration")
//public class StaticLoggerBinder implements LoggerFactoryBinder {
//    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
//
//    public static String REQUESTED_API_VERSION = "1.6";
//
//    public static final StaticLoggerBinder getSingleton() {
//        return SINGLETON;
//    }
//
//    private StaticLoggerBinder() {
//    }
//
//    @Override
//    public ILoggerFactory getLoggerFactory() {
//        return new Log4jLoggerFactory();
//    }
//
//    @Override
//    public String getLoggerFactoryClassStr() {
//        return "org.slf4j.impl.Log4jLoggerFactory";
//    }
//}