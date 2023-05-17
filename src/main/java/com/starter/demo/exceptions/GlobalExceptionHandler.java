package com.starter.demo.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({AccessDeniedException.class})
    public String handleAccessDenied(HttpServletRequest request, Exception ex) {
        return "accessDenied";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public String entityNotFound(HttpServletRequest request, Exception ex) {
        return "error/404";
    }


    @ExceptionHandler(RuntimeException.class)
    public String handleUnMapped(HttpServletRequest request, Model model, Exception ex) {
        String errorId = UUID.randomUUID().toString();
        model.addAttribute("errorId", errorId);
        log.error("Some exception occurred");
        log.error("error id: {}", errorId);
        log.error("Request URI {}", request.getRequestURL());
        log.error("Error: ", ex);
        return "error";
    }

}
