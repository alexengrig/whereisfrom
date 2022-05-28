# whereisfrom

[![Maven Central](https://img.shields.io/maven-central/v/dev.alexengrig/whereisfrom.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22dev.alexengrig%22%20AND%20a:%22whereisfrom%22)
[![Javadocs](https://www.javadoc.io/badge/dev.alexengrig/whereisfrom.svg)](https://www.javadoc.io/doc/dev.alexengrig/whereisfrom)
[![GitHub](https://img.shields.io/github/license/alexengrig/whereisfrom?style=flat&&color=informational)](LICENSE)

Util library with [WhereIsFrom](src/main/java/dev/alexengrig/util/WhereIsFrom.java),
provides methods for getting information about a method invocation as
[StackTraceElement](https://docs.oracle.com/javase/8/docs/api/java/lang/StackTraceElement.html).

## Install

### Gradle

```groovy

implementation 'dev.alexengrig:whereisfrom:1.0'
```

### Maven

```xml

<dependency>
    <groupId>dev.alexengrig</groupId>
    <artifactId>whereisfrom</artifactId>
    <version>1.0</version>
</dependency>
```

## Use case

Example of a simple Spring Boot application:

```java

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Usually we have to pass a [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html) to
<a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/SpringApplication.html#run-java.lang.Class-java.lang.String...-">
SpringApplication#run(Class<?> primarySource, String... args)
</a>
in which this method is called.

We can create an util method that can define
a [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html) - using `WhereIsFrom#here()`:

```java
public class AwareSpringApplication {
    public static ConfigurableApplicationContext run(String... args) {
        try {
            String className = WhereIsFrom.here().getClassName();
            Class<?> targetClass = Class.forName(className);
            return SpringApplication.run(targetClass, args);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
```

And use this util method:

```java

@SpringBootApplication
public class AwareApplication {
    public static void main(String[] args) {
        AwareSpringApplication.run(args);
    }
}
```

Full code in [demo project](/demo).

## License

This project is [licensed](LICENSE) under [Apache License, version 2.0](https://www.apache.org/licenses/LICENSE-2.0).