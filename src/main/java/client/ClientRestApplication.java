package client;

import client.model.User;
import client.service.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ClientRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientRestApplication.class, args);
    }

}
