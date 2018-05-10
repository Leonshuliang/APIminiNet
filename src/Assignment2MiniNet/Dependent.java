package Assignment2MiniNet;
/**
* dependent is abstract class implements methods in class person
* @author  Yongqi Zhong 3691039
* @version 1.0
* @since   2018-03-20 
*/
public abstract class Dependent implements Person {
 private String id;//id of the person
 private String age;//age of the people in this social network
 private String gender;//gender of the people in this social network
 private String pic;//picture on profile
 private String status;//status of person, like work 
 private String parents;//children's parents
 
 public Dependent(int id,String name,String age,String gender,String pic,String status,String parents){
  this.age=age;
  this.gender=gender;
  this.pic=pic;
  this.status=status;
  this.parents=parents;
 }
 public abstract int getId();
 public abstract void setId(int id);

 public abstract String getName();
 public abstract void setName(String name);
 public abstract String getAge();
 public abstract void setAge(String age);
 public abstract String getGender();
 public abstract void setGender(String gender);
 public abstract String getPic();
 public abstract void setPic(String pic);
 public abstract String getStatus();
 public abstract void setStatus(String status);
 public abstract String getParents();
 public abstract void setParents(String parents);
}