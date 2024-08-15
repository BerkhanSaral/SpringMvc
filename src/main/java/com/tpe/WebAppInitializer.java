package com.tpe;


//Java tabanlı Web uygulamarli web.xml dosyasi ile configure edilir
//bu class web.xml dosyasi yerine kullanilir



import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//AbstractAnnotionConfig... : DiscpatcherServlet konfigurasyonunu vermek icin gerekli class
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {//Data erişimi : Hibernate,JDBC
        return new Class[]{
                RootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//viewresolver
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override//hangi url ile gelen istekler servlet tarafından karsılanıcak onun ayarlaması
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }

}