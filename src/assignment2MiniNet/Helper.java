package assignment2MiniNet;

/**
 * 
 *Helper includes regular expression matching and read file method 
 *
 * @author Shuliang Xin 3647666
 * @version 2.0
 * @since 20-05-2018
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Helper {

	// verify name
	@SuppressWarnings("resource")
	public String nameRegex(String name) {
		String pattern = "[A-Za-z]+";// all letters include capital A_Z and lower a-z
		String str = name;
		Scanner sc = new Scanner(System.in);
		while (true) {
			if (str.matches(pattern)) {
				return str;
			} else {
				str = sc.nextLine();
				continue;
			}
		}
	}

	// verify age
	public String ageRegex(String age) {
		String pattern = "^([0-9]|[0-9]{2}|100)$";// only between 0-99 is valid
		String str = age;
		Scanner sc = new Scanner(System.in);
		while (true) {
			if (str.matches(pattern)) {
				return str;
			} else {
				str = sc.nextLine();
				continue;
			}
		}
	}

	// Read people.txt from the location, if no file will call the data from
	// database
	public void readFile() {

		File file = new File("./src/people.txt");
		if (file.exists() == false) {
			System.out.println("cannot find file");
			return;
		}
		try {
			// define reader to read file
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String lineText;
			lineText = br.readLine();
			//only read with real context 
			while (lineText != null) {
				creatInfo(lineText);
				lineText = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// read all the context into each line then split them by "，"
	public void creatInfo(String text)

	{
		if (text == null)
			return;
		if ("".equals(text))
			return;
		String[] inforMation = text.split(",");
		if (inforMation == null || inforMation.length < 6) {
			return;
		} else {

		}
	}

}
