package com.kieranpotts.learning.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalcTest  {
  @Test
  public void test() {
    Calc calc = new Calc();

    int actual = calc.divide(10, 5);
    int expected = 2;

    assertEquals(expected, actual);
  }
}
