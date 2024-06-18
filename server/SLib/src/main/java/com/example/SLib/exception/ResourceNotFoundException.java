package com.example.SLib.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String objName){
    super("No " + objName + " Found");
  }
}
