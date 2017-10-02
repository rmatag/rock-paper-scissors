package com.rps.services;

import com.rps.types.GameMode;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ConsoleInputScannerTest {

    private ConsoleInputScanner consoleInputScanner;

    @Test
    @Parameters(method = "getOptionFromUserParams")
    public void testGetOptionFromUser(String fakeOption, int expectedOption) throws Exception {
        consoleInputScanner = new ConsoleInputScanner(new ByteArrayInputStream(fakeOption.getBytes()));
        int optionFromUser = consoleInputScanner.getOptionFromUser();
        assertThat(optionFromUser, is(expectedOption));

    }

    @Test(expected = InputMismatchException.class)
    public void testGetOptionFromUserFail() {
        consoleInputScanner = new ConsoleInputScanner(new ByteArrayInputStream("asd".getBytes()));
        consoleInputScanner.getOptionFromUser();
    }

    private Object[] getOptionFromUserParams() {
        return $(
                $("1", 1),
                $("2", 2),
                $("3", 3),
                $("4", 4)
        );
    }
}
