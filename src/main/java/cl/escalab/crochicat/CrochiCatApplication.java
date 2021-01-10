package cl.escalab.crochicat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class CrochiCatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrochiCatApplication.class, args);
    }

}
