package assignment2MiniNet;

/**
 * 
 * Adults are the people whose age over 18
 *
 * @author Yongqi Zhong 3691039
 * @version 2.0
 * @since 20-05-2018
 */
public class Adult extends Person {

	private String name;// name of the people in this social network
	private String age;// age of the people in this social network
	private String gender;// gender of the people in this social network
	private String pic;// picture on profile
	private String status;// status of person, like work
	private String state;// state of where is this person from

	//constructor
	public Adult(String name, String age, String gender, String pic, String status, String state) {
		super(name, age, gender, pic, status, state);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}