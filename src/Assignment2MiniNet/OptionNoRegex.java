package Assignment2MiniNet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
* OptionNoRegex is only for all the ergodic process 
* of option number in this project 
* @author  Shuliang Xin 3647666
* @version 1.0
* @since   2018-03-20 
*/
import java.util.Scanner;
public class OptionNoRegex {
 //verify the option number 

 public int menuRegex(int min,int max)
 {
  String regex="[0-9]{1}";//menu is only one-digit between 1-9
  Scanner sc=new Scanner(System.in);
  while(true)
  {
   String input=sc.nextLine();
   if(input.matches(regex))
   {
    int key=Integer.parseInt(input);
    if(key>=min && key<=max){
     return key;
    }else
    {
     System.out.println("The number you are entering is uncorrect!");
    }
   }else
   {
    System.out.println("Please input a number!");
   }
  }
 
 }

 //verify name
 @SuppressWarnings("resource")
 public String nameRegex(String name){
  String pattern ="[A-Za-z]+";//all letters include capital A_Z and lower a-z
  String str=name;
  Scanner sc=new Scanner(System.in);
  while(true){
   if(str.matches(pattern)){
    return str;
   }else{
    System.out.println("Please Enter valid name");
    str=sc.nextLine();
    continue;
   }
  }
 }
 //verify age
 
 public String ageRegex(String age){
  String pattern ="^([0-9]|[0-9]{2}|100)$";//only between 0-99 is valid
  String str=age;
  Scanner sc=new Scanner(System.in);
  while(true){
   if(str.matches(pattern)){
    return str;
   }else{
    System.out.println("Invalid age,Please enter again");
    str=sc.nextLine();
    continue;
   }
  }
 }
 //verify of gender 
 public String sexRegex(String sex){
  String pattern ="[FM]";//F is female  M is male
  String str=sex;
  Scanner sc=new Scanner(System.in);
  while(true){
   if(str.matches(pattern)){
    return str;
   }else{
    System.out.println("Please Enter M on behalf of Male,F on behalf Female");
    str=sc.nextLine();
    continue;
   }
  }
 }
 public void readFile() {
	 
	 File file = new File("./src/people.txt");
	 if (file.exists()==false){
	 System.out.println("cannot find file");
	 return;
	 }
	 try
	 {
	 FileReader fr = new FileReader(file);
	 BufferedReader br = new BufferedReader(fr);
	 String lineText;
	 lineText = br.readLine();
	 while(lineText!= null){
     creatInfo(lineText);
	 lineText = br.readLine();
	 }
	 } catch ( FileNotFoundException e )
	 {
	 e.printStackTrace();
	 } catch ( IOException e )
	 {
	 e.printStackTrace();
	 }
	
 }

public void creatInfo(String text)

 {
 if (text == null)
 return;
 if ("".equals(text))
 return;

 String[] pswdUsername = text.split(",");
 if (pswdUsername == null || pswdUsername.length < 6)
 {
 System.out.println("Please check content form in your file");
 return;
 }
 else{
 //userName = pswdUsername[0];
 //System.out.print(pswdUsername[0]+pswdUsername[1]+pswdUsername[2]+pswdUsername[3]+pswdUsername[4]+pswdUsername[5]);
 //passWord = pswdUsername[1];
	 
 }
 }
//public boolean checkIDandPsw(String uName,String pWord){
//	 readFile();
//	if(uName.equals(userName)&&pWord.equals(passWord)){
//		return true;
//	}
//	else{
//		System.out.println("Wrong UserName or Password");
//		return false;
//	}
//	
//}
//}

}
