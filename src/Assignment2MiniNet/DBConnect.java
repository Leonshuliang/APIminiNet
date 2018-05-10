package Assignment2MiniNet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hsqldb.Server;
public class DBConnect { 
	static Server hsqlServer= null;
	static Connection connection= null;
	static ResultSet rs= null;
	public void connectPrepareSql(String sql) {
//		Server hsqlServer= null;
//		Connection connection= null;
//		ResultSet rs= null;
		hsqlServer= new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();// making a connection
		try{Class.forName("org.hsqldb.jdbcDriver");
		connection= 
				DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
		connection.prepareStatement(sql).execute();
		//connection.prepareStatement("create table barcodes (id integer, barcode varchar(20) not null);").execute();
		//connection.prepareStatement("insert into barcodes (id, barcode)"+ "values (1, '12345577');").execute();
		////// query from the db
		//rs= connection.prepareStatement("select id, barcode  from barcodes;").executeQuery();
		//rs.next();
		//System.out.println(String.format("ID: %1d, Name: %1s", rs.getInt(1), rs.getString(2)));
		connection.commit();
		} catch(SQLException e2) 
		{
			e2.printStackTrace();
		} catch(ClassNotFoundException e2) 
		{
			e2.printStackTrace();
		}// end of stub code for in/out stub}}
		}
	
	public ResultSet connectSqlQuery(String sql) {
		Server hsqlServer= null;
		Connection connection= null;
		ResultSet rs= null;
		hsqlServer= new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();// making a connection
		try{Class.forName("org.hsqldb.jdbcDriver");
		connection= 
				DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
		//connection.prepareStatement("drop table barcodes if exists;").execute();
		//connection.prepareStatement("create table barcodes (id integer, barcode varchar(20) not null);").execute();
		//connection.prepareStatement("insert into barcodes (id, barcode)"+ "values (1, '12345577');").execute();
		////// query from the db
		rs= connection.prepareStatement("sql").executeQuery();
		rs.next();
		
		//System.out.println(String.format("ID: %1d, Name: %1s", rs.getInt(1), rs.getString(2)));
		connection.commit();
		return rs;
		} catch(SQLException e2) 
		{
			e2.printStackTrace();
		} catch(ClassNotFoundException e2) 
		{
			e2.printStackTrace();
		}// end of stub code for in/out stub}}
		finally{  
           close();
              
        }  
		return rs;
		}
	
	public void insertAllPeople() {
		Server hsqlServer= null;
		Connection connection= null;
		ResultSet rs= null;
		hsqlServer= new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();// making a connection
		try{Class.forName("org.hsqldb.jdbcDriver");
		connection= 
				DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
		
		connection.prepareStatement("drop table people  if exists;").execute();
		connection.prepareStatement("CREATE table people ( ID int,Name varchar(200),Age varchar(200),Gender varchar(200),Pic varchar(200),Status varchar(200));").execute();
		//connection.prepareStatement("insert into barcodes (id, barcode)"+ "values (1, '12345577');").execute();
		connection.commit();
		} catch(SQLException e2) 
		{
			e2.printStackTrace();
		} catch(ClassNotFoundException e2) 
		{
			e2.printStackTrace();
		}// end of stub code for in/out stub}}
		finally {
			close();
		}
		}
	
	 //close dbsql
    public static void close()  
    {  
        //关闭  
        try{  
            if(rs!=null) rs.close();  
            if(connection!=null) connection.close(); 
            hsqlServer.shutdown();
           // if(ct!=null) ct.close();  
              
              
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
	
	}
