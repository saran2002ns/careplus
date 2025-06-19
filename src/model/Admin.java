package model;

public class Admin {
  
    Integer id;
    String name;
    String number;
    String password;
    public Admin( String name, String number, String password) {
       
        this.name = name;
        this.number = number;
        this.password = password;
       
    }
    public Admin( int id,String name, String number, String password) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.password = password;
       
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", name=" + name + ", number=" + number + "," + ", avalability=" + "]";
    }
    public static Admin fromString(String line) {
        String[] parts = line.split("\\|");
        return new Admin(parts[1], parts[2], parts[3]);
       
    }

}
