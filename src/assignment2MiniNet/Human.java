package assignment2MiniNet;

/**
 *human is abstract class includes basic information of human
 * 
 * @author Yongqi Zhong 3691039
 * @version 2.0
 * @since 20-05-2018
 */
public abstract class Human {

	private String name;// name of the person
	private String age;// age of the person in this social network
	private String gender;// gender of the people in this social network

	// default constructor
	public Human() {
	}

	public Human(String name, String age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;

	}

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getAge();

	public abstract void setAge(String age);

	public abstract String getGender();

	public abstract void setGender(String gender);

}