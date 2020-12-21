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

class SubFromIsCallerTest extends BaseTest {

    @Test
    void should_return_here_stackTraceElement_from_subClass() {
        StackTraceElement stackTraceElement = SubFromIsCaller.Sub.here();
        assertStackTrace(SubFromIsCaller.Sub.class, "here", SubFromIsCaller.class, 10, stackTraceElement);
    }

    @Test
    void should_return_hereAll_stackTraceElement_from_subClass() {
        List<StackTraceElement> stackTraceElements = SubFromIsCaller.Sub.hereAll();
        assertStackTrace(SubFromIsCaller.Sub.class, "hereAll", SubFromIsCaller.class, 30, stackTraceElements.get(0));
        String methodName = "should_return_hereAll_stackTraceElement_from_subClass";
        assertStackTrace(SubFromIsCallerTest.class, methodName, 17, stackTraceElements.get(1));
    }

    @Test
    void should_return_upOne_stackTraceElement_from_subClass() {
        StackTraceElement stackTraceElement = SubFromIsCaller.Sub.upOne();
        assertStackTrace(SubFromIsCaller.Sub.class, "upOne", SubFromIsCaller.class, 18, stackTraceElement);
    }

    @Test
    void should_return_upAllOne_stackTraceElement_from_subClass() {
        List<StackTraceElement> stackTraceElements = SubFromIsCaller.Sub.upAllOne();
        assertStackTrace(SubFromIsCaller.Sub.class, "upAllOne", SubFromIsCaller.class, 38, stackTraceElements.get(0));
        String methodName = "should_return_upAllOne_stackTraceElement_from_subClass";
        assertStackTrace(SubFromIsCallerTest.class, methodName, 31, stackTraceElements.get(1));
    }
}
