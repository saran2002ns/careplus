package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

import base.Base;
import model.*;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Db extends Base {
	private static Db dataBase;

	private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

	private Db() {
		// loadAdmins();
		// loadReceptionLists();
		// loadPatients();
		// loadDoctors();
		// loadAppointments();

	}

	public static Db getInstance() {
		if (dataBase == null) {
			dataBase = new Db();
		}
		return dataBase;
	}

	public void addAdmin(Admin admin) {
		String query = "INSERT INTO admins ( admin_name, admin_number, admin_password) VALUES ( ?, ?, ?)";

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

	public Map<Integer, Admin> getAdmin() {
		Map<Integer, Admin> admins = new HashMap<>();
		String query = "SELECT * FROM admins";

		try {

			Connection connection = databaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int id = result.getInt("admin_id");
				String name = result.getString("admin_name");
				String number = result.getString("admin_number");
				String password = result.getString("admin_password");

				Admin admin = new Admin(id, name, number, password);
				admins.put(id, admin);
			}

		} catch (SQLException e) {
			System.out.println("get admin");
			e.printStackTrace();
		}
		return admins;
	}

	public void addRecptionList(ReceptionList receptionList) {
		String query = "INSERT INTO receptionList (reception_name, reception_number, reception_password) VALUES (?, ?, ?)";

		try {
			Connection connection = databaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, receptionList.getName());
			statement.setString(2, receptionList.getNumber());
			statement.setString(3, receptionList.getPassword());

			int rowsInserted = statement.executeUpdate();
			System.out.println(rowsInserted);

		} catch (SQLException e) {
			System.out.println("add receptionList ");
			e.printStackTrace();
		}
	}

	public Map<Integer, ReceptionList> getReceptionLists() {
		Map<Integer, ReceptionList> receptionLists = new HashMap<>();
		String query = "SELECT * FROM receptionList";

		try {

			Connection connection = databaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int id = result.getInt("reception_id");
				String name = result.getString("reception_name");
				String number = result.getString("reception_number");
				String password = result.getString("reception_password");
				ReceptionList reception = new ReceptionList(id, name, number, password);
				receptionLists.put(id, reception);
			}

		} catch (SQLException e) {
			System.out.println("get receptionList ");
			e.printStackTrace();
		}

		return receptionLists;
	}

	public void removeRecptionList(int id) {
		String query = "DELETE FROM receptionList WHERE id = ?";

		try {
			Connection connection = databaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			System.out.println(rowsDeleted);

		} catch (SQLException e) {
			System.out.println("delete receptionList");
			e.printStackTrace();
		}
	}

	public void addPatient(Patient patient) {
		String query = "INSERT INTO patients (patient_name, patient_number, patient_date, patient_time, patient_age, patient_gender, patient_address, patient_isAllocated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
			System.out.println("add patient");
			e.printStackTrace();
		}
	}

	public Map<Integer, Patient> getPatients() {
		Map<Integer, Patient> patients = new HashMap<>();
		String query = "SELECT * FROM patients";

		try {
			Connection connection = databaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int id = result.getInt("patient_id");
				String name = result.getString("patient_name");
				String number = result.getString("patient_number");
				LocalDate date = result.getDate("patient_date").toLocalDate();
				String time = result.getString("patient_time");
				byte age = result.getByte("patient_age");
				String gender = result.getString("patient_gender");
				String address = result.getString("patient_address");
				boolean isAllocated = result.getBoolean("patient_isAllocated");

				Patient patient = new Patient(id, name, number, date, time, age, gender, address, isAllocated);
				patients.put(id, patient);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patients;
	}

	public Patient getPatient(int patientId) {
		String query = "SELECT * FROM patients WHERE patient_id = ?";
		Patient patient = null;

		try {
			Connection connection = databaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, patientId);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				int id = result.getInt("patient_id");
				String name = result.getString("patient_name");
				String number = result.getString("patient_number");
				LocalDate date = result.getDate("patient_date").toLocalDate();
				String time = result.getString("patient_time");
				byte age = result.getByte("patient_age");
				String gender = result.getString("patient_gender");
				String address = result.getString("patient_address");
				boolean isAllocated = result.getBoolean("patient_isAllocated");

				patient = new Patient(id, name, number, date, time, age, gender, address, isAllocated);
			} 

		} catch (SQLException e) {
			System.out.println("get patient id");
			e.printStackTrace();
		}

		return patient;
	}

