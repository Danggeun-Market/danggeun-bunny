package com.example.danggeunbunny.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    RESPONSE_OK(200, "Response Ok"),
    RESPONSE_CONFLICT(409, "Response Conflict"),
    RESPONSE_BAD_REQUEST(400, "Response Bad Request"),
    RESPONSE_NOT_FOUND(404, "Response Not Found"),
    RESPONSE_UNAUTHORIZED(401, "Response Unauthorized"),
    RESPONSE_FORBIDDEN(403, "Response Forbidden"),
    ;

    private final int status;
    private final String message;

}

