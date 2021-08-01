package au.com.pathak.springbootdemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.core.ResolvableType;

@Configuration
public class Config {



  public class MyBean implements ApplicationContextAware {

    private ApplicationContext appCtx ;

    public MyBean(){
      System.out.println("appCtx = " + appCtx);
      System.out.printf("%d",System.currentTimeMillis() );
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      appCtx = applicationContext;

    }

    public String print() {
      return appCtx.getApplicationName();
    }


  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
  public MyBean foo(String bar) {

    return new MyBean();
  }

  @Bean
  public String cbar(ApplicationContext applicationContext) {
    return applicationContext.getApplicationName();
  }

  @Bean
  public MyBean myBean() {
    return new MyBean();
  }
}
