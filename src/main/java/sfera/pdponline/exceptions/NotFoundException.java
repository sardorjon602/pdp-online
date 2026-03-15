package sfera.pdponline.exceptions;

import sfera.pdponline.payload.ApiResponse;

public class NotFoundException extends RuntimeException {
    public NotFoundException(ApiResponse message) {
        super(message.getMessage());
    }
}
