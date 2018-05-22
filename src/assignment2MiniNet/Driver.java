package assignment2MiniNet;
/**
 * Driver includes most methods and define SQl statements for DBConnect
 * 
 * @author Shuliang Xin 3647666
 * @version 2.0
 * @since 20-05-2018
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class Driver extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	// rowData store all the data in the table
	// columnNames is the names of all columns
	Vector rowData, columnNames;
	DBConnect db = null;

	// add update and delete a person
	public boolean updatePerson(String sql, Person person) {
		//
		String[] paras = { person.getAge(), person.getGender(), person.getPic(), person.getStatus(), person.getState(),
				person.getName() };
		db = new DBConnect();
		return db.updateExcute(sql, paras);
	}

	// add a person into people table
	public boolean addPerson(String sql, Person person) {
		String[] paras = { person.getName(), person.getAge(), person.getGender(), person.getPic(), person.getStatus(),
				person.getState() };
		db = new DBConnect();
		return db.updateExcute(sql, paras);
	}

	/*
	 * before delete this person, check if he/she is in a relation\ if in a
	 * relation, can not delete this person.
	 */
	public boolean checkIfCanDel(String strDelPerson) {
		boolean delCheck = false;
		String relation = null;
		String sqlCheckRelation = "select Relation from relation where NameF = ? or NameS = ?";
		String[] paraNames = { strDelPerson, strDelPerson };
		db = new DBConnect();
		ResultSet rs = db.checkRelation(sqlCheckRelation, paraNames);

		try {
			while (rs.next()) {
				delCheck = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return delCheck;

	}

	// insert a relation to relation table
	public boolean makeRelation(String name1, String name2, String relation) {
		// sql for insert a relation
		String sql = "INSERT into relation (NameF,NameS,Relation) VALUES (?, ?, ?);";
		String[] paraNames = { name1, name2, relation };
		db = new DBConnect();
		return db.updateExcute(sql, paraNames);
	}

	// delete a person by person's name 
	public boolean delPerson(String strDelPerson) {
		// sql for deleting a person
		String sql = "Delete from people where name = ?";
		String[] paras = { strDelPerson };
		db = new DBConnect();
		return db.updateExcute(sql, paras);
	}

	// check relation between 2 people
	public String checkRelation(String name1, String name2) {
		String relation = null;
		String sqlCheckRelation = "select Relation from relation where (NameF = ? and NameS = ? )or( NameF = ? and NameS = ?)";
		String[] paras = { name1, name2, name2, name1 };
		db = new DBConnect();
		ResultSet rs = db.getFriends(sqlCheckRelation, paras);
		try {
			while (rs.next()) {
				relation = rs.getString(1);
			}
			if (relation == null) {
				relation = "No any relation";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return relation;
	}

	// get all friends
	public ArrayList getFriends(String name, String nameCheck) {
		// List allFriendslist = (List) new ArrayList();
		ArrayList listAllFriends = new ArrayList();
		//get the first person's friends
		String sqlGetFriends1 = "select NameF from relation where (NameS = ? and Relation = 'friends')";
		db = new DBConnect();
		String[] paras = { name };
		ResultSet rs1 = db.checkRelation(sqlGetFriends1, paras);
		String sqlGetFriends2 = "select NameS from relation where (NameF = ? and Relation = 'friends')";
		ResultSet rs2 = db.checkRelation(sqlGetFriends2, paras);

		try {
			// put all friends into list
			while (rs1.next()) {

				listAllFriends.add(rs1.getString(1));
			}
			while (rs2.next()) {
				listAllFriends.add(rs2.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAllFriends;

	}

	// check if two people are couple or not
	public String checkIfInCouple(String name1, String name2) {
		String relation = null;
		String sqlCheckIfInCou = "select Relation from relation where (NameF = ? or NameS = ? or NameF = ? or Names = ?) and Relation = 'couple'";
		String[] paras = { name1, name2, name2, name1 };
		db = new DBConnect();
		ResultSet rs = db.checkRelation(sqlCheckIfInCou, paras);
		try {
			while (rs.next()) {
				relation = rs.getString(1);
			}
			if (relation == null) {
				relation = "Notcouple";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return relation;
	}

	// get all people from peope text file
	public void getPeople() {
		File file = new File("./src/people.txt");
		if (file.exists()) {
			try {
				System.out.println("have file");
				listPeopleByFile();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			listPeopleBySql();
		}
	}

	// search all the people in the table
	public void listPeopleBySql() {

		String sqlStrAll = "select * from people";// SqlConnect sqlHelper =null;

		columnNames = new Vector(); // put all columns names in this vector
		// set columns name
		// columnNames.add("ID");
		columnNames.add("Name");
		columnNames.add("Age");
		columnNames.add("Gender");
		columnNames.add("Pic");
		columnNames.add("Status");
		columnNames.add("State");

		rowData = new Vector();

		try {
			db = new DBConnect();
			ResultSet rs = db.connectSqlQuery(sqlStrAll);
			// System.out.print(rs);
			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));

				// add data to rowData
				rowData.add(hang);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.close();
		}
	}

	public void listPeopleByFile() throws IOException {
		columnNames = new Vector(); // "ID","Name","Age","Gender","Pic","Status"
		// set all columns name

		columnNames.add("Name");
		columnNames.add("Age");
		columnNames.add("Gender");
		columnNames.add("Pic");
		columnNames.add("Status");
		columnNames.add("State");

		rowData = new Vector();

		FileReader reader = new FileReader("./src/people.txt");
		BufferedReader br = new BufferedReader(reader);

		String eachLine = null;
		while ((eachLine = br.readLine()) != null) {

			String[] temp = eachLine.split(",");
			Vector<String> row = new Vector<String>();
			for (int i = 0; i < temp.length; i++) {
				row.add(temp[i]);
			}
			rowData.add(row);
		}
	}

	/*
	 * search one person by name then put all people in table if the check field is
	 * empty will list all people
	 */
	public void listPByFileName(String name) throws IOException {
		String personName = name;
		columnNames = new Vector(); // "ID","Name","Age","Gender","Pic","Status"
		// set all columns name
		columnNames.add("Name");
		columnNames.add("Age");
		columnNames.add("Gender");
		columnNames.add("Status");
		columnNames.add("State");
		columnNames.add("Pic");
		// rowData is storage for people's information
		rowData = new Vector();
		// people.txt is the file for storage
		FileReader reader = new FileReader("./src/people.txt");
		BufferedReader br = new BufferedReader(reader);
		// put eachline into eachLine
		String eachLine = null;

		// each line read in file to the end, then split every line by every coma
		if (personName.isEmpty()) {
			while ((eachLine = br.readLine()) != null) {

				String[] temp = eachLine.split(",");
				Vector<String> row = new Vector<String>();
				for (int i = 0; i < temp.length; i++) {
					row.add(temp[i]);
				}
				// add each row into rowdata
				rowData.add(row);
			}
		} else {
			while ((eachLine = br.readLine()) != null) {

				String[] temp = eachLine.split(",");
				Vector<String> row = new Vector<String>();
				if (temp[0].equals(personName)) {
					for (int i = 0; i < temp.length; i++) {
						row.add(temp[i]);
					}
					rowData.add(row);
				}
			}
		}
	}

	// choose a child, then find this child's parent
	public ArrayList<String> findParents(String childName) {
		String relation = null;
		// sql to find parent
		String sqlCheckRelation = "select NameF,NameS from relation where NameF = ? or NameS = ?";
		String[] paras = { childName, childName };
		// list[] parents = null;
		ArrayList<String> parents = new ArrayList<>();
		db = new DBConnect();
		ResultSet rs = db.checkRelation(sqlCheckRelation, paras);
		try {
			while (rs.next()) {
				// put parents into parents list
				if (rs.getString(1).equals(childName)) {
					parents.add(rs.getString(2));
				}
				if (rs.getString(2).equals(childName)) {
					parents.add(rs.getString(1));
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parents;
	}

	// get how many rows
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	// get how many columns
	@Override
	public int getColumnCount() {

		return this.columnNames.size();
	}

	// get data in one specific
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(row)).get(column);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

}