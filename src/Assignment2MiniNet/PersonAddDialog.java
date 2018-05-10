package Assignment2MiniNet;  
  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
public class PersonAddDialog extends JDialog implements ActionListener{  
  
    //all components needed in this dialog
    JLabel jl1,jl2,jl3,jl4,jl5,jl6;  
    JButton jb1,jb2;  
    JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;  
    JPanel jp1,jp2,jp3;  
      
    //owner is father class
    //modal is modal dialog
    public PersonAddDialog(Frame owner,String title ,boolean modele)  
    {  
        super(owner,title,modele);//constructor of father class 
        //set dialog location
        this.setLocation(300, 200);
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  //"ID","Name","Age","Gender","Pic","Status" 
          
        //jl1=new JLabel("ID");  
        jl1=new JLabel("Name");  
        jl2=new JLabel("Age");  
        jl3=new JLabel("Gender");  
        jl4=new JLabel("Pic");  
        jl5=new JLabel("Status");  
          
        jtf1=new JTextField();  
        jtf2=new JTextField();  
        jtf3=new JTextField();  
        jtf4=new JTextField();  
        jtf5=new JTextField();  
        //jtf6=new JTextField();  
          
        jb1=new JButton("Submit");  
        //add action  
        jb1.addActionListener(this);  
        jb2=new JButton("Cancel");  
        jb2.addActionListener(this); 
        //set layout
        jp1.setLayout(new GridLayout(6,1));  
        jp2.setLayout(new GridLayout(6,1));  
          
        //app JLables 
        jp1.add(jl1);  
        jp1.add(jl2);  
        jp1.add(jl3);  
        jp1.add(jl4);  
        jp1.add(jl5);  
        //jp1.add(jl6);  
          
        jp2.add(jtf1);  
        jp2.add(jtf2);  
        jp2.add(jtf3);  
        jp2.add(jtf4);  
        jp2.add(jtf5);  
        //jp2.add(jtf6);  
          
        jp3.add(jb1);  
        jp3.add(jb2);  
          
        this.add(jp1,BorderLayout.WEST);  
        this.add(jp2,BorderLayout.CENTER);  
        this.add(jp3,BorderLayout.SOUTH);  
          
       // this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //set visible
        this.setSize(300,250);  
        this.setVisible(true);
        
    }  
  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        if(e.getSource()==jb1)  
        {  
            //insert table sql 
//            PeopleTable temp=new PeopleTable();  
//            String sql="insert into stu values(?,?,?,?,?,?)";  
//            String []paras={jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText()};  
//            if(!temp.updStu(sql, paras))  
//            {  
//                //alert message
//                JOptionPane.showMessageDialog(this, "Add failed");  
//                  
//            }  
            //close dialog
        	File writename = new File("./src/people.txt"); // 
            try {
            	BufferedWriter out = null;
				writename.createNewFile();
				//out = new BufferedWriter(new FileWriter(writename));
				out = new BufferedWriter(new FileWriter(writename,true));
				out.write(jtf1.getText()+","+jtf2.getText()+","+jtf3.getText()+","+jtf4.getText()+","+jtf5.getText()+"\n");
				out.flush();
				out.close();
				this.dispose();  
				MainFrame miniNet=new MainFrame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
        }  else if(e.getSource()==jb2)  
        { 
        	jtf1.setText("");
            jtf2.setText("");
            jtf3.setText("");
            jtf4.setText("");
            jtf5.setText("");
        }
       }  
      
      
}  