
## Table of Contents
* [The Many Java Logging Frameworks](#frameworks) 
  - [The console](#console) 
  - [Log4j](#log4j) 
  - [Java Util Logging](#jul) 
  - [Jakarta/Apache Commons Logging](#jcl) 
  - [Slf4j](#slf4j) 
  - [Logback](#logback) 
  - [Log4j2](#log4j2) 
* [Using SLF4J](#using-slf4j) 
  - [Bindings](#bindings) 
  - [Bridges](#bridges) 
  - [Gotchas](#gotchas) 
* [Tips for library authors](#library-authors) 
* [Useful Resources](#useful-resources) 
* [Project Descriptions](#project-descriptions) 

## <a name="frameworks"></a> The Many Java Logging Frameworks

#### <a name="console"></a> The Console - System.out, System.err

This is the oldest method of logging with Java. Logs are always printed to the console, regardless of where the
application is running (e.g. development, test, production). In production, stdout is commonly redirected to /dev/null
to reduce the logging of sensitive data and unnecessary data.

Pros:
* No dependencies

Cons:
* No extra features
* It is always "on"


#### <a name="log4j"></a> Log4j

Log4j introduced the idea of "logging levels" to mitigate the "always on" nature of console logging. This allowed
different environments to choose which logs would be written. In addition, Log4j introduced "appenders", which are
responsible for outputting logs to a single destination type (e.g. files, databases, etc). Log4j's development reached
end-of-life in 2015.

Pros:
* Log levels
* Enhanced customization
* Widely used

Cons:
* No longer the most performant or flexible option

http://logging.apache.org/log4j/1.2/manual.html

#### <a name="jul"></a> Java Util Logging aka JUL

Log4j became extremely popular in the Java community. This gave rise to the development of a standard library, which
was introduced in Java 1.4 - java.util.logging. JUL never caught on due to limited out-of-the-box output functionality
(only console and file output was provided) and the fact that Log4j was superior.

Pros:
* No dependencies required

Cons:
* Poor semantics around log levels (e.g. FINER - finer than what?)
* Limited out-of-the-box output adapters
* Poor adoption

http://www.vogella.com/tutorials/Logging/article.html

#### <a name="jcl"></a> Jakara Commons Logging aka Apache Commons Logging aka JCL aka ACL

Now that Java developers are using both Log4j and JUL, application developers must configure two different logging
implementations. Commons Logging was developed to help mitigate this situation. JCL is a logging facade much like 
SLF4j. It was recommended to be used by library authors who desired logging without tying consumers of the library down
to a particular logging implementation (http://radio-weblogs.com/0122027/2003/08/15.html). JCL relies on classloading
trickery to discovery the logging framework that is being used at runtime. Unfortunately, this led to unexpected
behavior, hard to debug classloading problems, and even memory leaks (https://articles.qos.ch/thinkAgain.html). JCL
is no longer recommended to use.

Additional great information about JCL in the accepted answer here: 
https://stackoverflow.com/questions/3222895/what-is-the-issue-with-the-runtime-discovery-algorithm-of-apache-commons-logging

Pros:
* Java's first logging facade that can discovery the logging implementation at runtime

Cons:
* High complexity
* Hard to debug problems
* Memory leaks
* Just don't use it

https://commons.apache.org/proper/commons-logging/
https://commons.apache.org/proper/commons-logging/guide.html

#### <a name="slf4j"></a> Simple Logging Facade For Java aka SLF4J

SLF4j was designed to serve as a logging facade for Java without the problems of JCL. SLF4j uses static binding rather
than JCL's dynamic binding to get around many of JCL's problems (https://v4forums.wordpress.com/2008/12/27/slf4j-vs-jcl-dynamic-binding-vs-static-binding/).
SLF4j log redirection is accomplished through **bindings** and **bridges**, which are explained in detail below. SLF4j
enables you to configure a single logging implementation while your application and libraries may depend on many.

Pros:
* High adoption
* De-facto standard
* Supports parameterized logging for increased performance (See "What is the fastest way of (not) logging? https://www.slf4j.org/faq.html)

Cons:
* Redirecting logs does incur a small performance penalty

#### <a name="logback"></a> Logback

Logback is simply a native implementation of the SLF4j API. It was intended to be the successor of log4j. The
combination of SLF4j and Logback corrects one of Log4j's main problems - which is the coupling of the logging API and
implementation.

https://logback.qos.ch/
https://logback.qos.ch/reasonsToSwitch.html
https://stackify.com/logging-logback/

Pros:
* Native implementation of SLF4j API
* Better performance than Log4j

Cons:
* Not quite as performance at Log4j2

#### <a name="log4j2"></a> Log4j2

Log4j2 is the newest of the Java logging frameworks. It was designed with performance in mind and takes advantage
of modern Java language features.

Performance benchmarks: https://logging.apache.org/log4j/2.x/performance.html

Pros:
* Lazy evaluation of log statements using lambdas
* Asynchronous logging
* Fast

Cons:
* Yet another logging framework


## <a name="using-slf4j"></a> Using SLF4j

https://www.slf4j.org/manual.html

SLF4j provides a simple API that redirects to your logging implementation of choice. In addition to redirecting calls
to the SLF4j API to your choice of logging implementation, SLF4j provides libraries than can redirect logging calls from
other implementations to SLF4j. For example, a library that uses Log4j can have those calls redirected to the SLF4j
API, which are then redirected to Logback.

Example: Log4j -> redirect calls to the SLF4j API -> SLF4j -> redirect calls to Logback -> Logback

#### <a name="bindings"></a> SLF4j bindings

The binding on your classpath determines which implementation of logging framework SLF4j will redirect to. The
binding will determining the final destination of logs. A binding must be present on the classpath for SLF4j to use
it. Think of a binding as a translator - for example, if slf4j-api, slf4j-log4j12, and log4j are all on the classpath,
then calls to the slf4j API will be forwarded to the binding (slf4j-log4j12, in this case), which wraps the the log4j
api and forwards the data to log4j.

* `slf4j-log4j12` - redirects calls to Log4j. The Log4j dependency must also be present on the classpath.
* `slf4j-jdk14` - redirects calls to Java Util Logging
* `slf4j-simple` - redirects all calls to System.err
* `slf4j-jcl` - redirects calls to Jakarta Commons Logging. The JCL dependency must also be present on the classpath.
* `logback-classic` - Logback is a native implementation of the SLF4j API. No redirection occurs and no computational
  overhead is used.
* `log4j-slf4j-impl` - redirects calls to Log4j2. The Log4j2 dependencies must also be present on the classpath.

#### <a name="bridges"></a> SLF4j bridges

Check out the diagrams on: https://www.slf4j.org/legacy.html

These libraries implement the API of the logging framework that they are bridging. As such, they are intended as 
**replacements** for the other framework dependencies. Many examples on the internet fail to mention (even the
official SLF4j documentation glosses over it) that you must exclude the framework dependency that you intend to
bridge. In other words, if log4j-over-slf4j is on the classpath, then log4j should be excluded.  Likewise, if
jcl-over-slf4j is on the classpath, then jcl should be excluded.

See the following lines in the following link:
`It allows log4j users to migrate existing applications to SLF4J without changing a single line of code but simply by replacing the log4j.jar file with log4j-over-slf4j.jar, as described below.`
`To use log4j-over-slf4j in your own application, the first step is to locate and then to replace log4j.jar with log4j-over-slf4j.jar. Note that you still need an SLF4J binding and its dependencies for log4j-over-slf4j to work properly.`
https://www.slf4j.org/legacy.html

* `jcl-over-slf4j` - redirects calls from Commons Logging to SLF4j
* `jul-over-slf4j` - redirects calls from JUL to SLF4j
* `log4j-over-slf4j` - redirects calls from Log4j to SLF4j
* `log4j-to-slf4j` - redirects calls from Log4j2 to SLF4j

#### <a name="gotchas"></a> SLF4j Gotchas

* Be careful that you only include one binding on your classpath. If you include more than one, SLF4j will
  simply pick one and log the following warning: `SLF4J: Class path contains multiple SLF4J bindings.`. It may be
  possible that you are including a library that includes a binding, so be careful.
  See "first warning": https://www.sderosiaux.com/articles/2016/09/07/why-it-s-important-to-log-using-slf4j/
  
* If a bridge library and the framework that it is bridging are both on the classpath simultaneously, then there is a
  conflict. The Java classloader will resolve the conflict by simply picking one (Which one? Who knows!) of the
  implementations of the API and ignoring the other. This can lead to confusion, lost logs, difficult debugging, etc.
  
* If a bridge library and a binding library for the same framework are on the classpath simultaneously, then calls to
  SLF4j will result in an infinite loop. For example if log4j-over-slf4j and slf4j-log4j12 are both on the classpath,
  then calls to log4j will be redirected to slf4j, which will then redirect calls to log4j. Since version 1.5.11,
  slf4j-api will preempt the inevitable StackOverflowError and indicate that declared dependencies have caused the
  problem.
  
* Libraries that you depend on can cause you trouble. For example, you are using the slf4j api and logback implementation
  in your application and have configured logback to output as you require. You then include `hadoop-client` in your
  project and all of your logs disappear. What happened? The `hadoop-client` pulled in `slf4j-log4j12` transitively, 
  and then your slf4j had to choose between the two implementations from the classpath. Unfortunately, slf4j chose log4j
  which you did not configure, and therefore all of your logs are going into a blackhole. How should this be fixed? You
  should exclude `slf4j-log4j12` from the `hadoop-client` dependency. Your logs now appear, but the hadoop logs are
  still not visible. You can now include the `log4j-over-slf4j` dependency to send the hadoop logs to logback. Alternatively,
  you can make a separate configuration for log4j.
  See "second warning": https://www.sderosiaux.com/articles/2016/09/07/why-it-s-important-to-log-using-slf4j/
  
* Many of these gotchas can be prevented with the Maven enforcer plugin: https://vanwilgenburg.wordpress.com/2017/02/13/sl4j-setup/

* Excellent discussion around using Log4j2 with Spring Boot: https://stackoverflow.com/questions/41498021/is-it-worth-to-use-slf4j-with-log4j2

## <a name="library-authors"></a> Tips for library authors
Libraries should depend on slf4j so that the final application consuming those libraries can choose the implementation. 
The author of Log4j, SLF4j, and Logback makes a compelling case on StackOverflow (https://stackoverflow.com/questions/11359187/why-not-use-java-util-logging)
Even that, though, is not always 100% effective (See "Are SLF4J versions backward compatible?" https://www.slf4j.org/faq.html#compatibility).
Library authors may want to include an slf4j binding so that they can test with a logging implementation. Remove the burden
of exluding the implementation dependency by your consumer by declaring the dependency in such a way that it is not
transitively included.

With gradle, use `compileOnly`: https://blog.gradle.org/introducing-compile-only-dependencies
With maven, use `provided` or `optional`: https://maven.apache.org/guides/introduction/introduction-to-optional-and-excludes-dependencies.html

## <a name="useful-resources"></a> Useful Resources
* State of logging in Java: https://stackify.com/logging-java/
* Log4j page: https://logging.apache.org/log4j/1.2/manual.html
* Log4j2 article: https://stackify.com/log4j2-java/
* Logback stuff: https://stackify.com/logging-logback/



## <a name="project-descriptions"></a> Project Descriptions

TODO











========================TODO==================================
table of contents

project explanations

gradle alternative to maven enforcer plugin?


During demo/exercise, show the process of manually figuring out which classes to exclude, etc.
Check out the process here: https://vanwilgenburg.wordpress.com/2017/02/13/sl4j-setup/
