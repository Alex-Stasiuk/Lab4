package com.example.lab4;

import org.junit.Test;
import static org.junit.Assert.*;

public class Lab4UnitTest {
    @Test
    public void testNoteValidation() {
        // Test logic: Ensure our validation detects empty strings correctly
        String noteName = "";
        String noteContent = "Some content";

        boolean isInvalid = noteName.trim().isEmpty() || noteContent.trim().isEmpty();

        assertTrue("Validation should fail if name is empty", isInvalid);
    }
}