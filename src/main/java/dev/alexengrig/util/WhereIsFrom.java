/*
 * Copyright 2020 - 2022 Alexengrig Dev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.alexengrig.util;

import java.util.Arrays;
import java.util.List;

/**
 * Util class, provides methods for getting information about a method invocation
 * as {@link StackTraceElement}.
 *
 * <p>
 * Example of a simple Spring Boot application:
 * <pre>{@code
 * @SpringBootApplication
 * public class Application {
 *     public static void main(String[] args) {
 *         SpringApplication.run(Application.class, args);
 *     }
 * }
 * }</pre>
 *
 * <p>
 * Usually we have to pass a {@link Class} to
 * <a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/SpringApplication.html#run-java.lang.Class-java.lang.String...-">
 * SpringApplication#run(Class<?> primarySource, String... args)
 * </a>
 * in which this method is called.
 *
 * <p>
 * We can create an util method that can define a {@link Class} - using {@link WhereIsFrom#here()}:
 * <pre>{@code
 * public class AwareSpringApplication {
 *     public static ConfigurableApplicationContext run(String... args) {
 *         try {
 *             String className = WhereIsFrom.here().getClassName();
 *             Class<?> targetClass = Class.forName(className);
 *             return SpringApplication.run(targetClass, args);
 *         } catch (ClassNotFoundException e) {
 *             throw new IllegalStateException(e);
 *         }
 *     }
 * }
 * }</pre>
 *
 * <p>
 * And use this util method:
 * <pre>{@code
 * @SpringBootApplication
 * public class AwareApplication {
 *     public static void main(String[] args) {
 *         AwareSpringApplication.run(args);
 *     }
 * }
 * }</pre>
 *
 * @author Grig Alex
 * @version 1.0
 * @implNote Takes the stack trace from an instance of {@link Throwable} - {@link Throwable#getStackTrace()}.
 * @since 1.0
 */
public final class WhereIsFrom {

    /**
     * Returns {@link StackTraceElement} of a method,
     * that calls a method - in which {@code WhereIsFrom#here} is called:
     *
     * <pre>{@code
     * void method() {
     *     WhereIsFrom.here(); // StackTraceElement of #clientMethod
     * }
     *
     * void clientMethod() {
     *     method();
     * }
     * }</pre>
     *
     * @return {@link StackTraceElement} of a calling method
     * @since 1.0
     */
    public static StackTraceElement here() {
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        return getElement(stackTrace, 2);
    }

    /**
     * Returns {@link StackTraceElement}-s of methods,
     * that call a method - in which {@code WhereIsFrom#hereAll} is called:
     *
     * <pre>{@code
     * void method() {
     *     WhereIsFrom.hereAll(); // StackTraceElement-s of #clientMethod0 and #clientMethod1, #main
     * }
     *
     * void clientMethod0() {
     *     method();
     * }
     *
     * void clientMethod1() {
     *     clientMethod0();
     * }
     * }</pre>
     *
     * @return {@link StackTraceElement}-s of calling methods
     * @since 1.0
     */
    public static List<StackTraceElement> hereAll() {
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        return Arrays.asList(getElements(stackTrace, 2));
    }

    /**
     * Returns {@link StackTraceElement} of a method is higher on the stack trace,
     * that calls a method - in which {@code WhereIsFrom#up} is called:
     *
     * <pre>{@code
     * private privateMethod() {
     *     WhereIsFrom.up(1); // StackTraceElement of #clientMethod
     * }
     *
     * void method() {
     *     privateMethod();
     * }
     *
     * void clientMethod() {
     *     method();
     * }
     * }</pre>
     *
     * @param height number of stack elements to skip
     * @return {@link StackTraceElement} of a calling method is up {@code height}
     * @apiNote Use to skip internal methods.
     * @since 1.0
     */
    public static StackTraceElement up(int height) {
        requirePositiveHeight(height);
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        return getElement(stackTrace, 2 + height);
    }

    /**
     * Returns {@link StackTraceElement}-s of methods are higher on the stack trace,
     * that call a method - in which {@code WhereIsFrom#upAll} is called:
     *
     * <pre>{@code
     * private privateMethod() {
     *     WhereIsFrom.upAll(1); // StackTraceElement of #clientMethod0 and #clientMethod1, #main
     * }
     *
     * void method() {
     *     privateMethod();
     * }
     *
     * void clientMethod0() {
     *     method();
     * }
     *
     * void clientMethod1() {
     *     clientMethod0();
     * }
     * }</pre>
     *
     * @param height number of stack elements to skip
     * @return {@link StackTraceElement}-s of calling methods are up {@code height}
     * @apiNote Use to skip internal methods.
     * @since 1.0
     */
    public static List<StackTraceElement> upAll(int height) {
        requirePositiveHeight(height);
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        return Arrays.asList(getElements(stackTrace, 2 + height));
    }

    private static StackTraceElement getElement(StackTraceElement[] stackTrace, int index) {
        requireNotEmptyStackTrace(stackTrace);
        return stackTrace[index > stackTrace.length ? stackTrace.length - 1 : index];
    }

    private static StackTraceElement[] getElements(StackTraceElement[] stackTrace, int begin) {
        requireNotEmptyStackTrace(stackTrace);
        StackTraceElement[] target = new StackTraceElement[stackTrace.length - begin];
        System.arraycopy(stackTrace, begin, target, 0, target.length);
        return target;
    }

    private static void requirePositiveHeight(int height) {
        if (height < 0) throw new IllegalArgumentException("The height must not be less than zero: " + height);
    }

    private static void requireNotEmptyStackTrace(StackTraceElement[] stackTrace) {
        if (stackTrace.length == 0) throw new IllegalStateException("The stack trace must not be empty");
    }
}
