package assignment2MiniNet;

import java.io.IOException;

public class MiniNet {
	public static void main(String[] args) throws IOException {

		DBConnect db = new DBConnect();
		db.insertAllPeople();
		 MainFrame miniNet=new MainFrame();

	}
}