package com.lvr.Dhakiya_backend.restadvice.exceptions;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
