package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Java tabanlı web uygulamaları web.xml ile config edilir.
//bu classı web.xml yerine kullanacağız.

//AbstractAnn... classının metodlarını override ederek DispatcherServlet ı configure edip
//başlatabiliriz.
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    dispatcher:
          Servlet WebAppContext-->controller-handlermapping-viewresolver
          Root WebAppContext-->dataya erişim:repos-services
     */


    @Override
    protected Class<?>[] getRootConfigClasses() {//dataya erişim:hibernate-jdbc
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//controller-handlermapping-viewresolver(MVC)
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override//hangi url ile gelen requestler(istekler) servlet tarafından karşılanacak
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }

}
