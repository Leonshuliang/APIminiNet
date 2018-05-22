package assignment2MiniNet;

/**
 * Main class
 * 
 * @author Shuliang Xin 3647666
 * @version 2.0
 * @since 20-05-2018
 */
import java.io.IOException;

//start the MiniNet system
public class MiniNet {
	public static void main(String[] args) throws IOException {

		DBConnect db = new DBConnect();
		db.insertAllPeople();
		MainFrame miniNet = new MainFrame();

	}
}