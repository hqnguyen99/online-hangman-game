package ca.cmpt213.a4.onlinehangman.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RESTGameNotFoundException extends RuntimeException{
}
