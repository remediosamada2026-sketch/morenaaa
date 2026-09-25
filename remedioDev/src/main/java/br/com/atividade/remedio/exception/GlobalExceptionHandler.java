package br.com.atividade.remedio.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        Map<String, Object> err = new HashMap<>();
        err.put("timestamp", Instant.now());
        err.put("status", HttpStatus.NOT_FOUND.value());
        err.put("error", "Recurso não encontrado");
        err.put("message", ex.getMessage());
        err.put("path", req.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    
}

