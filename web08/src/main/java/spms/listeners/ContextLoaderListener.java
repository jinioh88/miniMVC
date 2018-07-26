package spms.listeners;

// SqlSessionFactory 객체 준비
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spms.context.ApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  static ClassPathXmlApplicationContext applicationContext;
  
  public static ClassPathXmlApplicationContext getApplicationContext() {
    return applicationContext;
  }
   
  @Override
  public void contextInitialized(ServletContextEvent event) {
    try {
      applicationContext = new ClassPathXmlApplicationContext("beans.xml");
       
    } catch(Throwable e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void contextDestroyed(ServletContextEvent event) {}
}