public void addAppoinment(Appoiment appoiment) {
    String insertAppointment = "INSERT INTO appointments (doctor_id, patient_id, appointment_date, appointment_time) VALUES (?, ?, ?, ?)";
    String updatePatient = "UPDATE patients SET patient_isAllocated = true WHERE patient_id = ?";
    String updateTimeSlot = "UPDATE doctor_timeslots ts " +
            "JOIN doctor_dates dd ON ts.date_id = dd.doctor_dates_id " +  
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

        conn.commit(); 
    } catch (SQLException e) {
        System.out.println("add appoinment");
        e.printStackTrace();
    }
}


	public Map<Integer, Appoiment> getAppoinments() {
		Map<Integer, Appoiment> appoiments = new HashMap<>();
		String query = "SELECT * FROM appointments";

		try {
			Connection connection = databaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int id = result.getInt("appointment_id");
				LocalDate date = result.getDate("appointment_date").toLocalDate();
				String time = result.getString("appointment_time");
				int patientId = result.getInt("patient_Id");
				int doctorId = result.getInt("doctor_Id");
				Appoiment appoiment = new Appoiment(id, date, time, patientId, doctorId);
				appoiment.setId(id);

				appoiments.put(id, appoiment);
			}

		} catch (SQLException e) {
			System.out.println("get appoinment");
			e.printStackTrace();
		}

		return appoiments;
	}

