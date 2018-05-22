package assignment2MiniNet;

/**
 * 
 * MainFram is going to create main container to contain all components
 * 
 * @author Shuliang Xin 3647666
 * @version 2.0
 * @since 20-05-2018
 */
import javax.swing.*;
import exception.NoParentException;
import java.util.*;
import java.awt.event.*;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener {

	// define all lable panel and textfield
	JPanel jp1, jp2;
	JLabel jl1;
	JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	Driver tb;

	// rowData store data
	// store all columns name in columnNames
	Vector rowData, columnNames;
	static Helper read = new Helper();

	// consturctor
	public MainFrame() throws IOException {
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("Search");
		jb1.addActionListener(this);
		jl1 = new JLabel("Please enter name");

		// put components in panel1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jp2 = new JPanel();
		jb2 = new JButton("AddPerson");
		jb2.addActionListener(this);
		jb3 = new JButton("Modify");
		jb3.addActionListener(this);
		jb4 = new JButton("Delete");
		jb4.addActionListener(this);
		jb5 = new JButton("Make Relation");
		jb5.addActionListener(this);
		jb6 = new JButton("Check relation");
		jb6.addActionListener(this);
		jb7 = new JButton("connection chain");
		jb7.addActionListener(this);
		jb8 = new JButton("EXIT");
		jb8.addActionListener(this);
		jb9 = new JButton("FindParent");
		jb9.addActionListener(this);

		// put components in panel2
		jp1.add(jb8);
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
		jp2.add(jb6);
		jp2.add(jb7);
		jp2.add(jb9);

		// creat a instance of Driver to get the table
		tb = new Driver();
		tb.getPeople();
		// jt is instance of driver
		jt = new JTable(tb);
		// if double click table profile page will show up
		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// get row selected
				int rowNum = jt.getSelectedRow();
				if (e.getClickCount() >= 2) {
					new ShowPersonProfile(this, "Modify information", true, tb, rowNum);
				}
			}
		});
		// initialize scrollpane
		jsp = new JScrollPane(jt);

		// put jsp in this Jframe
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");

		// set size
		this.setSize(900, 500);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// specify different condition
		if (e.getSource() == jb1) {
			// System.out.println("user want to search a person");

			String name = this.jtf.getText().trim();
			tb = new Driver();

			try {
				tb.listPByFileName(name);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// update all table
			jt.setModel(tb);

		}
		// when clicking add button
		else if (e.getSource() == jb2) {
			PersonAddDialog sa = new PersonAddDialog(this, "Add a person", true);
			tb = new Driver();
			tb.listPeopleBySql();
			jt.setModel(tb);
		} else if (e.getSource() == jb3) {
			// modify one person's information
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// Message
				JOptionPane.showMessageDialog(this, "Please choose one person");
				return;
			}
			// Modify dialog
			new PersonUpdDialog(this, "Modify information", true, tb, rowNum);
			tb = new Driver();
			tb.listPeopleBySql();
			jt.setModel(tb);
		}

		else if (e.getSource() == jb4) {
			// user wants to delete one person
			// if user did not choose any person will return -1
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please choose one person you want to delete");
				return;
			}

			String personDelName = (String) tb.getValueAt(rowNum, 0);

			//

			if (tb.checkIfCanDel(personDelName)) {
				JOptionPane.showMessageDialog(this, "This person in a relationship, you can not delete");
				return;
			}

			// call delete method delPerson
			if (tb.delPerson(personDelName)) {
				JOptionPane.showMessageDialog(this, "Delete successfully");

				tb.listPeopleBySql();
				jt.setModel(tb);
			} else {
				// if delete fail there is a message show up
				JOptionPane.showMessageDialog(this, "Delete fail");

			}
		} else if (e.getSource() == jb5) {
			// user wants to delete one person
			// if user did not choose any person will return -1
			int[] rowNum = this.jt.getSelectedRows();

			if (rowNum.length != 2) {
				JOptionPane.showMessageDialog(this, "Please choose two People you want to make a relation");
				return;
			}
			// //get the name of this person
			String personRelName1 = (String) tb.getValueAt(rowNum[0], 0);
			String personRelName2 = (String) tb.getValueAt(rowNum[1], 0);
			String ageStr1 = (String) tb.getValueAt(rowNum[0], 1);
			String ageStr2 = (String) tb.getValueAt(rowNum[1], 1);

			MakeRelationDIalog relationDialog = new MakeRelationDIalog(this, "Modify information", true, personRelName1,
					personRelName2, ageStr1, ageStr2);

		} else if (e.getSource() == jb6) {
			int[] rowNum = this.jt.getSelectedRows();

			if (rowNum.length != 2) {
				JOptionPane.showMessageDialog(this, "Please choose two People  ");
				return;
			}
			String personName1 = (String) tb.getValueAt(rowNum[0], 0);
			String personName2 = (String) tb.getValueAt(rowNum[1], 0);

			String relation = tb.checkRelation(personName1, personName2);

			JOptionPane.showMessageDialog(this, "Their relation is " + relation);
			return;

		} else if (e.getSource() == jb7) {
			int[] rowNum = this.jt.getSelectedRows();
			ArrayList arr1 = new ArrayList();
			ArrayList arr2 = new ArrayList();
			if (rowNum.length != 2) {
				JOptionPane.showMessageDialog(this, "Please choose two People  ");
				return;
			}
			String personName1 = (String) tb.getValueAt(rowNum[0], 0);
			String personName2 = (String) tb.getValueAt(rowNum[1], 0);
			String relation = tb.checkRelation(personName1, personName2);

			if (!relation.equals("No any relation")) {
				JOptionPane.showMessageDialog(this, "They are " + relation + ", so they dont have a relation chain");
				return;
			}

			arr1 = tb.getFriends(personName1, personName2);
			arr2 = tb.getFriends(personName2, personName1);
			if (arr1.size() == 0 || arr2.size() == 0) {
				JOptionPane.showMessageDialog(this, "They do not have relation chain");
				return;
			}
			for (int i = 0; i < arr1.size(); i++) {
				for (int j = 0; j < arr2.size(); j++) {

					if (arr1.get(i).equals(arr2.get(j))) {
						JOptionPane.showMessageDialog(this,
								"The relations chain is " + personName1 + "-->" + arr1.get(i) + "---->" + personName2);
						return;
					}

					JOptionPane.showMessageDialog(this, "They do not have relation chain");

				}
			}
		} else if (e.getSource() == jb8) {
			System.exit(getDefaultCloseOperation());
		} else if (e.getSource() == jb9) {

			int[] rowNum = this.jt.getSelectedRows();
			if (rowNum.length != 1) {
				// Message will come up if user did not chooss a child
				JOptionPane.showMessageDialog(this, "Please choose one child");
				return;
			}
			int ageChild = Integer.parseInt((String) tb.getValueAt(rowNum[0], 1));
			if (ageChild > 18) {
				//Message will come up if chose over 1 child
				JOptionPane.showMessageDialog(this, "Please choose one child");
				return;
			}
			String childName = (String) tb.getValueAt(rowNum[0], 0);
			ArrayList<String> parentsArr = tb.findParents(childName);
			if (parentsArr.size() == 0 || parentsArr.size() == 1) {
				try {
					//Throw exception when a child only have one parent
					throw new NoParentException("This child only have one parent or no parent");
				} catch (NoParentException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());

				}
			} else if (parentsArr.size() == 2) {
				//find out child's parents
				JOptionPane.showMessageDialog(this, "Parents are " + parentsArr.get(0) + " and " + parentsArr.get(1));
			}

		}

	}

}