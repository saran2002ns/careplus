package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Doctor extends ModelUtil{
	Integer id ;
    String name ;
    byte age;
    String number;
    Map<LocalDate,List<String>> avalabilSlot;
	 public Doctor(String name, byte age, String number) {
	        this.name = name;
	        this.age = age;
	        this.number = number;
	        this.avalabilSlot.put(convertStringToDate("02-02-2024"), new ArrayList<>(List.of("9","11","14","19")));
	        this.avalabilSlot.put(convertStringToDate("03-02-2024"), new ArrayList<>(List.of("9","11","14","19")));
	        this.avalabilSlot.put(convertStringToDate("04-02-2024"), new ArrayList<>(List.of("9","11","14","19")));
	 }
    
	 public Doctor(int id,String name, byte age, String number) {
	        this.id=id;
	        this.name = name;
	        this.age = age;
	        this.number = number;
	 }
    
   
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Map<LocalDate, List<String>> getAvalabilSlot() {
        return avalabilSlot;
    }
    public void setAvalabilSlot(Map<LocalDate, List<String>> avalabilSlot) {
        this.avalabilSlot = avalabilSlot;
    }
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public byte getAge() {
        return age;
    }
    public void setAge(byte age) {
        this.age = age;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    @Override
    public String toString() {
        return "Docter [id=" + id + ", name=" + name + ", age=" + age + ", number=" + number + ", avalabilSlot="
                + avalabilSlot + "]";
    }
    public static Doctor fromString(String line) {
        String[] parts = line.split("\\|");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        byte age = Byte.parseByte(parts[2]);
        String number = parts[3];

        return new Doctor(id, name, age, number);
    }


}
