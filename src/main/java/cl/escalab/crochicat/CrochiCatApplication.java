package cl.escalab.crochicat;

import cl.escalab.crochicat.service.PhotoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;


@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CrochiCatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrochiCatApplication.class, args);
    }


}
