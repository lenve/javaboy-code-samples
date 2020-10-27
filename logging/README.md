Java 里边的日志两大类：

- 门面（Apache Commons Logging，Slf4j）
- 实现（Java Util Logging、Log4j）

日志级别：

- ERROR
- WARN
- INFO
- DEBUG
- TRACE

Spring Boot 使用 Apache Commons Logging 作为内部日志框架，默认的日志实现是 Java Util Logging。