package com.tto.yaus.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTest {
    @Test
    public void whenGenerateThenReturnHashWithExpectedLength() {
        final Hash hash = new Hash(5);

        assertEquals(5, hash.generate().length());
    }
}
