package ru.netology.proxy_nginx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.proxy_nginx.exception.InvalidCredentials;
import ru.netology.proxy_nginx.exception.UnauthorizedUser;
import ru.netology.proxy_nginx.modelenum.Authorities;
import ru.netology.proxy_nginx.service.AuthorizationService;
import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;
    AuthorizationController(AuthorizationService service) {
        this.service = service;
    }
    @GetMapping("/authorize")
    private List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }
    @ExceptionHandler(InvalidCredentials.class)
    private ResponseEntity<String> handlerInvalidCredentials(InvalidCredentials e){
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    private ResponseEntity<String> handlerUnauthorizedUser(UnauthorizedUser e){
        System.out.printf("Exception: "+ e.getMessage());
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
