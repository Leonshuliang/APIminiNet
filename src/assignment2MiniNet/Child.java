package assignment2MiniNet;

/**
 *
 * The people under 18 are child includes 1-3 young child and 3-18 child
 * 
 * * @author Yongqi Zhong 3691039
 * @version 2.0
 * @since 20-05-2018
 */

public class Child extends Person {
	private String name;// name of the people in this social network
	private String age;// age of the people in this social network
	private String gender;// gender of the people in this social network
	private String pic;// picture on profile
	private String status;// status of person, like work
	private String state;// friends of person

	public Child(String name, String age, String gender, String pic, String status, String state) {
		super(name, age, gender, pic, status, state);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public String getAge() {
		// TODO Auto-generated method stub
		return age;
	}

	@Override
	public void setAge(String age) {
		// TODO Auto-generated method stub
		this.age = age;
	}

	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return gender;
	}

	@Override
	public void setGender(String gender) {
		// TODO Auto-generated method stub
		this.gender = gender;
	}

	@Override
	public String getPic() {
		// TODO Auto-generated method stub
		return pic;
	}

	@Override
	public void setPic(String pic) {
		// TODO Auto-generated method stub
		this.pic = pic;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status = status;
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return state;
	}

	@Override
	public void setState(String state) {
		// TODO Auto-generated method stub
		this.state = state;
	}

}
