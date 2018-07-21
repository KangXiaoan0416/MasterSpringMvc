package masterspringmvc;

import masterspringmvc.config.PicturesUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PicturesUploadProperties.class})
public class MasterspringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterspringmvcApplication.class, args);
    }
}
