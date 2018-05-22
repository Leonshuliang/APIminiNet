package assignment2MiniNet;

/*
 * DBConnect is class using for connect database and apply method to execute all sql statement in DB
 *
 * @author Yongqi Zhong 3691039
 * @version 2.0
 * @since 20-05-2018
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hsqldb.Server;

public class DBConnect {
	static Server hsqlServer = new Server();// create a hsqlServer
	static Connection connection = null;// make connection
	static ResultSet rs = null;// result set to contain all results
	PreparedStatement ps = null;

	public void connect() {
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		// set database name and path
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertAllPeople() {
		// connect database
		connect();
		try {
			// drop table if there is this table
			connection.prepareStatement("drop table people  if exists;").execute();
			/* create table people */
			connection.prepareStatement(
					"CREATE table people ( Name varchar(200),Age varchar(200),Gender varchar(200),Pic varchar(200),Status varchar(200),State varchar(200));")
					.execute();
			// insert all data into table people
			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('Alex Smith', '21', 'M','./src/pic/ma1.jpg','student at RMIT','VIC') ;")
					.execute();
			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('Ben Turner', '35', 'M','./src/pic/ma2.jpg','manager at Coles','VIC') ;")
					.execute();
			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('Hannah White','15', 'F','./src/pic/fc1.jpg','student at PLC','VIC') ;")
					.execute();
			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('Zoe Foster', '28', 'F','./src/pic/fa1.jpg','Founder of ZFX','VIC');")
					.execute();
			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('Mark Turner', '2', 'F','./src/pic/fc2.jpg','baby','VIC') ;")
					.execute();

			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('John', '25', 'F','./src/pic/fa2.jpg','Students','VIC') ;")
					.execute();

			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('John Legend', '34', 'F','./src/pic/fa2.jpg','Manager','VIC') ;")
					.execute();
			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('Tina Dar', '29', 'F','./src/pic/fa2.jpg','IT','VIC') ;")
					.execute();
			connection.prepareStatement(
					"INSERT into people (Name, Age,Gender,Pic,Status,State) VALUES ('Mika Newman', '38', 'M','./src/pic/fa2.jpg','Government','VIC') ;")
					.execute();

			connection.prepareStatement("drop table relation  if exists;").execute();
			// create relation table
			connection
					.prepareStatement(
							"CREATE table relation ( NameF varchar(200), NameS varchar(200),Relation varchar(200));")
					.execute();

			// insert all data into relation
			connection.prepareStatement(
					"INSERT into relation (NameF,NameS,Relation) VALUES ('Alex Smith', 'Ben Turner', 'friends');")
					.execute();
			connection.prepareStatement(
					"INSERT into relation (NameF,NameS,Relation) VALUES ('Ben Turner', 'Zoe Foster', 'couple');")
					.execute();
			connection.prepareStatement(
					"INSERT into relation (NameF,NameS,Relation) VALUES ('Ben Turner', 'Mark Turner', 'parent');")
					.execute();
			connection.prepareStatement(
					"INSERT into relation (NameF,NameS,Relation) VALUES ('Mark Turner', 'Zoe Foster', 'parent');")
					.execute();
			connection.prepareStatement(
					"INSERT into relation (NameF,NameS,Relation) VALUES ('John Legend', 'Tina Dar', 'friends');")
					.execute();
			connection.prepareStatement(
					"INSERT into relation (NameF,NameS,Relation) VALUES ('Tina Dar', 'Mika Newman', 'friends');")
					.execute();

			connection.commit();
			hsqlServer.stop();
			connection.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

	// select data e.g get all people from people table
	public ResultSet connectSqlQuery(String sqlStr) {
		connect();
		try {
			rs = connection.prepareStatement(sqlStr).executeQuery();
			connection.commit();
			return rs;
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return rs;
	}

	// give sql and paras to get all data
	public ResultSet checkRelation(String sqlStr, String[] paras) {
		connect();

		try {
			ps = connection.prepareStatement(sqlStr);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();
			connection.commit();
			hsqlServer.stop();
			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;
	}

	// get person's all friends
	public ResultSet getFriends(String sqlStr, String[] paras) {

		// connect to sql database
		connect();
		try {
			ps = connection.prepareStatement(sqlStr);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();
			connection.commit();
			hsqlServer.stop();
			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;
	}

	//
	public void connectPrepareSql(String sql) {
		// connect database
		connect();
		try {
			connection.prepareStatement(sql).execute();
			connection.commit();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public boolean updateExcute(String sql, String[] paras) {
		boolean b = true;
		connect();
		try {
			ps = connection.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			if (ps.executeUpdate() != 1) {
				b = false;
			}
			connection.commit();
			hsqlServer.stop();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		hsqlServer.stop();
		return b;

	}

	// close dbsql
	public static void close() {
		// close
		try {
			if (rs != null)
				rs.close();
			if (connection != null)
				connection.close();
			hsqlServer.shutdown();
			// if(ct!=null) ct.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
