package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;

import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			InitialContext initCOntext = new InitialContext();
			DataSource ds = (DataSource)initCOntext.lookup("java:comop/env/jdbc/studydb");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds); 

			sc.setAttribute("memberDao", memberDao);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
}
