package dev.antoniogrillo.primoesempiocineca.exception;

import jakarta.validation.ConstraintViolationException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Integrity constraint violation: " + ex.getMessage());
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<String> handlePropertyValueException(PropertyValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Property value exception: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String,String> m=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e->m.put(e.getField(),e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String,String> m=new HashMap<>();
        for(var c:ex.getConstraintViolations()){
            String message=c.getMessage();
            String campo=c.getPropertyPath().toString();
            if(campo.contains("."))campo=campo.substring(campo.indexOf('.')+1);
            m.put(campo,message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<Map<String,String>> handleCustomResponseException(CustomResponseException ex){
        return ResponseEntity.status(ex.getStatus()).body(Map.of("message",ex.getMessage()));
    }
}
