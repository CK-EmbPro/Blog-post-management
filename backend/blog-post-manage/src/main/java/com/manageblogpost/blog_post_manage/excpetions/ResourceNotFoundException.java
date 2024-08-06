package com.manageblogpost.blog_post_manage.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    private String errorCode;
    private LocalDateTime timeStamp;
    public ResourceNotFoundException(String message){
        super(message);
        this.errorCode = errorCode;
        this.timeStamp = LocalDateTime.now();
    }


}
