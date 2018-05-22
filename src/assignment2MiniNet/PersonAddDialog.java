package assignment2MiniNet;

/**
 * Dialog for adding a person
 * 
 * @author Shuliang Xin 3647666
 * @version 2.0
 * @since 20-05-2018
 */
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
		jp3 = new JPanel();

		// set all labels name
		jl1 = new JLabel("Name");
		jl2 = new JLabel("Age");
		jl3 = new JLabel("Gender");
		jl4 = new JLabel("Status");
		jl5 = new JLabel("State");
		jl6 = new JLabel("Pic");

		// define new text fieldss

		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		jtf5 = new JTextField();
		jtf6 = new JTextField();

		// Define submit Button
		jb1 = new JButton("Submit");
		// add action
		jb1.addActionListener(this);
		// define cancel button
		jb2 = new JButton("Cancel");
		jb2.addActionListener(this);

		// set layout
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		// put all labels into panel1
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		// put all textfields into panel2
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);

		// put button1 and button 2 into panel 3
		jp3.add(jb1);
		jp3.add(jb2);

		// set layout of panel 1 2 4
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// set size and visible of this add dialog
		this.setSize(300, 250);
		this.setVisible(true);

	}

	// after click submit button, will make all data as a instance of person class
	public Person getAddData() {
		Person personAdd = new Person();
		boolean yesAdd = false;

		// check the information is empty or not picture can be empty
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
		// set value of all atributes in person instance
		personAdd.setName(str1);
		personAdd.setAge(str2);
		personAdd.setGender(str3);
		personAdd.setPic(str6);
		personAdd.setStatus(str4);
		personAdd.setState(str5);

		// get personAdd
		return personAdd;
	}

	// get action events after click buttons
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

				// regular expression of nuber
				JOptionPane.showMessageDialog(this, "pleasse input a number ");
				return;

			}

			// check the range of age if over 150 will have exception
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
				// insert sql statement
				String str = "INSERT into people (Name, Age,Gender,Pic,Status,State)" + "VALUES (?, ?,?,?,?,?);";

				// instance of driver to call addperson method
				Driver temp = new Driver();
				if (temp.addPerson(str, addPerson)) {
					JOptionPane.showMessageDialog(this, "Add a person successfully");
				}
				this.dispose();
			}

		} else if (e.getSource() == jb2) {
			// clean all the datas
			jtf1.setText("");
			jtf2.setText("");
			jtf3.setText("");
			jtf4.setText("");
			jtf5.setText("");
		}
	}

}