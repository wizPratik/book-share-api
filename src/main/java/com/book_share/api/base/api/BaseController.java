package com.book_share.api.base.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface BaseController {

    // SUCCESSFUL
    ResponseEntity<Void> OK = new ResponseEntity<>(HttpStatus.valueOf(200));
    ResponseEntity<Void> CREATED = new ResponseEntity<>(HttpStatus.valueOf(201));
    ResponseEntity<Void> ACCEPTED = new ResponseEntity<>(HttpStatus.valueOf(202));
    ResponseEntity<Void> NO_CONTENT = new ResponseEntity<>(HttpStatus.valueOf(204));
    ResponseEntity<Void> ALREADY_REPORTED = new ResponseEntity<>(HttpStatusCode.valueOf(208));

    // CLIENT ERROR
    ResponseEntity<Void> BAD_REQUEST = new ResponseEntity<>(HttpStatus.valueOf(400));
    ResponseEntity<Void> UNAUTHORIZED = new ResponseEntity<>(HttpStatus.valueOf(401));
    ResponseEntity<Void> FORBIDDEN = new ResponseEntity<>(HttpStatus.valueOf(403));
    ResponseEntity<Void> NOT_FOUND = new ResponseEntity<>(HttpStatus.valueOf(404));

    // SERVER ERROR
    ResponseEntity<Void> INTERNAL_SERVER_ERROR = new ResponseEntity<>(HttpStatus.valueOf(500));

    String JSON = MediaType.APPLICATION_JSON_VALUE;
    String MULTIPART = MediaType.MULTIPART_FORM_DATA_VALUE;

}
