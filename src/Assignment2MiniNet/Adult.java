package Assignment2MiniNet;

/**
* adult is over 16 has friends
*
* @author  Yongqi Zhong 3691039
* @version 1.0
* @since   2018-03-23
*/
public class Adult implements Person {
 private int id;//id of the people in this social network
 private String name;//name of the people in this social network
 private String age;//age of the people in this social network
 private String gender;//gender of the people in this social network
 private String pic;//picture on profile
 private String status;//status of person, like work 
 private String friends;//friends of person
 public Adult(int id,String name,String age,String gender,String pic,String status,String friends){
  this.id=id;
  this.name=name;
  this.age=age;
  this.gender=gender;
  this.pic=pic;
  this.status=status;
  this.friends=friends;
 }
 //
 public int getId() {
  return id;
 }
 public void setId(int id) {
  this.id=id;
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
 public String getFriends() {
	  return friends;
 }
	 public void setFriends(String friends) {
	  this.friends = friends;
	 }
 
}