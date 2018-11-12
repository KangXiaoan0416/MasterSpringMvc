package masterspringmvc;

import masterspringmvc.config.PicturesUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 功能描述: TODO
 * @author: 康小安
 * @createDate: 18-11-12 下午8:01
 */
@SpringBootApplication
@EnableConfigurationProperties({PicturesUploadProperties.class})
public class MasterspringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterspringmvcApplication.class, args);
    }
}
