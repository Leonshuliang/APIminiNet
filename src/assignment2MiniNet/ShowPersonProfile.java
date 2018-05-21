package assignment2MiniNet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class ShowPersonProfile extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// set all components in swing
	JButton jb1, jb2, jb3, jb4, jb5, jb6;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;

	public ShowPersonProfile(MouseAdapter mouseAdapter, String title, boolean modal, Driver sm, int rowNums) {
		super();// "ID","Name","Age","Gender","Pic","Status"

		JPanel panel = new JPanel();
		JLabel TitleLabel = new JLabel("MiniNet Menu：");
		panel.setLayout(null);

		TitleLabel.setBounds(100, 10, 220, 40);

		TitleLabel.setHorizontalAlignment(JLabel.CENTER);

		panel.add(TitleLabel);

		// information button
		jb1 = new JButton("Name");

		jb2 = new JButton("Age");

		jb3 = new JButton("Gender");

		jb4 = new JButton("Status");

		jb5 = new JButton("State");

		jb6 = new JButton("Picture");

		// put all buttons in panel
		jb1.setBounds(20, 40, 80, 30);
		panel.add(jb1);

		jb2.setBounds(20, 70, 80, 30);
		panel.add(jb2);

		jb3.setBounds(20, 100, 80, 30);
		panel.add(jb3);

		jb4.setBounds(20, 130, 80, 30);
		panel.add(jb4);

		jb5.setBounds(20, 160, 80, 30);
		panel.add(jb5);

		jb6.setBounds(20, 190, 80, 30);
		panel.add(jb6);

		//
		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		jtf5 = new JTextField();
		jtf6 = new JTextField();

		jtf1.setText((String) sm.getValueAt(rowNums, 0));
		jtf1.setBounds(110, 40, 220, 30);
		jtf2.setText((String) sm.getValueAt(rowNums, 1));
		jtf2.setBounds(110, 70, 220, 30);
		jtf3.setText((String) sm.getValueAt(rowNums, 2));
		jtf3.setBounds(110, 100, 220, 30);
		jtf4.setText((String) sm.getValueAt(rowNums, 4));
		jtf4.setBounds(110, 130, 220, 30);
		jtf5.setText((String) sm.getValueAt(rowNums, 5));
		jtf5.setBounds(110, 160, 220, 30);

		String picTxt = (String) sm.getValueAt(rowNums, 3);
		if (picTxt.isEmpty()) {
			System.out.println(picTxt);
			jtf6.setText("NO PICTURE");
			jtf6.setBounds(110, 190, 220, 30);
			panel.add(jtf6);
		} else {
			ImageIcon ii = new ImageIcon((String) sm.getValueAt(rowNums, 3));
			ii.setImage(ii.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
			JLabel label = new JLabel(ii);
			label.setBounds(110, 160, 240, 500);
			panel.add(label);
		}

		// set all textfield are not editable
		jtf1.setEditable(false);
		jtf2.setEditable(false);
		jtf3.setEditable(false);
		jtf4.setEditable(false);
		jtf5.setEditable(false);

		panel.add(jtf1);
		panel.add(jtf2);
		panel.add(jtf3);
		panel.add(jtf4);
		panel.add(jtf5);

		this.add(panel);

		this.setBounds(400, 150, 400, 550);

		this.setTitle("MiniNet");

		this.setVisible(true);

		this.setResizable(false);
	}

}