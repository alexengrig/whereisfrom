# whereisfrom

Util library with [WhereIsFrom](src/main/java/dev/alexengrig/util/WhereIsFrom.java),
provides methods for getting information about a method invocation as
[StackTraceElement](https://docs.oracle.com/javase/8/docs/api/java/lang/StackTraceElement.html).

## Install

### Gradle

```groovy

implementation 'dev.alexengrig.util:whereisfrom:1.0'
```

### Maven

```xml

<dependency>
    <groupId>dev.alexengrig.util</groupId>
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

We can create an util method that can define a [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)

- using `WhereIsFrom#here()`:

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
