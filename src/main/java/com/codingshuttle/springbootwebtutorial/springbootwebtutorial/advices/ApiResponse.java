package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApiResponse<T> {
    @JsonFormat(pattern="hh-mm-ss dd-MM-yyyy")
    private LocalDateTime timeStamp;



    private T data;
    private APIError error;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(APIError error) {
        this();
        this.error = error;
    }
}
