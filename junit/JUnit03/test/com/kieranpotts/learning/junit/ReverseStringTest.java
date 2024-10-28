package com.kieranpotts.learning.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReverseStringTest  {
  @Test
  void test()  {
    ReverseString r = new ReverseString();

    String actual = r.reverseString("Java");
    String expected = "avaJ";

    assertEquals(expected, actual);
  }
}
