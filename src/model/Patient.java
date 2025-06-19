package model;

import java.time.LocalDate;

public class Patient extends ModelUtil{
	 public Patient( String name, String number, LocalDate date, String time, byte age, String gender,
				String address) {

			this.name = name;
			this.number = number;
			this.date = date;
			this.time = time;
			this.age = age;
			this.gender = gender;
			this.address = address;
		}
    public Patient(Integer id, String name, String number, LocalDate date, String time, byte age, String gender,
			String address,boolean isAllocated) {
		
		this.id = id;
		this.name = name;
		this.number = number;
		this.date = date;
		this.time = time;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.isAllocated = isAllocated;
	}
	Integer id;
    String name;
    String number;
    LocalDate date;
    String time;
    byte age;
    String gender;
    String address;
    boolean isAllocated;
   


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
    public byte getAge() {
        return age;
    }
    public void setAge(byte age) {
        this.age = age;
    }
    public boolean isAllocated() {
        return isAllocated;
    }
    public void setAllocated(boolean isAllocated) {
        this.isAllocated = isAllocated;
    }
   
    public LocalDate getDate() {
        return date;
    }
    public void setDate(String date) {
    	
        this.date = convertStringToDate(date);
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", number=" + number + ", date=" + date + ", time=" + time
                + ", age=" + age + ", isAllocated=" + isAllocated + "]";
    }
    public static Patient fromString(String line) {
        String[] parts = line.split("\\|");

        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String number = parts[2];
        LocalDate date = convertStringToDate(parts[3]);
        String time = parts[4];
        byte age = Byte.parseByte(parts[5]);
        String gender = parts[6];
        String address = parts[7];
        boolean isAllocated = Boolean.parseBoolean(parts[8]);

        return new Patient(id, name, number, date, time, age, gender, address, isAllocated);
    }


}
