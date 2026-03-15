package sfera.pdponline.exceptions;

import sfera.pdponline.payload.ApiResponse;

public class JWTException extends RuntimeException {
    public JWTException(String message) {
        super(message);
    }
}
