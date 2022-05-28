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

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseTest {

    void assertStackTrace(Class<?> type, String methodName, int line, StackTraceElement element) {
        assertStackTrace(type.getName(), methodName, type.getSimpleName() + ".java", line, element);
    }

    void assertStackTrace(Class<?> type, String methodName, Class<?> fileType, int line, StackTraceElement element) {
        assertStackTrace(type.getName(), methodName, fileType.getSimpleName() + ".java", line, element);
    }

    void assertStackTrace(String className, String methodName, String fileName, int line, StackTraceElement element) {
        assertEquals(className, element.getClassName(), "Class name is not valid");
        assertEquals(methodName, element.getMethodName(), "Method name is not valid");
        assertEquals(fileName, element.getFileName(), "File name is not valid");
        assertEquals(line, element.getLineNumber(), "Line number is not valid");
    }
}
