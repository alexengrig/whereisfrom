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

package dev.alexengrig.fromis;

import org.junit.jupiter.api.Test;

import java.util.List;

class FromIsCallerTest extends BaseTest {

    @Test
    void should_return_here_stackTraceElement() {
        StackTraceElement stackTraceElement = FromIsCaller.here();
        assertStackTrace(FromIsCaller.class, "here", 8, stackTraceElement);
    }

    @Test
    void should_return_hereAll_stackTraceElement() {
        List<StackTraceElement> stackTraceElements = FromIsCaller.hereAll();
        assertStackTrace(FromIsCaller.class, "hereAll", 28, stackTraceElements.get(0));
        assertStackTrace(FromIsCallerTest.class, "should_return_hereAll_stackTraceElement", 17, stackTraceElements.get(1));
    }

    @Test
    void should_return_upOne_stackTraceElement() {
        StackTraceElement stackTraceElement = FromIsCaller.upOne();
        assertStackTrace(FromIsCaller.class, "upOne", 16, stackTraceElement);
    }

    @Test
    void should_return_upAllOne_stackTraceElement() {
        List<StackTraceElement> stackTraceElements = FromIsCaller.upAllOne();
        assertStackTrace(FromIsCaller.class, "upAllOne", 36, stackTraceElements.get(0));
        assertStackTrace(FromIsCallerTest.class, "should_return_upAllOne_stackTraceElement", 30, stackTraceElements.get(1));
    }
}