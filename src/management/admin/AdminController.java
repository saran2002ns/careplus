package management.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Appoiment;
import model.Doctor;
import model.Patient;
import model.ReceptionList;
import repository.Db;

class AdminController {
    private final Scanner scanner = new Scanner(System.in);
    Db dataBase = Db.getInstance();

    public AdminController(AdminView adminView) { }

    public void appoinmentSheduling() {
        int patienId;
        Patient patient;
        do {
            boolean noPatients = printNonAnappoinmentPatient();
            if (noPatients) {
                System.out.println(" THERE ARE NO PATIENTS WITHOUT APPOINTMENTS.");
                return;
            }
            try {
                System.out.print("Enter Patient Id : ");
                patienId = scanner.nextInt();
                scanner.nextLine();
                patient = dataBase.getPatient(patienId);
                if (patient == null || patient.isAllocated()) {
                    System.out.println(" INVALID PATIENT DATA OR ALREADY ALLOCATED.");
                    System.out.println("ℹ TO EXIT, ENTER EXIT.");
                } else break;
            } catch (Exception e) {
                return;
            }
        } while (true);
        int docterId;
        Doctor docter;
        do {
            try {
                boolean noDoctors = printAvailableDocterForPatient(patient);
                if (noDoctors) {
                    System.out.println("NO DOCTOR AVAILABLE FOR THE PATIENT'S DATE.");
                    return;
                }
                System.out.print("ENTER DOCTOR ID: ");
                docterId = scanner.nextInt();
                scanner.nextLine();
                docter = dataBase.getDocterById(docterId);
                if (docter == null || !lookAvailability(patient, docter)) {
                    System.out.println("NO MATCHING DOCTOR FOUND FOR SELECTED DATE/TIME.");
                    System.out.println(" TO EXIT, ENTER EXIT.");
                } else break;
            } catch (Exception e) {
                return;
            }
        } while (true);
    }

    private boolean lookAvailability(Patient patient, Doctor docter) {
        Map<LocalDate, List<String>> avalabilSlot = docter.getAvalabilSlot();
        if (avalabilSlot.containsKey(patient.getDate())) {
            List<String> times = avalabilSlot.get(patient.getDate());
            for (String time : times) {
                if (time.equals(patient.getTime())) {
                    patient.setAllocated(true);
                    times.remove(time);
                    if (times.isEmpty()) {
                        avalabilSlot.remove(patient.getDate());
                    }
                    addAppoiment(patient.getId(), docter.getId(), time, patient.getDate());
                    System.out.println(" APPOINTMENT ADDED SUCCESSFULLY!");
                    return true;
                }
            }
        }
        return false;
    }

    private void addAppoiment(Integer patientId, Integer docterId, String time, LocalDate date) {
        dataBase.addAppoinment(new Appoiment(date, time, patientId, docterId));
    }

    private boolean printAvailableDocterForPatient(Patient patient) {
        Map<Integer, Doctor> docters = dataBase.getDocters();
        boolean noDoctor = true;
        for (Map.Entry<Integer, Doctor> entry : docters.entrySet()) {
            if (entry.getValue().getAvalabilSlot().containsKey(patient.getDate())) {
                System.out.println(entry.getValue());
                noDoctor = false;
            }
        }
        return noDoctor;
    }

    private boolean printNonAnappoinmentPatient() {
        Map<Integer, Patient> patients = dataBase.getPatients();
        boolean noPatient = true;
        for (Map.Entry<Integer, Patient> entry : patients.entrySet()) {
            if (!entry.getValue().isAllocated()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
                noPatient = false;
            }
        }
        return noPatient;
    }

    public void removeReceptionList(int id) {
        if (dataBase.getReceptionLists().containsKey(id)) {
            dataBase.getReceptionLists().remove(id);
            System.out.println(" RECEPTIONIST REMOVED SUCCESSFULLY!");
        } else {
            System.out.println("RECEPTIONIST ID NOT FOUND!");
        }
    }

    public void addToReceptionList(String name, String number, String password) {
        dataBase.addRecptionList(new ReceptionList(name, number, password));
        System.out.println("RECEPTIONIST ADDED SUCCESSFULLY!");
    }

    public void addDocter(String name, byte age, String number) {
        dataBase.addDocter(new Doctor(name, age, number));
        System.out.println("DOCTOR ADDED SUCCESSFULLY!");
    }

    public void removeDocter() {
        int docterId;
        Doctor docter;
        do {
            try {
                boolean noDoctor = printDocters(dataBase.getDocters());
                if (noDoctor) {
                    System.out.println(" THERE ARE NO DOCTORS TO REMOVE !");
                    return;
                }
                System.out.print("Enter Doctor Id: ");
                docterId = scanner.nextInt();
                scanner.nextLine();
                docter = dataBase.getDocterById(docterId);
                if (docter == null) {
                    System.out.println(" DOCTOR ID NOT FOUND.");
                    System.out.println("ℹ TO EXIT, ENTER EXIT.");
                    return;
                } else break;
            } catch (Exception e) {
                return;
            }
        } while (true);
        dataBase.removeDocterById(docterId);
        System.out.println("✅ DOCTOR REMOVED SUCCESSFULLY!");
    }

    private boolean printDocters(Map<Integer, Doctor> docters) {
        if (docters.isEmpty()) {
            return true;
        }
        for (Map.Entry<Integer, Doctor> entry : docters.entrySet()) {
            System.out.println(entry.getValue());
        }
        return false;
    }

    public boolean printreceptionlist() {
        Map<Integer, ReceptionList> receptionLists = dataBase.getReceptionLists();
        if (receptionLists.isEmpty()) {
            System.out.println("ℹ️ NO RECEPTIONISTS AVAILABLE.");
            return true;
        }
        for (Map.Entry<Integer, ReceptionList> entry : receptionLists.entrySet()) {
            System.out.println(entry);
        }
        return false;
    }

    public boolean searchReceptionList(int id) {
        ReceptionList receptionList = dataBase.getReceptionListById(id);
        return receptionList != null;
    }

    public void removeReceptionListById(int id) {
        dataBase.removeRecptionListById(id);
        System.out.println(" RECEPTIONIST REMOVED SUCCESSFULLY!");
    }
}
