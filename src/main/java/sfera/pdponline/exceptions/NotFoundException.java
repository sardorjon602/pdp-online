package sfera.pdponline.exceptions;

import sfera.pdponline.payload.ApiResponse;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
