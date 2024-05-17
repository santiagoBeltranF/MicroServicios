package co.edu.eam.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
public class autenticacionApp {
    public static void main(String[] args) {
        SpringApplication.run(autenticacionApp.class, args);
    }
}
