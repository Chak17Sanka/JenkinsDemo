package org.local.jenkins.jenkins_demo;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

/**
 * Hello world!
 *
 */
public class App 
{
	static Properties prop;
	
	static {
		
		try {
			
		prop = new Properties();
		String path = "D:\\Spring-dev\\workspace\\git-projects\\jenkins-demo\\jenkins-demo\\src\\resources\\connection.properties";
		//InputStream in = prop.getClass().getResourceAsStream(path);
		FileReader fReader = new FileReader(path);	
			prop.load(fReader);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Class.forName(prop.getProperty("Driver"));
        Connection con = DriverManager.getConnection(prop.getProperty("url")
        		                                    ,prop.getProperty("username")
        		                                    ,prop.getProperty("password"));
        
        return con;
		
		
	}
	
    public static void main( String[] args )
    {
    	try {
        System.out.println( "Hello World! Welcome to a Quick Jenkins Demo Project, This contains all the basic information required" );
        Connection con = App.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from EMPLOYEE");
        while(rs.next()) {
        	System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
        }
        con.close();
    	}
    	catch(SQLException se) {
    		se.printStackTrace();
    	}
    	
    	catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    }
}
