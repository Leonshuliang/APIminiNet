package Assignment2MiniNet;  
import javax.swing.*;  
  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.util.*;  
import java.awt.*;  
import java.awt.event.*;
import java.io.IOException;  
  
public class MainFrame extends JFrame implements ActionListener{  
  
    //define all lable panel and textfield
    JPanel jp1,jp2;  
    JLabel jl1;  
    JButton jb1,jb2,jb3,jb4,jb5;  
    JTable jt;  
    JScrollPane jsp;  
    JTextField jtf;  
    PeopleTable tb;  
      
    //rowData store data  
    //store all columns name in columnNames
    Vector rowData,columnNames;  
    static OptionNoRegex read = new OptionNoRegex();  
     
    
    //consturctor
    public MainFrame() throws IOException  
    {  
        jp1=new JPanel();  
        jtf=new JTextField(10);  
        jb1=new JButton("Search");  
        jb1.addActionListener(this);  
        jl1=new JLabel("Please enter name");  
          
        //put components in panel1
        jp1.add(jl1);  
        jp1.add(jtf);  
        jp1.add(jb1);  
          
        jp2=new JPanel();  
        jb2=new JButton("Add");  
        jb2.addActionListener(this);  
        jb3=new JButton("Modify");  
        jb3.addActionListener(this);  
        jb4=new JButton("Delete");  
        jb4.addActionListener(this); 
        jb5=new JButton("Make Relation");  
        jb5.addActionListener(this);  
          
        //put components in panel2
        jp2.add(jb2);  
        jp2.add(jb3);  
        jp2.add(jb4); 
        jp2.add(jb5); 
          
        //creat a instance of PeopleTable to get the table 
        tb=new PeopleTable();  
       // String []paras={"1"};  
        //sm.listPeopleBySql("select * from People");  
          tb.listPeopleByFile();
        //initialize table  
        jt=new JTable(tb);  
        jt.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
            	int rowNum=jt.getSelectedRow(); 
              if(e.getClickCount()>=2){
            	  new ShowPersonProfile(this,"Modify information",true,tb,rowNum);  
                  }
            }
          });
        //initialize scrollpane
        jsp=new JScrollPane(jt);  
          
        //put jsp in this Jframe
        this.add(jsp);  
        this.add(jp1,"North");  
        this.add(jp2,"South");  
          
        this.setSize(700, 500);  
          
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
          
          
    }  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        //specify different condition
        if(e.getSource()==jb1)  
        {  
            //System.out.println("user want to search a person");  
            
            String name=this.jtf.getText().trim();  
            //write a sql for searching   
            //String sql="select * from stu where stuName=?";  
           // String paras[]={name};
            //update all table 
            tb=new PeopleTable( );  
              
            //sm.listPeopleBySql(sql, paras);  
            //tb.listPeopleBySql(sql);  
            try {
				tb.listPByFileName(name);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            //update all table
            jt.setModel(tb);  
              
        }  
        //when clicking add button
        else if(e.getSource()==jb2)  
        {  
            PersonAddDialog sa=new PersonAddDialog(this,"Add a person",true);  
              
            //
            //
           // tb=new PeopleTable();  
//            String []paras2={"1"};  
//            //sm.listPeopleBySql("select * from stu where 1=?", paras2); 
//            tb.listPeopleBySql("select * from stu where 1=?");  
//            //更新JTable  
//            jt.setModel(tb);  
        }  
        else if(e.getSource()==jb3)  
        {  
            //modify one person's information 
            int rowNum=this.jt.getSelectedRow();  
            if(rowNum==-1)  
            {  
                //Message  
                JOptionPane.showMessageDialog(this, "Please choose one person");  
                return;  
            }  
            //Modify dialog
            new PersonUpdDialog(this,"Modify information",true,tb,rowNum);  
            
            tb=new PeopleTable();  
            String []paras2={"1"};  
           // sm.listPeopleBySql("select * from stu where 1=?", paras2);  
            tb.listPeopleBySql("select * from stu where 1=?");  
            //更新JTable  
            jt.setModel(tb);  
              
        }  
          
        else if(e.getSource()==jb4)  
        {  
            //user wants to delete one person
            //if user did not choose any person will return -1
            int rowNum=this.jt.getSelectedRow();  
            if(rowNum==-1)  
            {  
                JOptionPane.showMessageDialog(this, "Please choose one person you want to delete");  
                return;  
            }  
            //get the name of this person
            String personDelName=(String)tb.getValueAt(rowNum, 0);  
              
            //
            //String sql="delete from stu where stuid=?";  
           // String []paras={personDelName};  
            PeopleTable temp=new PeopleTable();  
           // temp.updStu(sql, paras);  
              try {
				temp.delInfield(personDelName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            //update table and all model
            tb=new PeopleTable();  
            String []paras2={"1"};  
            //sm.listPeopleBySql("select * from stu where 1=?", paras2);  
            tb.listPeopleBySql("select * from stu where 1=?");  
            //update table
            jt.setModel(tb);  
        }  
        else if(e.getSource()==jb5)  
        {  
            //user wants to delete one person
            //if user did not choose any person will return -1
            int []rowNum=this.jt.getSelectedRows(); 
            System.out.print(rowNum);
            if(rowNum.length!=2)  
            {  
                JOptionPane.showMessageDialog(this, "Please choose two People you want to make a relation");  
                return;  
            }  
//            //get the name of this person
            String personRelName1=(String)tb.getValueAt(rowNum[0], 0);  
            String personRelName2=(String)tb.getValueAt(rowNum[1], 0);  
            
            MakeRelationDIalog relationDialog = new MakeRelationDIalog(this,"Modify information",true,personRelName1,personRelName2);
 
        }  
       
          
    }  
    
  
}  