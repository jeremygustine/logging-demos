This project is intended to show the dependency tree given an included `spring-boot-starter` dependency.

`./gradlew spring-boot-with-default-config:dependencies --configuration compile`


```
compile - Dependencies for source set 'main' (deprecated, use 'implementation' instead).
\--- org.springframework.boot:spring-boot-starter -> 2.1.1.RELEASE
     +--- org.springframework.boot:spring-boot:2.1.1.RELEASE
     |    +--- org.springframework:spring-core:5.1.3.RELEASE
     |    |    \--- org.springframework:spring-jcl:5.1.3.RELEASE
     |    \--- org.springframework:spring-context:5.1.3.RELEASE
     |         +--- org.springframework:spring-aop:5.1.3.RELEASE
     |         |    +--- org.springframework:spring-beans:5.1.3.RELEASE
     |         |    |    \--- org.springframework:spring-core:5.1.3.RELEASE (*)
     |         |    \--- org.springframework:spring-core:5.1.3.RELEASE (*)
     |         +--- org.springframework:spring-beans:5.1.3.RELEASE (*)
     |         +--- org.springframework:spring-core:5.1.3.RELEASE (*)
     |         \--- org.springframework:spring-expression:5.1.3.RELEASE
     |              \--- org.springframework:spring-core:5.1.3.RELEASE (*)
     +--- org.springframework.boot:spring-boot-autoconfigure:2.1.1.RELEASE
     |    \--- org.springframework.boot:spring-boot:2.1.1.RELEASE (*)
     +--- org.springframework.boot:spring-boot-starter-logging:2.1.1.RELEASE
     |    +--- ch.qos.logback:logback-classic:1.2.3
     |    |    +--- ch.qos.logback:logback-core:1.2.3
     |    |    \--- org.slf4j:slf4j-api:1.7.25
     |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.11.1
     |    |    +--- org.slf4j:slf4j-api:1.7.25
     |    |    \--- org.apache.logging.log4j:log4j-api:2.11.1
     |    \--- org.slf4j:jul-to-slf4j:1.7.25
     |         \--- org.slf4j:slf4j-api:1.7.25
     +--- javax.annotation:javax.annotation-api:1.3.2
     +--- org.springframework:spring-core:5.1.3.RELEASE (*)
     \--- org.yaml:snakeyaml:1.23

```