package assignment2MiniNet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PersonUpdDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// define all swing components
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;
	String str1, str2, str3, str4, str5, str6;

	public PersonUpdDialog(Frame owner, String title, boolean modal, Driver sm, int rowNums) {
		super(owner, title, modal);// "ID","Name","Age","Gender","Pic","Status"

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();


		jl1 = new JLabel("Name");
		jl2 = new JLabel("Age");
		jl3 = new JLabel("Gender");
		jl4 = new JLabel("Status");
		jl5 = new JLabel("State");
		jl6 = new JLabel("Pic");

		jtf1 = new JTextField();
		jtf1.setText((String) sm.getValueAt(rowNums, 0));
		jtf1.setEditable(false);
		jtf2 = new JTextField();
		jtf2.setText((String) sm.getValueAt(rowNums, 1));
		jtf3 = new JTextField();
		jtf3.setText((String) sm.getValueAt(rowNums, 2));
		jtf4 = new JTextField();
		jtf4.setText((String) sm.getValueAt(rowNums, 4));
		jtf5 = new JTextField();
		jtf5.setText((String) sm.getValueAt(rowNums, 5));
		jtf6 = new JTextField();
		jtf6.setText((String) sm.getValueAt(rowNums, 3));

		jb1 = new JButton("Submit");
		// add listener
		jb1.addActionListener(this);
		jb2 = new JButton("Cancel");

		// set layout
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		// add component
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

		// setvisible
		this.setSize(300, 250);
		this.setVisible(true);
	}

	// update person's information
	public Person getUpdData() {
		// boolean yesUpd = false;
		Person personUpd = new Person();
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
		personUpd.setName(str1);
		personUpd.setAge(str2);
		personUpd.setGender(str3);
		personUpd.setPic(str6);
		personUpd.setStatus(str4);
		personUpd.setState(str5);

		return personUpd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Person person = getUpdData();
		if (e.getSource() == jb1) {
			String str = "update people set Age=?,Gender=?," + "Pic=?,Status=?,State=? where Name=?";
		Driver temp = new Driver();
			if (jtf1.getText().equals("") || jtf2.getText().equals("") || jtf3.getText().equals("")
					|| jtf4.getText().equals("") || jtf5.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please check your information");
				return;

			} else {
				if(temp.updatePerson(str, person))
				{
					JOptionPane.showMessageDialog(this, "Update sucessfully");
				}
			}

			this.dispose();

		}

	}

}