package net.leejjon.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@Slf4j
@Validated
@RestController
public class GetSomethingController {

    private final BusinessLogic businessLogic;
    private final Tracer tracer;

    @Autowired
    public GetSomethingController(Tracer tracer, BusinessLogic businessLogic) {
        this.tracer = tracer;
        this.businessLogic = businessLogic;
    }

    @GetMapping("/")
    public String getSomething(@RequestParam @Valid @Size(min = 3, max = 10) String name) {
        businessLogic.doBusinessLogic();
        return "Hello: " + name;
    }

    @ExceptionHandler(AlreadyLoggedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedErrorsThatAreAlreadyLogged() {
        // Do not log
        return "Error: " + tracer.currentSpan().context().traceId();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedErrors(HttpServletRequest req, Exception e) {
        log.error("Unexpected error occurred on request: " + req.getServletPath(), e);
        return "Error: " + tracer.currentSpan().context().traceId();
    }

    // Other handlers
}

//    @PostMapping("/post")
//    public String postSomething(@Valid @RequestBody SomePost somePost) {
//        log.info(somePost.getEmail());
//        return "Posted";
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleValidationError() {
//        return "Bad request";
//    }
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleMissingRequestParameterError() {
//        return "Bad request";
//    }
//
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleValidationErrors(MethodArgumentNotValidException e) {
//        StringBuilder validationErrorMessage = new StringBuilder("Validation error: \n");
//        for (ObjectError vallidationError : e.getAllErrors()) {
//            validationErrorMessage.append(vallidationError.getDefaultMessage());
//            validationErrorMessage.append("\n");
//        }
//
//        return validationErrorMessage.toString();
//    }
//}
