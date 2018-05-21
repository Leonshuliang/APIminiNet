package assignment2MiniNet;

import javax.swing.*;

import exception.NotToBeClassmatesException;
import exception.NotToBeColleaguesException;
import exception.NotToBeCoupledException;
import exception.NotToBeFriendsException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeRelationDIalog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// all components needed in this dialog
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;
	String name1, name2, ageStr1, ageStr2;
	String boxStrRelation;
	int age1, age2;
	JComboBox<String> comboBox = new JComboBox();
	Driver driver = new Driver();

	// owner is father class
	// modal is modal dialog
	public MakeRelationDIalog(Frame owner, String title, boolean modele, String name1, String name2, String ageStr1,
			String ageStr2) {
		super(owner, title, modele);// constructor of father class
		// set dialog location
		this.name1 = name1;
		this.name2 = name2;
		this.ageStr1 = ageStr1;
		this.ageStr2 = ageStr2;
		this.setLocation(300, 200);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel(); // "ID","Name","Age","Gender","Pic","Status"

		// jl1=new JLabel("ID");
		jl1 = new JLabel("Person1");
		jl2 = new JLabel("Person2");
		jl3 = new JLabel("Relation");

		// comboBox.setModel(new DefaultComboBoxModel(new String[]
		// {"Friends","Couple","Colleague","parent"}));
		comboBox.addItem("Friends");
		comboBox.addItem("Couple");
		comboBox.addItem("Colleagues");
		comboBox.addItem("Parent");
		comboBox.addItem("Classmates");
		// comboBox.setBounds(12, 11, 92, 22);

		jtf1 = new JTextField();
		jtf2 = new JTextField();

		// System.out.print(name1);
		jtf1.setText(name1);
		jtf2.setText(name2);

		jtf1.setEditable(false);
		jtf2.setEditable(false);

		jb1 = new JButton("Submit");
		// add action
		jb1.addActionListener(this);

		// set layout
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		// app JLables
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);

		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(comboBox);

		jp3.add(jb1);
		// jp3.add(jb2);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// set visible
		this.setSize(300, 250);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			age1 = Integer.parseInt(ageStr1);
			age2 = Integer.parseInt(ageStr2);
			boxStrRelation = (String) comboBox.getSelectedItem();
			if (boxStrRelation.equals("Friends")) {
				try {
					makeFriends();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}
			if (boxStrRelation.equals("Couple")) {
				try {
					makeCouple();
				} catch (NotToBeCoupledException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}
			if (boxStrRelation.equals("Colleagues")) {
				try {
					makeColleagues();
				} catch (NotToBeColleaguesException e1) {
					// TODO Auto-generated catch block

					JOptionPane.showMessageDialog(this, e1.getMessage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (boxStrRelation.equals("Parent")) {

			}
			if (boxStrRelation.equals("Classmates")) {
				try {
					makeClassmates();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}
		}
	}

	public void makeFriends() throws Exception {

		String relation = driver.checkRelation(name1, name2);
		if (age1 < 3 || age2 < 3) {
			JOptionPane.showMessageDialog(this, "Young child can not have friends");
		}

		else if ((age1 <= 16 && age2 > 16) || (age1 > 16 && age2 <= 16)) {
			throw new NotToBeFriendsException("An adult and a child can not be friends");
		} else if (age1 <= 16 && age2 <= 16 && Math.abs(age1 - age2) > 3) {
			throw new NotToBeFriendsException("Two children whose age gap can not be larger than 3");
		}

		else if (relation.equalsIgnoreCase("friends")) {
			JOptionPane.showMessageDialog(this, "They are already friends");
		} else {
			if (driver.makeRelation(name1, name2, "friends")) {
				JOptionPane.showMessageDialog(this, "They are  friends now");
			}
		}

	}

	public void makeCouple() throws NotToBeCoupledException {
		String relation = driver.checkRelation(name1, name2);
		String ifInCouple = driver.checkIfInCouple(name1, name2);
		System.out.println(ifInCouple);

		if (age1 < 18 || age2 < 18) {
			throw new NotToBeCoupledException("One person is not adult, cant to be made as couple");
		} else if (relation.equalsIgnoreCase("couple")) {
			JOptionPane.showMessageDialog(this, "They are already couple");
		} else if (ifInCouple != "Notcouple") {
			if (ifInCouple.equalsIgnoreCase("couple")) {
				// they cant have another couple relationship if they already in a couple
				// relationship
				JOptionPane.showMessageDialog(this, "They are already in another couple relation");
			}

		} else {
			// make two people as couple
			if (driver.makeRelation(name1, name2, "couple")) {
				JOptionPane.showMessageDialog(this, "They are couple now ");
			}
		}
	}

	public void makeColleagues() throws Exception {
		String relation = driver.checkRelation(name1, name2);
		// if one person is younger than 16 they can not have cplleagues
		if (age1 <= 16 || age2 <= 16) {
			throw new NotToBeColleaguesException("Too young to have colleagus");
		} else if (relation.equalsIgnoreCase("colleagues")) {
			JOptionPane.showMessageDialog(this, "They are already colleagues");
		} else {
			// make two people as colleagues
			if (driver.makeRelation(name1, name2, "Colleagues")) {
				JOptionPane.showMessageDialog(this, "They are colleagues now ");
			}
		}
	}

	// make 2 people as classmates
	public void makeClassmates() throws Exception {
		String relation = driver.checkRelation(name1, name2);
		// if one person is younger 3 they can not have classmates
		if (age1 < 3 || age2 < 3) {
			throw new NotToBeClassmatesException("Too young to have classmate");
		} else if (relation.equalsIgnoreCase("classmates")) {
			JOptionPane.showMessageDialog(this, "They are already classmates");
		} else {
			// make two people as classmates
			if (driver.makeRelation(name1, name2, "Classmates")) {
				JOptionPane.showMessageDialog(this, "They are classmates now ");
			}

		}
	}

}