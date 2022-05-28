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

package dev.alexengrig.util.whereisfrom;

import dev.alexengrig.util.WhereIsFrom;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
