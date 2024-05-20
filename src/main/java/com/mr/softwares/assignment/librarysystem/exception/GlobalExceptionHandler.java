package com.mr.softwares.assignment.librarysystem.exception;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.*;
import org.springframework.http.*;
import org.springframework.validation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)

public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
Map<String, String> errors = new HashMap<>();
exception.getBindingResult().getFieldErrors().forEach(error -> {
String fieldName = error.getField();
String errorMessage = error.getDefaultMessage();
errors.put(fieldName, errorMessage);
});

return new ResponseEntity<>("success : false, errors : "+errors, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(ServiceException.class)
public ResponseEntity<Object> handleServiceException(ServiceException serviceException) {
String error;
if(serviceException.hasGenericException())
{
error=serviceException.getGenericException();
return new ResponseEntity<>("success : false, error : "+error, HttpStatus.BAD_REQUEST);
}

Map<String, String> errors = new HashMap<>();
serviceException.getProperties().forEach((property)->{
String message=serviceException.getException(property);
errors.put(property,message);
});

return new ResponseEntity<>("success : false, errors : "+errors, HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(MissingServletRequestParameterException.class)
public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception)
{
String parameterName=exception.getMethodParameter().getParameterName();
return new ResponseEntity<>("success : false, error : "+parameterName+" required", HttpStatus.BAD_REQUEST);
}


}