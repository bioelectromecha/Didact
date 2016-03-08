package com.didactapp.didact;

import com.didactapp.didact.test.TestActivity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the TestActivity Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @TestActivity
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}