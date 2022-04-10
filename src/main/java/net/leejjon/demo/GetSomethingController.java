package net.leejjon.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
}
