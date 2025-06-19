package model;

import java.time.LocalDate;


public class Appoiment extends ModelUtil{
   
    Integer id;
    LocalDate date;
    String time;
    Integer patientId;
    Integer docterId;
    public Appoiment(LocalDate date, String time, Integer patientId, Integer docterId) {
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.docterId = docterId;
    }
    public Appoiment(int id,LocalDate date, String time, Integer patientId, Integer docterId) {
        this.id=id;
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.docterId = docterId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
    public Integer getDocterId() {
        return docterId;
    }
    public void setDocterId(Integer docterId) {
        this.docterId = docterId;
    }
    @Override
    public String toString() {
        return "Appoiment [id=" + id + ", date=" + date + ", time=" + time + ", patientId=" + patientId + ", docterId="
                + docterId + "]";
    }
     public static Appoiment fromString(String line) {
        String[] parts = line.split("\\|");
        return new Appoiment(convertStringToDate(parts[1]),parts[2],Integer.parseInt (parts[3]),Integer.parseInt( parts[4]));
    }
	public LocalDate getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = convertStringToDate(date);
	}
   
}
