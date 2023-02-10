package hvdloc.vn.doan_t3h.config;

import hvdloc.vn.doan_t3h.config.paging.PagingResolve;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${folder.image}")
    String folderImageFile;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(new PagingResolve());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/image-product/**") // lưu ảnh
                .addResourceLocations("file:/" + folderImageFile);
    }


    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver()  {
        SessionLocaleResolver r = new SessionLocaleResolver();
        r.setDefaultLocale(new Locale("vi"));  // ngôn ngữ mặc định là tiếng việt
        r.setLocaleAttributeName("session.current.locale");
        r.setTimeZoneAttributeName("session.current.timezone");
        return r;
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageResource()  {
        ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();


        messageResource.setBasename("classpath:message"); // đường dẫn tới resources
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang"); // thay đổi ngôn ngữ


        registry.addInterceptor(localeInterceptor).addPathPatterns("/**"); //đệ qui
    }


}

