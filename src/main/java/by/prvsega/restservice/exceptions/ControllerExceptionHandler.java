package by.prvsega.restservice.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> employeeNotFound(EmployeeNotFoundException e){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse("Employee doesn't found with this id", System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> usernameNotFound(UsernameNotFoundException e){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse("Username doesn't found", System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> IncorrectPasswordOrLogin(PasswordAndUsernameIncorrectException e){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse("Username and/or password incorrect", System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> FileIsEmpty(FileIsEmptyException e){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse("File is empty or not found", System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> employeeNotFound(MediaNotFoundException e){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse("MediaFile doesn't found with this id", System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

}
