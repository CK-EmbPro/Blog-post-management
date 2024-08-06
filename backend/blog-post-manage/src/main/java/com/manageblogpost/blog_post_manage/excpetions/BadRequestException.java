package com.manageblogpost.blog_post_manage.excpetions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class BadRequestException extends Exception{
    private LocalDateTime timeStamp;
    public BadRequestException(String message){
        super(message);

        this.timeStamp = LocalDateTime.now();
    }
}
