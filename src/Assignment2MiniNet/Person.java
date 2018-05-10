package Assignment2MiniNet;
/**
* person is interface adult and dependent inherit Person
*
* @author  Shuliang Xin 3647666
* @version 1.0
* @since   2018-03-20 
*/
/*
 * person is interface , adult and dependent will implement all methods 
 * 
 * */
 interface Person {
	public int getId();
    public void setId(int id);
		 public String getName();
		 public void setName(String name);
		 public String getAge();
		 public void setAge(String age) ;
		 public String getGender() ;
		 public void setGender(String gender) ;
		 public String getPic() ;
		 public void setPic(String pic); 
		 public String getStatus() ;
		 public void setStatus(String status);
}
