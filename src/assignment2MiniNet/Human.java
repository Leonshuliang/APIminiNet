package assignment2MiniNet;

/**
 * dependent is abstract class implements methods in class person
 * 
 * @author Yongqi Zhong 3691039
 * @version 1.0
 * @since 2018-03-20
 */
public abstract class Human {

	private String name;// name of the person
	private String age;// age of the person in this social network
	private String gender;// gender of the people in this social network

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