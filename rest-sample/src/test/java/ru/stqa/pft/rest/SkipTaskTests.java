package ru.stqa.pft.rest;

import org.testng.annotations.Test;

public class SkipTaskTests extends TestBase{
    @Test
    public void testSkip() {
        int blockedIssueId = 1;
        skipIfNotFixed(blockedIssueId);

    }

}
