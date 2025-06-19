package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import base.Base;
import model.*;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Db extends Base{
    private static Db dataBase;

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    private Db() {
//        loadAdmins();
//        loadReceptionLists();
//        loadPatients();
//        loadDoctors();
//        loadAppointments();

    }
 
    public static Db getInstance() {
        if (dataBase == null) {
            dataBase = new Db();
        }
        return dataBase;
    }
    
      public Map<Integer, Admin> getAdmin() {
    	  Map<Integer, Admin> admins = new HashMap<>();
    	    String query = "SELECT * FROM admins";

    	    try{
    	    	Connection connection = databaseConnection.getConnection();
   	            PreparedStatement statement = connection.prepareStatement(query);
   	            ResultSet result = statement.executeQuery();

    	        while (result.next()) {
    	            int id = result.getInt("id");
    	            String name = result.getString("name");
    	            String number = result.getString("number");
    	            String password = result.getString("password");
    	           
    	            Admin admin = new Admin(id,name, number, password);
    	            admins.put(id, admin);
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return admins;
	  }
	
      public Map<Integer, ReceptionList> getReceptionLists() {
    	    Map<Integer, ReceptionList> receptionLists = new HashMap<>();
    	    String query = "SELECT * FROM receptions";

    	    try {
    	        Connection connection = databaseConnection.getConnection();
    	        PreparedStatement statement = connection.prepareStatement(query);
    	        ResultSet result = statement.executeQuery();

    	        while (result.next()) {
    	            int id = result.getInt("id");
    	            String name = result.getString("name");
    	            String number = result.getString("number");
    	            String password = result.getString("password");
    	            ReceptionList reception = new ReceptionList(id,name, number, password);
    	            receptionLists.put(id, reception);
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return receptionLists;
    	}
      public Map<Integer, Patient> getPatients() {
    	    Map<Integer, Patient> patients = new HashMap<>();
    	    String query = "SELECT * FROM patients";

    	    try {
    	        Connection connection = databaseConnection.getConnection();
    	        PreparedStatement statement = connection.prepareStatement(query);
    	        ResultSet result = statement.executeQuery();

    	        while (result.next()) {
    	            int id = result.getInt("id");
    	            String name = result.getString("name");
    	            String number = result.getString("number");
    	            LocalDate date = result.getDate("date").toLocalDate();
    	            String time = result.getString("time");
    	            byte age = result.getByte("age");
    	            String gender = result.getString("gender");
    	            String address = result.getString("address");
    	            boolean isAllocated = result.getBoolean("isAllocated");

    	            Patient patient = new Patient(id, name, number, date, time, age, gender, address, isAllocated);
    	            patients.put(id, patient);
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return patients;
    	}

      

	
      public Map<Integer, Appoiment> getAppoinments() {
    	    Map<Integer, Appoiment> appoiments = new HashMap<>();
    	    String query = "SELECT * FROM appointments";

    	    try {
    	        Connection connection = databaseConnection.getConnection();
    	        PreparedStatement statement = connection.prepareStatement(query);
    	        ResultSet result = statement.executeQuery();

    	        while (result.next()) {
    	            int id = result.getInt("id");
    	            LocalDate date = result.getDate("date").toLocalDate();
    	            String time = result.getString("time");
    	            int patientId = result.getInt("patientId");
    	            int doctorId = result.getInt("doctorId");
    	            Appoiment appoiment = new Appoiment(id,date, time, patientId, doctorId);
    	            appoiment.setId(id);

    	            appoiments.put(id, appoiment);
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return appoiments;
    	}

	
      public void addAdmin(Admin admin) {
    	    String query = "INSERT INTO admins ( name, number, password) VALUES ( ?, ?, ?)";

    	    try {
    	        Connection connection = databaseConnection.getConnection();
    	        PreparedStatement statement = connection.prepareStatement(query);
    	       
    	        statement.setString(1, admin.getName());
    	        statement.setString(2, admin.getNumber());
    	        statement.setString(3, admin.getPassword());
    	        statement.executeUpdate();
    	        

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}

	
      public void addPatient(Patient patient) {
    	    String query = "INSERT INTO patients (name, number, date, time, age, gender, address, isAllocated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    	    try {
    	    	Connection connection = databaseConnection.getConnection();
    	        PreparedStatement statement = connection.prepareStatement(query);

    	        statement.setString(1, patient.getName());
    	        statement.setString(2, patient.getNumber());
    	        statement.setDate(3, java.sql.Date.valueOf(patient.getDate()));
    	        statement.setString(4, patient.getTime());
    	        statement.setByte(5, patient.getAge());
    	        statement.setString(6, patient.getGender());
    	        statement.setString(7, patient.getAddress());
    	        statement.setBoolean(8, patient.isAllocated());

    	        int rowsInserted = statement.executeUpdate();
    	        System.out.println(rowsInserted);
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}


	
      public void addRecptionList(ReceptionList receptionList) {
    	    String query = "INSERT INTO receptions (name, number, password) VALUES (?, ?, ?)";

    	    try {
    	    	Connection connection = databaseConnection.getConnection();
    	        PreparedStatement statement = connection.prepareStatement(query);

    	        statement.setString(1, receptionList.getName());
    	        statement.setString(2, receptionList.getNumber());
    	        statement.setString(3, receptionList.getPassword());

    	        int rowsInserted = statement.executeUpdate();
    	        System.out.println(rowsInserted);

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}

	
      public void removeRecptionList(int id) {
    	    String query = "DELETE FROM receptions WHERE id = ?";

    	    try {
    	    	Connection connection = databaseConnection.getConnection();
    	        PreparedStatement statement = connection.prepareStatement(query);
    	        statement.setInt(1, id);

    	        int rowsDeleted = statement.executeUpdate();
    	        System.out.println(rowsDeleted);

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}

      public void addAppoinment(Appoiment appoiment) {
    	    String insertAppointment = "INSERT INTO appointments (doctor_id, patient_id, date, time, status) VALUES (?, ?, ?, ?, ?)";
    	    String updatePatient = "UPDATE patients SET isAllocated = true WHERE id = ?";
    	    String updateTimeSlot = "UPDATE doctor_timeslots ts " +
    	                            "JOIN doctor_dates dd ON ts.date_id = dd.id " +
    	                            "SET ts.is_available = false " +
    	                            "WHERE dd.doctor_id = ? AND dd.available_date = ? AND ts.time_slot = ?";

    	    try (Connection conn = databaseConnection.getConnection()) {
    	        conn.setAutoCommit(false); 
    	        try (PreparedStatement insertStmt = conn.prepareStatement(insertAppointment)) {
    	            insertStmt.setInt(1, appoiment.getDocterId());
    	            insertStmt.setInt(2, appoiment.getPatientId());
    	            insertStmt.setDate(3, java.sql.Date.valueOf(appoiment.getDate()));
    	            insertStmt.setString(4, appoiment.getTime());
    	            insertStmt.executeUpdate();
    	        }
    	        try (PreparedStatement updatePatientStmt = conn.prepareStatement(updatePatient)) {
    	            updatePatientStmt.setInt(1, appoiment.getPatientId());
    	            updatePatientStmt.executeUpdate();
    	        }

    	        try (PreparedStatement updateTimeStmt = conn.prepareStatement(updateTimeSlot)) {
    	            updateTimeStmt.setInt(1, appoiment.getDocterId());
    	            updateTimeStmt.setDate(2, java.sql.Date.valueOf(appoiment.getDate()));
    	            updateTimeStmt.setString(3, appoiment.getTime());
    	            updateTimeStmt.executeUpdate();
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	       
    	    }
    	}

      public Patient getPatient(int patientId) {
    	    String query = "SELECT * FROM patients WHERE id = ?";
    	    Patient patient = null;

    	    try {
    	        Connection connection = databaseConnection.getConnection();
    	        PreparedStatement statement = connection.prepareStatement(query);
    	        statement.setInt(1, patientId);
    	        ResultSet result = statement.executeQuery();

    	        if (result.next()) {
    	            int id = result.getInt("id");
    	            String name = result.getString("name");
    	            String number = result.getString("number");
    	            LocalDate date = result.getDate("date").toLocalDate();
    	            String time = result.getString("time");
    	            byte age = result.getByte("age");
    	            String gender = result.getString("gender");
    	            String address = result.getString("address");
    	            boolean isAllocated = result.getBoolean("isAllocated");

    	            patient = new Patient(id, name, number, date, time, age, gender, address, isAllocated);
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return patient;
    	}
      public Doctor getDocter(int doctorId) {
    	  String query = "SELECT d.id AS doctor_id, d.name, d.age, d.number, " +
                  "dd.available_date, ts.time_slot " +
                  "FROM doctors d " +
                  "JOIN doctor_dates dd ON d.id = dd.doctor_id " +
                  "JOIN doctor_timeslots ts ON dd.id = ts.date_id " +
                  "WHERE d.id = ?"+"AND dd.is_available==true AND ts.is_available ";

				   Doctor doctor = null;
				
				   try (Connection conn = databaseConnection.getConnection();
				        PreparedStatement stmt = conn.prepareStatement(query)) {
				
				       stmt.setInt(1, doctorId);
				       ResultSet rs = stmt.executeQuery();
				
				       Map<LocalDate, List<String>> slots = new HashMap<>();
				     
				
				       while (rs.next()) {
				           if (doctor == null) {
				              int id = rs.getInt("doctor_id");
				             String  name = rs.getString("name");
				             byte age = rs.getByte("age");
				             String number = rs.getString("number");
				               doctor = new Doctor(id, name, (byte)age, number);
				           }
				
				           LocalDate date = rs.getDate("available_date").toLocalDate();
				           String timeSlot = rs.getString("time_slot");
				
				           slots.computeIfAbsent(date, k -> new ArrayList<>()).add(timeSlot);
				       }
				
				       if (doctor != null) {
				           doctor.setAvalabilSlot(slots);
				       }
				
				   } catch (SQLException e) {
				       e.printStackTrace();
				   }
				
				   return doctor;
	  }
      public void addDocter(Doctor doctor) {
    	    String insertDoctorQuery = "INSERT INTO doctors (id, name, age, number) VALUES (?, ?, ?, ?)";
    	    String insertDateQuery = "INSERT INTO doctor_dates (doctor_id, available_date) VALUES (?, ?)";
    	    String insertTimeQuery = "INSERT INTO doctor_timeslots (date_id, time_slot) VALUES (?, ?)";

    	    try (Connection conn = databaseConnection.getConnection()) {
    	        conn.setAutoCommit(false); 
    	        try (PreparedStatement doctorStmt = conn.prepareStatement(insertDoctorQuery)) {
    	            doctorStmt.setInt(1, doctor.getId());
    	            doctorStmt.setString(2, doctor.getName());
    	            doctorStmt.setByte(3, doctor.getAge());
    	            doctorStmt.setString(4, doctor.getNumber());
    	            doctorStmt.executeUpdate();
    	        }

    	        for (Map.Entry<LocalDate, List<String>> entry : doctor.getAvalabilSlot().entrySet()) {
    	            LocalDate date = entry.getKey();
    	            List<String> timeSlots = entry.getValue();

    	            int dateId;
    	            try (PreparedStatement dateStmt = conn.prepareStatement(insertDateQuery)) {
    	                dateStmt.setInt(1, doctor.getId());
    	                dateStmt.setDate(2, java.sql.Date.valueOf(date));
    	                dateStmt.executeUpdate();

    	                ResultSet keys = dateStmt.getGeneratedKeys();
    	                if (keys.next()) {
    	                    dateId = keys.getInt(1);
    	                } else {
    	                    throw new SQLException("Failed to insert date for doctor: " + doctor.getId());
    	                }
    	            }


    	            try (PreparedStatement timeStmt = conn.prepareStatement(insertTimeQuery)) {
    	                for (String slot : timeSlots) {
    	                    timeStmt.setInt(1, dateId);
    	                    timeStmt.setString(2, slot);
    	                    timeStmt.addBatch();
    	                }
    	                timeStmt.executeBatch();
    	            }
    	        }

    	        conn.commit();

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	       
    	    }
    	}

      public Map<Integer, Doctor> getDocters() {
    	    Map<Integer, Doctor> doctors = new HashMap<>();
    	    String doctorQuery = "SELECT * FROM doctors d ";
    	    String dateQuery = "SELECT * FROM doctor_dates dd WHERE doctor_id = ?"+"AND dd.is_available ==true";
    	    String timeQuery = "SELECT * FROM doctor_timeslots ts WHERE date_id = ?"+"AND ts.is_available ==true";

    	    try (Connection conn = databaseConnection.getConnection();
    	         PreparedStatement doctorStmt = conn.prepareStatement(doctorQuery);
    	         ResultSet doctorRs = doctorStmt.executeQuery()) {

    	        while (doctorRs.next()) {
    	            int id = doctorRs.getInt("id");
    	            String name = doctorRs.getString("name");
    	            byte age = doctorRs.getByte("age");
    	            String number = doctorRs.getString("number");

    	            Map<LocalDate, List<String>> availabilityMap = new HashMap<>();

    	          
    	            try (PreparedStatement dateStmt = conn.prepareStatement(dateQuery)) {
    	                dateStmt.setInt(1, id);
    	                ResultSet dateRs = dateStmt.executeQuery();

    	                while (dateRs.next()) {
    	                    int dateId = dateRs.getInt("id");
    	                    LocalDate date = dateRs.getDate("available_date").toLocalDate();

    	               
    	                    List<String> timeSlots = new ArrayList<>();
    	                    try (PreparedStatement timeStmt = conn.prepareStatement(timeQuery)) {
    	                        timeStmt.setInt(1, dateId);
    	                        ResultSet timeRs = timeStmt.executeQuery();

    	                        while (timeRs.next()) {
    	                            timeSlots.add(timeRs.getString("time_slot"));
    	                        }
    	                    }

    	                    availabilityMap.put(date, timeSlots);
    	                }
    	            }

    	            Doctor doctor = new Doctor(id, name, (byte)age, number);
    	            doctor.setAvalabilSlot(availabilityMap);
    	            doctors.put(id, doctor);
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return doctors;
    	}

	public void setAppoinment(Integer patientId, Integer docterId, String time, LocalDate date) {
		
		
	}



  //
//      private void loadAdmins() {
//          List<String> lines = FileUtil.readFromFile("admins.txt");
//          for (String line : lines) {
//              Admin admin = Admin.fromString(line);
//              admins.put(admin.getId(), admin);
//          }
//      }
  //
//      private void loadReceptionLists() {
//          List<String> lines = FileUtil.readFromFile("receptions.txt");
//          for (String line : lines) {
//              ReceptionList reception = ReceptionList.fromString(line);
//              receptionLists.put(reception.getId(), reception);
//          }
//      }
  //
//      private void loadPatients() {
//          List<String> lines = FileUtil.readFromFile("patients.txt");
//          for (String line : lines) {
//              Patient patient = Patient.fromString(line);
//              patients.put(patient.getId(), patient);
//          }
//      }
  //
//      private void loadDoctors() {
//          List<String> lines = FileUtil.readFromFile("doctors.txt");
//          for (String line : lines) {
//              Doctor doctor = Doctor.fromString(line);
//              doctors.put(doctor.getId(), doctor);
//          }
//      }
  //
//      private void loadAppointments() {
//          List<String> lines = FileUtil.readFromFile("appointments.txt");
//          for (String line : lines) {
//              Appoiment app = Appoiment.fromString(line);
//              appoiments.put(app.getId(), app);
//          }
//      }


//      public Map<Integer, Admin> getAdmin() {
//          return admins;
//      }
  //
//      public Map<Integer, ReceptionList> getReceptionLists() {
//          return receptionLists;
//      }
  //
//      public Map<Integer, Patient> getPatients() {
//          return patients;
//      }
  //
//      public Map<Integer, Doctor> getDocters() {
//          return doctors;
//      }
  //
//      public Map<Integer, Appoiment> getAppoinments() {
//          return appoiments;
//      }
  //
//      public void addAdmin(Admin admin) {
//          admins.put(admin.getId(), admin);
//          FileUtil.saveToFile("admins.txt", admins.values());
//      }
  //
//      public void addpatient(Patient patient) {
//          patients.put(patient.getId(), patient);
//          FileUtil.saveToFile("patients.txt", patients.values());
//      }
  //
//      public void addRecptionList(ReceptionList receptionList) {
//          receptionLists.put(receptionList.getId(), receptionList);
//          FileUtil.saveToFile("receptions.txt", receptionLists.values());
//      }
  //
//      public void removeRecptionList(int id) {
//          if (receptionLists.containsKey(id)) {
//              receptionLists.remove(id);
//              FileUtil.saveToFile("receptions.txt", receptionLists.values());
//          }
//      }
  //
//      public void addDocter(Doctor doctor) {
//          doctors.put(doctor.getId(), doctor);
//          FileUtil.saveToFile("doctors.txt", doctors.values());
//      }
  //
//      public void addAppoinment(Appoiment appoiment) {
//          appoiments.put(appoiment.getId(), appoiment);
//          FileUtil.saveToFile("appointments.txt", appoiments.values());
//      }
  //
//      public Patient getPatient(int patientId) {
//          return patients.get(patientId);
//      }
  //
//      public Doctor getDocter(int doctorId) {
//          return doctors.get(doctorId);
//      }
  //  
	  
}
