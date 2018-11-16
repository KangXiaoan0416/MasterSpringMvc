package masterspringmvc.config;

import masterspringmvc.date.USLocalDateFormatter;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.UrlPathHelper;

import java.time.LocalDate;

/** 
 * 功能描述: TODO
 * 教程是继承WebMvcConfigurerAdapter spring5.0已经废弃 改用实现接口
 * @author: 康小安 
 * @createDate: 18-11-16 下午3:49
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer{

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDate.class, new USLocalDateFormatter());
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // spring mvc 默认移除url中分号后的字符,这里关掉它
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    /**
     * 这里注意因为之前这个类名称不对，所以国际化一直失败，应该是localeResolver 而我写成了localResover
     * @author 康小安
     * @createDate: 2018年11月16日 下午3:49
     * @return org.springframework.web.servlet.LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public ConfigurableServletWebServerFactory customizer() {
        //spring-boot 2.0 tomcat配置方法 和spring-boot 1.0 差别很大
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addErrorPages(new ErrorPage(MultipartException.class, "/uploadError"));
        return factory;
    }
}
