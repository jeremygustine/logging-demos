If you would like to use Log4j2 with Spring Boot 2+, you can include the `spring-boot-starter-log4j2`
dependency. You will also have to exclude the `spring-boot-starter-logging` dependency from all
of the `spring-boot-starter-*` dependencies that you are including.  There are two ways to accomplish
this:

1. Exclude it from each `starter` project manually:
```
dependencies {
    compile('org.springframework.boot:spring-boot-starter') {
        exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
    }
    compile('org.springframework.boot:spring-boot-starter-web') {
        exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
    }
    compile('org.springframework.boot:spring-boot-starter-log4j2:2.1.1.RELEASE')
}
```

2. Include a `configuration` that will automatically exclude it from all of the `starter`
dependencies:
```
dependencies {
    compile('org.springframework.boot:spring-boot-starter')
    compile('org.springframework.boot:spring-boot-starter-web')
    compile('org.springframework.boot:spring-boot-starter-log4j2:2.1.1.RELEASE')
}

configurations {
    all {
        exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
    }
}
```

Dependency tree for this project:
```
+--- org.springframework.boot:spring-boot-starter -> 2.1.1.RELEASE
|    +--- org.springframework.boot:spring-boot:2.1.1.RELEASE
|    |    +--- org.springframework:spring-core:5.1.3.RELEASE
|    |    |    \--- org.springframework:spring-jcl:5.1.3.RELEASE
|    |    \--- org.springframework:spring-context:5.1.3.RELEASE
|    |         +--- org.springframework:spring-aop:5.1.3.RELEASE
|    |         |    +--- org.springframework:spring-beans:5.1.3.RELEASE
|    |         |    |    \--- org.springframework:spring-core:5.1.3.RELEASE (*)
|    |         |    \--- org.springframework:spring-core:5.1.3.RELEASE (*)
|    |         +--- org.springframework:spring-beans:5.1.3.RELEASE (*)
|    |         +--- org.springframework:spring-core:5.1.3.RELEASE (*)
|    |         \--- org.springframework:spring-expression:5.1.3.RELEASE
|    |              \--- org.springframework:spring-core:5.1.3.RELEASE (*)
|    +--- org.springframework.boot:spring-boot-autoconfigure:2.1.1.RELEASE
|    |    \--- org.springframework.boot:spring-boot:2.1.1.RELEASE (*)
|    +--- javax.annotation:javax.annotation-api:1.3.2
|    +--- org.springframework:spring-core:5.1.3.RELEASE (*)
|    \--- org.yaml:snakeyaml:1.23
+--- org.springframework.boot:spring-boot-starter-web -> 2.1.1.RELEASE
|    +--- org.springframework.boot:spring-boot-starter:2.1.1.RELEASE (*)
|    +--- org.springframework.boot:spring-boot-starter-json:2.1.1.RELEASE
|    |    +--- org.springframework.boot:spring-boot-starter:2.1.1.RELEASE (*)
|    |    +--- org.springframework:spring-web:5.1.3.RELEASE
|    |    |    +--- org.springframework:spring-beans:5.1.3.RELEASE (*)
|    |    |    \--- org.springframework:spring-core:5.1.3.RELEASE (*)
|    |    +--- com.fasterxml.jackson.core:jackson-databind:2.9.7
|    |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.9.0
|    |    |    \--- com.fasterxml.jackson.core:jackson-core:2.9.7
|    |    +--- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.7
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.9.7
|    |    |    \--- com.fasterxml.jackson.core:jackson-databind:2.9.7 (*)
|    |    +--- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.7
|    |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.9.0
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.9.7
|    |    |    \--- com.fasterxml.jackson.core:jackson-databind:2.9.7 (*)
|    |    \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.7
|    |         +--- com.fasterxml.jackson.core:jackson-core:2.9.7
|    |         \--- com.fasterxml.jackson.core:jackson-databind:2.9.7 (*)
|    +--- org.springframework.boot:spring-boot-starter-tomcat:2.1.1.RELEASE
|    |    +--- javax.annotation:javax.annotation-api:1.3.2
|    |    +--- org.apache.tomcat.embed:tomcat-embed-core:9.0.13
|    |    +--- org.apache.tomcat.embed:tomcat-embed-el:9.0.13
|    |    \--- org.apache.tomcat.embed:tomcat-embed-websocket:9.0.13
|    |         \--- org.apache.tomcat.embed:tomcat-embed-core:9.0.13
|    +--- org.hibernate.validator:hibernate-validator:6.0.13.Final
|    |    +--- javax.validation:validation-api:2.0.1.Final
|    |    +--- org.jboss.logging:jboss-logging:3.3.2.Final
|    |    \--- com.fasterxml:classmate:1.3.4 -> 1.4.0
|    +--- org.springframework:spring-web:5.1.3.RELEASE (*)
|    \--- org.springframework:spring-webmvc:5.1.3.RELEASE
|         +--- org.springframework:spring-aop:5.1.3.RELEASE (*)
|         +--- org.springframework:spring-beans:5.1.3.RELEASE (*)
|         +--- org.springframework:spring-context:5.1.3.RELEASE (*)
|         +--- org.springframework:spring-core:5.1.3.RELEASE (*)
|         +--- org.springframework:spring-expression:5.1.3.RELEASE (*)
|         \--- org.springframework:spring-web:5.1.3.RELEASE (*)
\--- org.springframework.boot:spring-boot-starter-log4j2:2.1.1.RELEASE
     +--- org.apache.logging.log4j:log4j-slf4j-impl:2.11.1
     |    +--- org.slf4j:slf4j-api:1.7.25
     |    +--- org.apache.logging.log4j:log4j-api:2.11.1
     |    \--- org.apache.logging.log4j:log4j-core:2.11.1
     |         \--- org.apache.logging.log4j:log4j-api:2.11.1
     +--- org.apache.logging.log4j:log4j-core:2.11.1 (*)
     +--- org.apache.logging.log4j:log4j-jul:2.11.1
     |    \--- org.apache.logging.log4j:log4j-api:2.11.1
     \--- org.slf4j:jul-to-slf4j:1.7.25
          \--- org.slf4j:slf4j-api:1.7.25[

```