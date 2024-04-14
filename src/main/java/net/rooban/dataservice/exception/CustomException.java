package net.rooban.dataservice.exception;


public class CustomException extends RuntimeException {
    public CustomException(String error) {
        super(error);
    }
}
