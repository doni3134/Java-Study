package member;

public class Admin extends Person{
    private String id = "Admin";
    private String password = "1234"; // pw
    public Admin(String name, int phone){
        super(name, phone);
    }
    public String getId(){
        return id;
    }
    public String getPassword(){
        return password;
    }
}
