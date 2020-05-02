package com.bz.blueauth.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.bz.blueauth.exception.AccessException;
import com.bz.blueauth.exception.BadRequestException;
import com.bz.blueauth.exception.ErrorResponse;
import com.bz.blueauth.exception.GeneralException;
import com.bz.blueauth.exception.NotAcceptableException;
import com.bz.blueauth.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${server.debugMode:false}")
    private boolean isDebug;

    private String getStringStackTrace(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        System.out.println(sStackTrace);
        return sStackTrace;
    }
  
    @ExceptionHandler(Exception.class)
    public final ErrorResponse handleAllException(final Exception ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        if(isDebug){
            details.add(this.getStringStackTrace(ex));
        }        
        final ErrorResponse error = new ErrorResponse("UNCATCHED INTERNAL SERVER ERROR", details);
        return error;
    }

    @ExceptionHandler(GeneralException.class)
    public final ResponseEntity<Object> handleGeneralException(final GeneralException ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        if(isDebug){
            details.add(this.getStringStackTrace(ex));
        } 
        final ErrorResponse error = new ErrorResponse("INTERNAL SERVER ERROR", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessException.class)
    public final ResponseEntity<Object> handleAccessException(final AccessException ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        if(isDebug){
            details.add(this.getStringStackTrace(ex));
        } 
        final ErrorResponse error = new ErrorResponse("UNAUTHORIZED", details);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(final BadRequestException ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        if(isDebug){
            details.add(this.getStringStackTrace(ex));
        } 
        final ErrorResponse error = new ErrorResponse("BAD REQUEST", details);        
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public final ResponseEntity<Object> handleNotAcceptableException(final NotAcceptableException ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        if(isDebug){
            details.add(this.getStringStackTrace(ex));
        } 
        final ErrorResponse error = new ErrorResponse("NOT_ACCEPTABLE", details);
        return new ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(final NotFoundException ex,
            final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        if(isDebug){
            details.add(this.getStringStackTrace(ex));
        } 
        final ErrorResponse error = new ErrorResponse("NOT FOUND", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        for (final ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        final ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}