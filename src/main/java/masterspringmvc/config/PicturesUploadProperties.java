package masterspringmvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/** 
 * 功能描述: TODO
 * @author: 康小安 
 * @createDate: 18-11-12 下午8:01
 */
@ConfigurationProperties(prefix = "upload.pictures")
public class PicturesUploadProperties {
    private Resource uploadPath;
    private Resource anonymousPicture;

    public Resource getAnonymousPicture() {
        return anonymousPicture;
    }

    public void setAnonymousPicture(String anonymousPicture) {
        this.anonymousPicture = new DefaultResourceLoader().getResource(anonymousPicture);
    }

    public Resource getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath =  new DefaultResourceLoader().getResource(uploadPath);
    }
}
