package com.kieranpotts.learning.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShapesTest  {
  Shapes shapes = new Shapes();

  @Test
  void test_computeSquareArea()  {
    double actual = shapes.computeSquareArea(24);
    double expected = 576;

    assertEquals(expected, actual);
  }

  @Test
  void test_computeCircleArea()  {
    double actual = shapes.computeCircleArea(5);
    double expected = 78.5;

    assertEquals(expected, actual, "Area of circle calculation is wrong");
  }
}
