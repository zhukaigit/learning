package com.zk.Util;

public class Tools {

  public static String getCurrentMethodName(StackTraceElement stackTraceElement) {
    String clazz = stackTraceElement.getClassName();
    String methodName = stackTraceElement.getMethodName();
    String result = clazz + "#" + methodName;
//    System.out.println("init method, " + result);
    return result;
  }

}
