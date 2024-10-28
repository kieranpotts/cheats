package com.kieranpotts.learning.junit;

public class Shapes  {
  public double computeSquareArea(double length) {
    double result = length * length;

    return result;
  }

  public double computeCircleArea(double radius) {
    double result = 3.14 * radius * radius;

    return result;
  }
}
