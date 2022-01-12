package com.example.projettigran.domain;


import org.junit.Assert;
import org.junit.Test;

public class PalindromesTest {

    // Test CharacterComparator
    @Test
    public void should_be_green() {
        Assert.assertEquals(ComparisonResult.GREEN, CharacterComparator.compare('a', 'a'));
    }

    @Test
    public void should_be_red() {
        Assert.assertEquals(ComparisonResult.RED, CharacterComparator.compare('a', 'b'));
    }
}