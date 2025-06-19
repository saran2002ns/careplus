package model;

public class ReceptionList {
  
    Integer id;
    String name;
    String number;
    String password;
    public ReceptionList( String name, String number, String password) {
        this.name = name;
        this.number = number;
        this.password = password;
    }
    public ReceptionList(int id, String name, String number, String password) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.password = password;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "ReceptionList [id=" + id + ", name=" + name + ", number=" + number + ", password=" + password + "]";
    }
    public static ReceptionList fromString(String line) {
        String[] parts = line.split("\\|");
        return new ReceptionList(parts[1], parts[2], parts[3]);
      
    }
    
}
