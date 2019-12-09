package aclaudel.codurance.atm.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExecutionContext {
    // variables
    private static Exception generatedError;

    private ExecutionContext() {}

    public static void execute(Runnable step) {
        try {
            step.run();
        } catch (Exception e) {
            generatedError = e;
        }
    }

    public static void assert_error_was_generated(Class<?> expectedError) {
        assertNotNull(generatedError, "no error was thrown");
        assertEquals(expectedError, generatedError.getClass());
    }
}
