/*
 * Copyright (C) 2025 FeatJAR-Development-Team
 *
 * This file is part of FeatJAR-base.
 *
 * base is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 *
 * base is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with base. If not, see <https://www.gnu.org/licenses/>.
 *
 * See <https://github.com/FeatureIDE/FeatJAR-base> for further information.
 */
package de.featjar.base.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ProblemTest {

    @Test
    public void getFirstExceptionTest() {
        List<Problem> problems = new ArrayList<>();
        Object object = new Object();

        // First block:
        // Tests whether getFirstException works if there are no prior exceptions.
        {
            Result<Object> wrappedObject = Result.ofNullable(object, problems);

            assertTrue(wrappedObject.getProblems().isEmpty());

            Result<Object> wrappedObject2 = Result.ofNullable(null, problems);

            assertThrows(RuntimeException.class, () -> {
                wrappedObject2.orElseThrow(Problem::getFirstException);
            });
        }

        // Create a new specific exception and add them to the problems.
        RuntimeException testException = new RuntimeException("Error");
        problems.add(0, new Problem(testException));

        // Second block:
        // Tests whether getFirstException works if there already is an exception in
        // the Result objects problem list. Check if the thrown exception has the added
        // testException as a cause.
        {
            Result<Object> wrappedObject = Result.ofNullable(object, problems);

            assertFalse(wrappedObject.getProblems().isEmpty());

            Result<Object> wrappedObject2 = Result.ofNullable(null, problems);

            RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
                wrappedObject2.orElseThrow(Problem::getFirstException);
            });

            assertEquals(testException, thrown.getCause());
        }
    }
}
