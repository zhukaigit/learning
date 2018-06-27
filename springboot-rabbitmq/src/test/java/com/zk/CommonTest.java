package com.zk;

import org.junit.Test;

public class CommonTest {

  @Test
  public void testGetMethodName() {
    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
    String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    System.out.println("class name: " + clazz + " Method Name " + methodName);

  }


}
