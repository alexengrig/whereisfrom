/*
 * Copyright 2019 - 2020 Alexengrig Dev.
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

import org.junit.jupiter.api.Test;

import java.util.List;

class SimpleCallerTest extends BaseTest {

    @Test
    void should_return_here_stackTraceElement() {
        StackTraceElement stackTraceElement = SimpleCaller.here();
        assertStackTrace(SimpleCaller.class, "here", 24, stackTraceElement);
    }

    @Test
    void should_return_hereAll_stackTraceElement() throws NoSuchMethodException {
        List<StackTraceElement> stackTraceElements = SimpleCaller.hereAll();
        assertStackTrace(
                SimpleCaller.class,
                SimpleCaller.class.getMethod("hereAll").getName(),
                44,
                stackTraceElements.get(0)
        );
        assertStackTrace(
                SimpleCallerTest.class,
                SimpleCallerTest.class.getDeclaredMethod("should_return_hereAll_stackTraceElement").getName(),
                33,
                stackTraceElements.get(1)
        );
    }

    @Test
    void should_return_upOne_stackTraceElement() throws NoSuchMethodException {
        StackTraceElement stackTraceElement = SimpleCaller.upOne();
        assertStackTrace(
                SimpleCaller.class,
                SimpleCaller.class.getMethod("upOne").getName(),
                32,
                stackTraceElement
        );
    }

    @Test
    void should_return_upAllOne_stackTraceElement() throws NoSuchMethodException {
        List<StackTraceElement> stackTraceElements = SimpleCaller.upAllOne();
        assertStackTrace(
                SimpleCaller.class,
                SimpleCaller.class.getMethod("upAllOne").getName(),
                52,
                stackTraceElements.get(0)
        );
        assertStackTrace(
                SimpleCallerTest.class,
                SimpleCallerTest.class.getDeclaredMethod("should_return_upAllOne_stackTraceElement").getName(),
                61,
                stackTraceElements.get(1)
        );
    }
}