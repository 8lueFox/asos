package com.io.usos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such object")
public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(){
        super(String.format("obiekt nie istnieje"));
    }

    public ObjectNotFoundException(String object, int id){
        super(String.format(object+" o id %d nie istnieje", id));
    }
}