
Including both the binding (e.g. `slf4j-log4j12`) and bridging (e.g. `log4j-over-slf4j`)
libraries for a particular logging implementation should not be done.

```
compile (group: 'org.slf4j', name: 'log4j-over-slf4j', version: '1.5.10')
compile (group: 'org.slf4j', name: 'slf4j-log4j12', version: '1.5.10')
```

In this case, the log4j binding module will cause SLF4J to delegate calls to the log4j
implementation. The log4j bridgingm module will cause all log4j calls to be delegated
to SLF4J. This causes an infinite loop that will eventually end in a StackOverflow error.

Since version 1.5.11, SLF4J will detect the inclusion of both the binding and bridging
libraries and will preempty the infinite loop:

```
Exception in thread "main" java.lang.ExceptionInInitializerError
	at org.apache.log4j.LogManager.getLogger(LogManager.java:39)
	at org.slf4j.impl.Log4jLoggerFactory.getLogger(Log4jLoggerFactory.java:73)
	at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:249)
	at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:261)
	at Main.<clinit>(Main.java:6)
Caused by: java.lang.IllegalStateException: Detected both log4j-over-slf4j.jar AND slf4j-log4j12.jar on the class path, preempting StackOverflowError. See also http://www.slf4j.org/codes.html#log4jDelegationLoop for more details.
	at org.apache.log4j.Log4jLoggerFactory.<clinit>(Log4jLoggerFactory.java:49)
	... 5 more
```