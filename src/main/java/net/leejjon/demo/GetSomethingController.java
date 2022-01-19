package net.leejjon.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
        try {
            businessLogic.doBusinessLogic();
        } catch (Exception e) {
            log.error("Something went wrong doing the business logic: " + e.getMessage(), e);
        }
        return "Hello";
    }
}
