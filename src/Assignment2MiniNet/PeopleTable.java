package Assignment2MiniNet;  
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.util.Vector;  
  
import javax.swing.table.*;  
  
public class PeopleTable extends AbstractTableModel{  

	private static final long serialVersionUID = 1L;


		//rowData store all the data in the table 
        //columnNames is the names of all columns
        Vector rowData,columnNames;  
          
                  
        // 
//        PreparedStatement ps=null;  
//        Connection ct=null;  
//        ResultSet rs=null;  
//        String url="jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=acm";  
//        String user="sa";  
//        String passwd="sa";  
//        String driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";  
          
        //add  update and delete a person 
        public boolean updStu(String sql,String []paras)  
        {  
            //make a sql helper 
            SqlConnect sqlHelper = new SqlConnect();  
            return sqlHelper.updExecute(sql, paras);  
        }  
          
        //search all the people in the table
        public void listPeopleBySql(String sql)  
        {  
            //SqlConnect sqlHelper =null;  
              DBConnect db = null;
              
            columnNames=new Vector();  //"ID","Name","Age","Gender","Pic","Status" 
            //set columns name
            columnNames.add("ID");  
            columnNames.add("Name");  
            columnNames.add("Age");  
            columnNames.add("Gender");  
            columnNames.add("Pic");  
            columnNames.add("Status");  
              
            rowData=new Vector();  
              
            try{  
            	db = new DBConnect();  
                ResultSet rs=db.connectSqlQuery(sql);  
                  
                while(rs.next())  
                {  
                    Vector hang=new Vector();  
                    hang.add(rs.getString(1));  
                    hang.add(rs.getString(2));  
                    hang.add(rs.getString(3));  
                    hang.add(rs.getInt(4));  
                    hang.add(rs.getString(5));  
                    hang.add(rs.getString(6));  
                      
                    //add data to rowData
                    rowData.add(hang);  
                }  
                  
            }catch(Exception e){  
                e.printStackTrace();  
            }finally{  
            	DBConnect.close();  
            }  
        }  
          
          
        public void listPeopleByFile() throws IOException  
        {  
            columnNames=new Vector();  //"ID","Name","Age","Gender","Pic","Status" 
            //set all columns name  
            //columnNames.add("ID");  
            columnNames.add("Name");  
            columnNames.add("Age");  
            columnNames.add("Gender");
            columnNames.add("Status");
            columnNames.add("State");
            columnNames.add("Pic");  
              
            rowData=new Vector();  
              
            FileReader reader = new FileReader("./src/people.txt");
            BufferedReader br = new BufferedReader(reader);

            String eachLine = null;//each line read in file
            while((eachLine = br.readLine()) != null){//read file to the end
            //-----split(String):split all strings from file.
            String[] temp = eachLine.split(",");//split every line every comma
            Vector<String> row = new Vector<String>();
            for(int i = 0; i < temp.length; i++){//traverse all lines 
            row.add(temp[i]);//put every line in row
            }
            rowData.add(row);//put all data in row into rowData
            }
        }  
          
        public void listPByFileName(String name) throws IOException  
        {  
        	String personName = name;
            columnNames=new Vector();  //"ID","Name","Age","Gender","Pic","Status" 
            //set all columns name  
            //columnNames.add("ID");  
            columnNames.add("Name");  
            columnNames.add("Age");  
            columnNames.add("Gender");
            columnNames.add("Status");
            columnNames.add("State");
            columnNames.add("Pic");  
              
            rowData=new Vector();  
              
            FileReader reader = new FileReader("./src/people.txt");
            BufferedReader br = new BufferedReader(reader);

            String eachLine = null;//each line read in file
            while((eachLine = br.readLine()) != null){//read file to the end
            //-----split(String):split all strings from file.
            String[] temp = eachLine.split(",");//split every line every comma
            Vector<String> row = new Vector<String>();
           // System.out.print(temp[0]);
            if(temp[0].equals(personName) ) 
            {
            for(int i = 0; i < temp.length; i++)
            {//traverse all lines 
              row.add(temp[i]);//put every line in row
            }
            System.out.print(rowData.size());
            
            rowData.add(row);
            }
            //put all data in row into rowData
            }
        }  
      
        public void delInfield(String delName) throws IOException {
            String name =delName;
            File file = new File("./src/people.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String tempStr;
            while((tempStr=br.readLine())!=null){
//                if(temp.trim().equals(remLine)){
//                    System.out.println("");
//                    continue;
//                }
            	 String[] temp = tempStr.split(",");//split every line every comma
                // Vector<String> row = new Vector<String>();
                 for(int i = 0; i < temp.length; i++)
                 {//traverse all lines 
                 //row.add(temp[i]);//put every line in row
                	 if(temp[0].equals(tempStr))
                	 {
                		 continue;
                	 }
                 }
            	
                sb.append(tempStr+"\n");
            }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.close();
            System.out.println("删除 完成  OK!");
        }
     
    //get how many rows
    public int getRowCount() {  
        // TODO Auto-generated method stub  
        return this.rowData.size();  
    }  
  
    //get how many columns
    @Override
    public int getColumnCount() {  
        
    	return this.columnNames.size();  
    }  
  
    //get data in one specific
    
    public Object getValueAt(int row, int column) {  
        // TODO Auto-generated method stub  
        return ((Vector)this.rowData.get(row)).get(column);  
    }  
  
  
  
    @Override  
    public String getColumnName(int column) {  
        // TODO Auto-generated method stub  
        return (String)this.columnNames.get(column);  
    }

	
	
}  