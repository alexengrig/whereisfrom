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

class SubCallerTest extends BaseTest {

    @Test
    void should_return_here_stackTraceElement_from_subClass() throws NoSuchMethodException {
        StackTraceElement stackTraceElement = SubCaller.Sub.here();
        assertStackTrace(
                SubCaller.Sub.class,
                SubCaller.Sub.class.getMethod("here").getName(),
                SubCaller.class,
                26,
                stackTraceElement
        );
    }

    @Test
    void should_return_hereAll_stackTraceElement_from_subClass() throws NoSuchMethodException {
        List<StackTraceElement> stackTraceElements = SubCaller.Sub.hereAll();
        assertStackTrace(
                SubCaller.Sub.class,
                SubCaller.Sub.class.getMethod("hereAll").getName(),
                SubCaller.class,
                46,
                stackTraceElements.get(0)
        );
        assertStackTrace(
                SubCallerTest.class,
                SubCallerTest.class
                        .getDeclaredMethod("should_return_hereAll_stackTraceElement_from_subClass").getName(),
                39,
                stackTraceElements.get(1)
        );
    }

    @Test
    void should_return_upOne_stackTraceElement_from_subClass() {
        StackTraceElement stackTraceElement = SubCaller.Sub.upOne();
        assertStackTrace(
                SubCaller.Sub.class,
                "upOne",
                SubCaller.class,
                34,
                stackTraceElement);
    }

    @Test
    void should_return_upAllOne_stackTraceElement_from_subClass() throws NoSuchMethodException {
        List<StackTraceElement> stackTraceElements = SubCaller.Sub.upAllOne();
        assertStackTrace(
                SubCaller.Sub.class,
                SubCaller.Sub.class.getMethod("upAllOne").getName(),
                SubCaller.class,
                54,
                stackTraceElements.get(0)
        );
        assertStackTrace(
                SubCallerTest.class,
                SubCallerTest.class
                        .getDeclaredMethod("should_return_upAllOne_stackTraceElement_from_subClass").getName(),
                69,
                stackTraceElements.get(1)
        );
    }
}
