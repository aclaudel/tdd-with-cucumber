package aclaudel.codurance.atm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ErrorContext {
    // variables
    private Exception generatedError;

    public void execute_and_save_generated_exception(Runnable codeToExecute) {
        try {
            codeToExecute.run();
        } catch (Exception e) {
            generatedError = e;
        }
    }

    public void assert_error_was_generated(Class<?> expectedError) {
        assertNotNull(generatedError, "no error was thrown");
        assertEquals(expectedError, generatedError.getClass());
    }
}
