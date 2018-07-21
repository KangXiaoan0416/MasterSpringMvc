package masterspringmvc.profile;

import masterspringmvc.config.PicturesUploadProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@SessionAttributes("picturePath")
public class PictureUploadController {
    //上传图片保存路径改为手动配置
    //public static final Resource PICTUERS_DIR = new FileSystemResource("pictures");
    private final Resource picturesDir;
    private final Resource anonymousPicture;

    @Autowired
    public PictureUploadController(PicturesUploadProperties uploadProperties) {
        picturesDir = uploadProperties.getUploadPath();
        anonymousPicture = uploadProperties.getAnonymousPicture();
    }

    @RequestMapping("upload")
    public String uploadPage() {
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttrs, Model model) throws IOException {
        if(file.isEmpty() || !isImage(file)) {
            redirectAttrs.addFlashAttribute("error", "Incorrect file.Please upload a picture");
            return "redirect:/upload";
        }
        //copyFileToPictures(file);
        Resource picturePath = copyFileToPictures(file);
        model.addAttribute("picturePath", picturePath);
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response, @ModelAttribute("picturePath") Resource picturePath) throws IOException{
        // 1. 写死的图片路径
        //ClassPathResource classPathResource = new ClassPathResource("/images/anonymous.png");
        //response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(classPathResource.getFilename()));
        //IOUtils.copy(classPathResource.getInputStream(), response.getOutputStream());

        // 2.自动配置图片路径
        //response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(anonymousPicture.getFilename()));
        //IOUtils.copy(anonymousPicture.getInputStream(), response.getOutputStream());

        // 3.自动获取上传图片路径
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.toString()));
        Path path = Paths.get(picturePath.getURI());
        Files.copy(path, response.getOutputStream());
    }

    private Resource copyFileToPictures(MultipartFile file) throws IOException {
        // String filename = file.getOriginalFilename();
        //File tempFile = File.createTempFile("pic", getFileExtension(filename), PICTUERS_DIR.getFile());
        String fileExtension = getFileExtension(file.getOriginalFilename());
        File tempFile = File.createTempFile("pic", fileExtension, picturesDir.getFile());
        try (  InputStream in =  file.getInputStream(); OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return new FileSystemResource(tempFile);
    }

    @ModelAttribute("picturePath")
    public Resource picturePath() {
        return anonymousPicture;
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIoException(IOException exception) {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error", exception.getMessage());
        return modelAndView;
    }

    @RequestMapping("uploadError")
    public ModelAndView onUploadError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("uploadPage");
        modelAndView.addObject("error", request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE));
        return modelAndView;
    }
}
