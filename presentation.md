- History

- Show all project folders. Not going to go over all of them, but you can see the basics of each framework,
as well as some more complicated examples.

- Projects
    - old-school
    - log4j-logging - show config
    - java-util-logging - no dependency required!
    - logback - show config
    - What if I pull in a library that is using another framework? What do I have to do?
        - log4j2-and-library-with-log4j
        - This works, but it requires configuring both implementations.
    - Well, what if I only want to configure one implementation?
        - You could use JCL, but don't. Instead, use SLF4J.
        - Go over the `Using SLF4J` section in the README, including bridges and bindings.
        - slf4j-noop
        - slf4j-simple
        - slf4j-logback-and-library-with-jcl - show the configuration before running!
            - I only have to configure one library (unlike the log4j2-and-library-with-log4j project). Yay!
            - Run again, this time comment out the jcl-over-slf4j lib. Notice that we don't see "logging via slf4j!!!"
    - That seems so simple! Just you wait...
        - Binding problems
            - Go over first gotcha
            - Show slf4j-simple-and-log4j-bindings
            - Check this here:
            `The way SLF4J picks a binding is determined by the JVM and for all practical purposes should be considered random. As of version 1.6.6, SLF4J will name the framework/implementation class it is actually bound to.`
            https://www.slf4j.org/codes.html#multiple_bindings
            - I think 'nondeterministic' would be a better description
        - Bridging problems
            - Go over second gotcha
            - Don't need to run it, but show build.gradle from slf4j-logback-and-library-with-jcl
            - Explain again what the bridge library does (translates to slf4j api) and that not excluding
              the commons logging library could cause those logs to be lost
            - This is hard to debug
        - Infinite loop
            - Don't go over the gotcha yet...
            - Show the build.gradle for the project (slf4j-log4j-bridge-and-binding)
            - Now that we've explained bridges and bindings, can anybody tell me what will happen and how?
            - Finally - run it!
        - Libraries can cause you pain (slf4j-simple-and-hadoop-client-library)
            - This is really a more specific example of the first gotcha
            - Make sure the custom binding `StaticLoggerBinder` class is commented out
            - Show the build.gradle. I have three dependencies, and I intend to use slf4j-simple. Nothing out of the ordinary, right?
            - Run the application. Notice that two bindings are found. How?
            - Also note that it chose the Simple binding and it DID output a log. Great!
            - Run this: `./gradlew hadoop:dependencies --configuration compile`
            - Show that the tree includes the slf4j-log4j12 binding
            - Explain that the JVM can non-deterministically choose for one of the bindings to load. (https://www.slf4j.org/codes.html#multiple_bindings)
            - Force the log4j12 binding by uncommenting the static binder code and running.  This simulates the JVM picking log4j12.
            - Now it uses Log4j. Since we did not configure it we get warnings - and we get no logs! Yuck.
            - Note that SLF4J printed the custom static binder out in the multiple bindings output.
            - Not exactly relevant, but explain why commenting out that static binder worked. 
            - Ask the class: How do we fix this? We can exclude log4j from hadoop-client and include the log4j bridge.
            - Show the 'Tips for library authors' section.
            - hadoop-client should probably not include that binding with their distribution.
            - You may want to test your lib with logging....use the tips described in 'Tips for library authors' section.
    - Spring Boot
        - We use spring boot frequently, so let's talk about how it handles logging
        - spring-boot-with-default-config
            - Show build.gradle. I have only included org.springframework.boot:spring-boot-starter
            - Run this: `./gradlew spring-boot-with-default-config:dependencies --configuration compile`
            - Show that this version (2.x) transitively includes:
                - Logback
                - slf4j api
                - log4j2 bridging library
                - java util bridging library
            - The 1.x version will bridge JUL, JCL, and Log4j1
            - Explain that if I pull in a library with commons logging, I would need to exclude the real implementation
              and then include the JCL bridge (if I'm using `spring-boot-starter` 2.x)
        - spring-boot-with-log4j2
            - If you would like to use Log4j2 with Spring Boot 2+, you can include the `spring-boot-starter-log4j2`
              dependency. You will also have to exclude the `spring-boot-starter-logging` dependency from all
              of the `spring-boot-starter-*` dependencies that you are including.  There are two ways to accomplish
              this:
                1. Exclude it from each `starter` project manually.
                2. Include a `configuration` that will automatically exclude it from all of the `starter`
                   dependencies.
            - Show lazy logging in project
    
    
   