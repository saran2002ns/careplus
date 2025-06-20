package management.appoiment;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Appoiment;
import model.Doctor;
import repository.Db;

class AppoimentControll {
    Db dataBase = Db.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public AppoimentControll(AppoimentView appoimentView) {}

    public void sheduledForPatient() {
        String name = getName();
        Map<Integer, Appoiment> appoiments = dataBase.getAppoinments();
        boolean found = false;
        for (Map.Entry<Integer, Appoiment> entry : appoiments.entrySet()) {
            if (dataBase.getPatient(entry.getValue().getPatientId()).getName().equalsIgnoreCase(name)) {
                System.out.println(entry.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("NO APPOINTMENTS FOUND FOR THIS PATIENT.");
        }
    }

    public void sheduledForDocters() {
        Map<Integer, Appoiment> appoiments = dataBase.getAppoinments();
        if (appoiments.isEmpty()) {
            System.out.println("NO SCHEDULED APPOINTMENTS FOUND.");
            return;
        }
        for (Map.Entry<Integer, Appoiment> entry : appoiments.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void sheduledForDocter() {
        Map<Integer, Appoiment> appoiments = dataBase.getAppoinments();
        Map<Integer, Doctor> doctors = dataBase.getDocters();
        int id;
        while (true) {
            try {
                System.out.print("Enter doctor ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                if (!doctors.containsKey(id)) {
                    System.out.println("INVALID DOCTOR ID. PLEASE TRY AGAIN.");
                } else {
                    break;
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("INVALID INPUT. PLEASE ENTER A NUMBER.");
            }
        }
        boolean found = false;
        for (Map.Entry<Integer, Appoiment> entry : appoiments.entrySet()) {
            if (entry.getValue().getDocterId() == id) {
                System.out.println(entry.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("NO APPOINTMENTS FOUND FOR THIS DOCTOR.");
        }
    }

    public void freeDocters() {
        Map<Integer, Doctor> doctors = dataBase.getDocters();
        if (doctors.isEmpty()) {
            System.out.println("NO DOCTORS FOUND.");
            return;
        }
        for (Map.Entry<Integer, Doctor> doctorEntry : doctors.entrySet()) {
            Doctor doctor = doctorEntry.getValue();
            Map<LocalDate, List<String>> slots = doctor.getAvalabilSlot();
            if (!slots.isEmpty()) {
                System.out.print(doctor + " -> ");
                for (Map.Entry<LocalDate, List<String>> slot : slots.entrySet()) {
                    System.out.print("DATE: " + slot.getKey() + " | TIME: ");
                    for (String time : slot.getValue()) {
                        System.out.print(time + " | ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    private String getName() {
        System.out.print("Enter patient name: ");
        return scanner.nextLine().trim();
    }
}
