package de.featjar.base.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import de.featjar.base.tree.Trees;
import java.util.ArrayList;
import java.util.List;

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
