package Assignment2MiniNet;
/**
* baby extends dependent which have own parents
*
* @author  Shuliang Xin 3647666
* @version 1.0
* @since   2018-03-24
*/

public class Baby extends Dependent{
	 private int id;//id of the people in this social network
	 private String name;//name of the people in this social network
	 private String age;//age of the people in this social network
	 private String gender;//gender of the people in this social network
	 private String pic;//picture on profile
	 private String status;//status of person, like work 
	 private String parents;//friends of person

	public Baby(int id, String name, String age, String gender, String pic, String status,String parents) {
		super(id, name, age, gender, pic, status, parents);
		// TODO Auto-generated constructor stub
		  this.id=id;
		  this.name=name;
		  this.age=age;
		  this.gender=gender;
		  this.pic=pic;
		  this.status=status;
		  this.parents=parents;
	}
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}
	@Override
	public String getAge() {
		// TODO Auto-generated method stub
		return age;
	}
	@Override
	public void setAge(String age) {
		// TODO Auto-generated method stub
		this.age=age;
	}
	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return gender;
	}
	@Override
	public void setGender(String gender) {
		// TODO Auto-generated method stub
		this.gender=gender;
	}
	@Override
	public String getPic() {
		// TODO Auto-generated method stub
		return pic;
	}
	@Override
	public void setPic(String pic) {
		// TODO Auto-generated method stub
		this.pic=pic;
	}
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status=status;
	}
	@Override
	public String getParents() {
		// TODO Auto-generated method stub
		return parents;
	}
	@Override
	public void setParents(String parents) {
		// TODO Auto-generated method stub
		this.parents=parents;
	}
}
