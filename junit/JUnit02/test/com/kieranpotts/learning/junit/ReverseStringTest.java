package com.kieranpotts.learning.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReverseStringTest  {
  @Test
  public void test()  {
    ReverseString r = new ReverseString();

    String actual = r.reverseString("Java");
    String expected = "avaJ";

    assertEquals(expected, actual);
  }
}
