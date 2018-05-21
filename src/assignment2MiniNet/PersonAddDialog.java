package assignment2MiniNet;

import javax.swing.*;

import exception.NoSuchAgeException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PersonAddDialog extends JDialog implements ActionListener {

	/**
	 * after click addPerson button, personaddDialog will come up to collect person
	 * information
	 */
	private static final long serialVersionUID = 1L;
	// all components needed in this dialog
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;
	String str1, str2, str3, str4, str5, str6;

	// owner is father class
	// modal is modal dialog
	public PersonAddDialog(Frame owner, String title, boolean modele) {
		super(owner, title, modele);// constructor of father class
		// set dialog location
		this.setLocation(300, 200);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel(); // "ID","Name","Age","Gender","Pic","Status"

		// jl1=new JLabel("ID");
		jl1 = new JLabel("Name");
		jl2 = new JLabel("Age");
		jl3 = new JLabel("Gender");
		jl4 = new JLabel("Status");
		jl5 = new JLabel("State");
		jl6 = new JLabel("Pic");

		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		jtf5 = new JTextField();
		jtf6 = new JTextField();

		jb1 = new JButton("Submit");
		// add action
		jb1.addActionListener(this);
		jb2 = new JButton("Cancel");
		jb2.addActionListener(this);
		// set layout
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		// app JLables
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);

		jp3.add(jb1);
		jp3.add(jb2);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		this.setSize(300, 250);
		this.setVisible(true);

	}

	public Person getAddData() {
		Person personAdd = new Person();
		boolean yesAdd = false;

		if (jtf1.getText().equals("") || jtf2.getText().equals("") || jtf3.getText().equals("")
				|| jtf4.getText().equals("") || jtf5.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please check your information");

		} else {
			str1 = jtf1.getText();
			str2 = jtf2.getText();
			str3 = jtf3.getText();
			str4 = jtf4.getText();
			str5 = jtf5.getText();
			str6 = jtf6.getText();

		}

		personAdd.setName(str1);
		personAdd.setAge(str2);
		personAdd.setGender(str3);
		personAdd.setPic(str6);
		personAdd.setStatus(str4);
		personAdd.setState(str5);

		return personAdd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {

			Person addPerson = getAddData();
			String pattern = "^[1-9]|[1-9][0-9]|[1-9][0-9][0-9]$";

			if (jtf1.getText().equals("") || jtf2.getText().equals("") || jtf3.getText().equals("")
					|| jtf4.getText().equals("") || jtf5.getText().equals(""))

			{
				JOptionPane.showMessageDialog(this, "Please complete your information");
				return;

			}
			if (!(jtf2.getText().matches(pattern)))

			{

				JOptionPane.showMessageDialog(this, "pleasse input a number ");
				return;

			}

			if (Integer.parseInt(jtf2.getText()) > 150 || Integer.parseInt(jtf2.getText()) < 0) {
				try {
					throw new NoSuchAgeException("Your age can not be over 150 or lower than 0");
				} catch (NoSuchAgeException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
				return;
			}

			{
				String str = "INSERT into people (Name, Age,Gender,Pic,Status,State)" + "VALUES (?, ?,?,?,?,?);";

				Driver temp = new Driver();
				if (temp.addPerson(str, addPerson)) {
					JOptionPane.showMessageDialog(this, "Add a person successfully");
				}
				this.dispose();
			}

		} else if (e.getSource() == jb2) {
			jtf1.setText("");
			jtf2.setText("");
			jtf3.setText("");
			jtf4.setText("");
			jtf5.setText("");
		}
	}

}