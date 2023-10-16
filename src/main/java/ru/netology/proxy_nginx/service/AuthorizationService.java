package ru.netology.proxy_nginx.service;

import org.springframework.stereotype.Service;
import ru.netology.proxy_nginx.exception.InvalidCredentials;
import ru.netology.proxy_nginx.exception.UnauthorizedUser;
import ru.netology.proxy_nginx.modelenum.Authorities;
import ru.netology.proxy_nginx.repository.UserRepository;
import java.util.List;


@Service
public class AuthorizationService {
    UserRepository userRepository;
    private AuthorizationService (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}

