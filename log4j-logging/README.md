Not configuring Log4J will cause the following warning on startup:

```
log4j:WARN No appenders could be found for logger (Main).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.

```

This example uses the `BasicConfigurator` to configure logging, which doesn't require a logging 
configuration file.  See the `log4j2-and-library-with-log4j` project for an example of the
`log4j.properties` configuration file.

See http://logging.apache.org/log4j/1.2/manual.html for configuration information.