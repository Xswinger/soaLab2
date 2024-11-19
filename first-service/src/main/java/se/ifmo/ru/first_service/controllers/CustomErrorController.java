package se.ifmo.ru.first_service.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    
    @RequestMapping
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        return switch (getStatusCode(request)) {
            case 400 -> handleBadRequest(request);
            case 404 -> handleNotFound(request);
            case 405 -> handleMethodNotAllowed(request);
            case 414 -> handleURITooLong(request);
            case 422 -> handleUnprocessibleEntity(request);
            case 429 -> handleTooManyRequest(request);
            default -> handleOtherError(request);
        };
    }

    private ResponseEntity<String> handleBadRequest(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private ResponseEntity<String> handleNotFound(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private ResponseEntity<String> handleMethodNotAllowed(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    private ResponseEntity<String> handleURITooLong(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.URI_TOO_LONG).build();
    }

    private ResponseEntity<String> handleUnprocessibleEntity(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    private ResponseEntity<String> handleTooManyRequest(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    private ResponseEntity<String> handleOtherError(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private int getStatusCode(HttpServletRequest request) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        return status != null ? Integer.parseInt(status.toString()) : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
