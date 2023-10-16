package ru.netology.proxy_nginx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ProxyNginxApplication {
    @Value("${server.port}")
    private int port;
    @GetMapping
    private String index(){
        return "Hello from port: " + port;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProxyNginxApplication.class, args);
    }

}