public void addDocter(Doctor doctor) {
    String insertDoctorQuery = "INSERT INTO doctors (doctor_name, doctor_age, doctor_number) VALUES (?, ?, ?)";
    String insertDateQuery = "INSERT INTO doctor_dates (doctor_id, available_date) VALUES (?, ?)";
    String insertTimeQuery = "INSERT INTO doctor_timeslots (date_id, time_slot) VALUES (?, ?)";

    try (Connection conn = databaseConnection.getConnection()) {
        conn.setAutoCommit(false);

        int doctorId;
        try (PreparedStatement doctorStmt = conn.prepareStatement(insertDoctorQuery, Statement.RETURN_GENERATED_KEYS)) {
            doctorStmt.setString(1, doctor.getName());
            doctorStmt.setByte(2, doctor.getAge());
            doctorStmt.setString(3, doctor.getNumber());
            doctorStmt.executeUpdate();

            ResultSet keys = doctorStmt.getGeneratedKeys();
            if (keys.next()) {
                doctorId = keys.getInt(1);
            } else {
				System.out.println("Not able to Add");
               return;
            }
        }

   
        for (Map.Entry<LocalDate, List<String>> entry : doctor.getAvalabilSlot().entrySet()) {
            LocalDate date = entry.getKey();
            List<String> timeSlots = entry.getValue();

            int dateId;
            try (PreparedStatement dateStmt = conn.prepareStatement(insertDateQuery, Statement.RETURN_GENERATED_KEYS)) {
                dateStmt.setInt(1, doctorId);
                dateStmt.setDate(2, java.sql.Date.valueOf(date));
                dateStmt.executeUpdate();

                ResultSet dateKeys = dateStmt.getGeneratedKeys();
                if (dateKeys.next()) {
                    dateId = dateKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve date_id after insert.");
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
        System.out.println("Doctor and availability added successfully.");

    } catch (SQLException e) {
        System.out.println("add docter ERR");
        e.printStackTrace();
    }
}



public Map<Integer, Doctor> getDocters() {
    Map<Integer, Doctor> doctors = new HashMap<>();
    String doctorQuery = "SELECT * FROM doctors";
    String dateQuery = "SELECT * FROM doctor_dates WHERE doctor_id = ?";
    String timeQuery = "SELECT * FROM doctor_timeslots WHERE date_id = ? AND is_available = true";

    try (Connection conn = databaseConnection.getConnection();
         PreparedStatement doctorStmt = conn.prepareStatement(doctorQuery);
         ResultSet doctorRs = doctorStmt.executeQuery()) {

        while (doctorRs.next()) {
            int id = doctorRs.getInt("doctor_id");
            String name = doctorRs.getString("doctor_name");
            byte age = doctorRs.getByte("doctor_age");
            String number = doctorRs.getString("doctor_number");

            Map<LocalDate, List<String>> availabilityMap = new HashMap<>();

            try (PreparedStatement dateStmt = conn.prepareStatement(dateQuery)) {
                dateStmt.setInt(1, id);
                ResultSet dateRs = dateStmt.executeQuery();

                while (dateRs.next()) {
                    int dateId = dateRs.getInt("doctor_dates_id");
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

            Doctor doctor = new Doctor(id, name, age, number);
            doctor.setAvalabilSlot(availabilityMap);
            doctors.put(id, doctor);
        }

    } catch (SQLException e) {
        System.out.println("Error while fetching doctors:");
        e.printStackTrace();
    }

    return doctors;
}

public Doctor getDocterById(int doctorId) {
    String query = "SELECT d.doctor_id, d.doctor_name, d.doctor_age, d.doctor_number, " +
                   "dd.available_date, ts.time_slot " +
                   "FROM doctors d " +
                   "JOIN doctor_dates dd ON d.doctor_id = dd.doctor_id " +
                   "JOIN doctor_timeslots ts ON dd.doctor_dates_id = ts.date_id " +
                   "WHERE d.doctor_id = ? AND ts.is_available = true";

    Doctor doctor = null;

    try (
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)
    ) {
        stmt.setInt(1, doctorId);
        try (ResultSet rs = stmt.executeQuery()) {

            Map<LocalDate, List<String>> slots = new HashMap<>();

            while (rs.next()) {
                if (doctor == null) {
                    int id = rs.getInt("doctor_id");
                    String name = rs.getString("doctor_name");
                    byte age = rs.getByte("doctor_age");
                    String number = rs.getString("doctor_number");
                    doctor = new Doctor(id, name, age, number);
                }

                LocalDate date = rs.getDate("available_date").toLocalDate();
                String timeSlot = rs.getString("time_slot");
                slots.computeIfAbsent(date, k -> new ArrayList<>()).add(timeSlot);
            }

            if (doctor != null) {
                doctor.setAvalabilSlot(slots);
            } 
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return doctor;
}

public ReceptionList getReceptionListById(int id) {
    String query = "SELECT * FROM receptionList WHERE reception_id = ?";
    ReceptionList receptionist = null;

    try (
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)
    ) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("reception_name");
                String number = rs.getString("reception_number");
                String password = rs.getString("reception_password");

                receptionist = new ReceptionList(id, name, number, password);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error fetching receptionist by ID");
        e.printStackTrace();
    }

    return receptionist;
}


public void removeRecptionListById(int id) {
    String query = "DELETE FROM receptionList WHERE reception_id = ?";

    try (
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)
    ) {
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Receptionist with ID " + id + " removed successfully.");
        } else {
            System.out.println("No receptionist found with ID: " + id);
        }
    } catch (SQLException e) {
        System.out.println("Error removing receptionist by ID.");
        e.printStackTrace();
    }
}

public void removeDocterById(int docterId) {
    String deleteTimeSlots = "DELETE ts FROM doctor_timeslots ts " +
                             "JOIN doctor_dates dd ON ts.date_id = dd.doctor_dates_id " +
                             "WHERE dd.doctor_id = ?";
    String deleteDates = "DELETE FROM doctor_dates WHERE doctor_id = ?";
    String deleteDoctor = "DELETE FROM doctors WHERE doctor_id = ?";

    try (Connection conn = databaseConnection.getConnection()) {
        conn.setAutoCommit(false); // Transaction start

        try (
            PreparedStatement stmt1 = conn.prepareStatement(deleteTimeSlots);
            PreparedStatement stmt2 = conn.prepareStatement(deleteDates);
            PreparedStatement stmt3 = conn.prepareStatement(deleteDoctor)
        ) {

            stmt1.setInt(1, docterId);
            stmt1.executeUpdate();

 
            stmt2.setInt(1, docterId);
            stmt2.executeUpdate();


            stmt3.setInt(1, docterId);
            int rowsAffected = stmt3.executeUpdate();

            conn.commit();

            if (rowsAffected > 0) {
                System.out.println("Doctor with ID " + docterId + " removed successfully.");
            } else {
                System.out.println("No doctor found with ID " + docterId);
            }

        } catch (SQLException e) {
            conn.rollback(); 
            System.out.println("Failed to delete doctor with ID " + docterId);
            e.printStackTrace();
        }

    } catch (SQLException e) {
        System.out.println("Database connection error during doctor deletion.");
        e.printStackTrace();
    }
}

public void searchPatientByName(String name) {
    String query = "SELECT * FROM patients WHERE patient_name LIKE ?";
    
    try (
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)
    ) {
        stmt.setString(1, "%" + name + "%"); // partial match
        ResultSet rs = stmt.executeQuery();
        
        boolean found = false;

        while (rs.next()) {
            int id = rs.getInt("patient_id");
            String patientName = rs.getString("patient_name");
            String number = rs.getString("patient_number");
            LocalDate date = rs.getDate("patient_date").toLocalDate();
            String time = rs.getString("patient_time");
            byte age = rs.getByte("patient_age");
            String gender = rs.getString("patient_gender");
            String address = rs.getString("patient_address");
            boolean isAllocated = rs.getBoolean("patient_isAllocated");

            Patient patient = new Patient(id, patientName, number, date, time, age, gender, address, isAllocated);
            System.out.println(patient);
            found = true;
        }

        if (!found) {
            System.out.println("No patient found with name containing: " + name);
        }

    } catch (SQLException e) {
        System.out.println("Error while searching for patient by name.");
        e.printStackTrace();
    }
}






	//
	// private void loadAdmins() {
	// List<String> lines = FileUtil.readFromFile("admins.txt");
	// for (String line : lines) {
	// Admin admin = Admin.fromString(line);
	// admins.put(admin.getId(), admin);
	// }
	// }
	//
	// private void loadReceptionLists() {
	// List<String> lines = FileUtil.readFromFile("receptions.txt");
	// for (String line : lines) {
	// ReceptionList reception = ReceptionList.fromString(line);
	// receptionLists.put(reception.getId(), reception);
	// }
	// }
	//
	// private void loadPatients() {
	// List<String> lines = FileUtil.readFromFile("patients.txt");
	// for (String line : lines) {
	// Patient patient = Patient.fromString(line);
	// patients.put(patient.getId(), patient);
	// }
	// }
	//
	// private void loadDoctors() {
	// List<String> lines = FileUtil.readFromFile("doctors.txt");
	// for (String line : lines) {
	// Doctor doctor = Doctor.fromString(line);
	// doctors.put(doctor.getId(), doctor);
	// }
	// }
	//
	// private void loadAppointments() {
	// List<String> lines = FileUtil.readFromFile("appointments.txt");
	// for (String line : lines) {
	// Appoiment app = Appoiment.fromString(line);
	// appoiments.put(app.getId(), app);
	// }
	// }

	// public Map<Integer, Admin> getAdmin() {
	// return admins;
	// }
	//
	// public Map<Integer, ReceptionList> getReceptionLists() {
	// return receptionLists;
	// }
	//
	// public Map<Integer, Patient> getPatients() {
	// return patients;
	// }
	//
	// public Map<Integer, Doctor> getDocters() {
	// return doctors;
	// }
	//
	// public Map<Integer, Appoiment> getAppoinments() {
	// return appoiments;
	// }
	//
	// public void addAdmin(Admin admin) {
	// admins.put(admin.getId(), admin);
	// FileUtil.saveToFile("admins.txt", admins.values());
	// }
	//
	// public void addpatient(Patient patient) {
	// patients.put(patient.getId(), patient);
	// FileUtil.saveToFile("patients.txt", patients.values());
	// }
	//
	// public void addRecptionList(ReceptionList receptionList) {
	// receptionLists.put(receptionList.getId(), receptionList);
	// FileUtil.saveToFile("receptions.txt", receptionLists.values());
	// }
	//
	// public void removeRecptionList(int id) {
	// if (receptionLists.containsKey(id)) {
	// receptionLists.remove(id);
	// FileUtil.saveToFile("receptions.txt", receptionLists.values());
	// }
	// }
	//
	// public void addDocter(Doctor doctor) {
	// doctors.put(doctor.getId(), doctor);
	// FileUtil.saveToFile("doctors.txt", doctors.values());
	// }
	//
	// public void addAppoinment(Appoiment appoiment) {
	// appoiments.put(appoiment.getId(), appoiment);
	// FileUtil.saveToFile("appointments.txt", appoiments.values());
	// }
	//
	// public Patient getPatient(int patientId) {
	// return patients.get(patientId);
	// }
	//
	// public Doctor getDocter(int doctorId) {
	// return doctors.get(doctorId);
	// }
	//

}
