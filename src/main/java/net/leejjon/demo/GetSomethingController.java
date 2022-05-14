package net.leejjon.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
public class GetSomethingController {
    private final BusinessLogic businessLogic;

    @Autowired
    public GetSomethingController(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @GetMapping("/")
    public String getSomething() {
        businessLogic.doBusinessLogic();
        return "Hello";
    }

    @PostMapping("/post")
    public String postSomething(@Valid @RequestBody SomePost somePost) {
        log.info(somePost.getEmail());
        return "Posted";
    }

    @ExceptionHandler(AlreadyLoggedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedErrorsThatAreAlreadyLogged(
            AlreadyLoggedException e) {
        // Do not log
        return "Error: " + e.getUuid();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedErrors(HttpServletRequest req, Exception e) {
        final String uuid = UUID.randomUUID().toString();
        log.error(uuid + " Unexpected error occurred on request: " + req.getServletPath(), e);
        return "Error: " + uuid;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationErrors(HttpServletRequest req, MethodArgumentNotValidException e) {
        StringBuilder validationErrorMessage = new StringBuilder("Validation error: \n");
        for (ObjectError vallidationError : e.getAllErrors()) {
            validationErrorMessage.append(vallidationError.getDefaultMessage());
            validationErrorMessage.append("\n");
        }

        return validationErrorMessage.toString();
    }
}
