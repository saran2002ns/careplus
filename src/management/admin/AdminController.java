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
//    private AdminView view;
    Db dataBase = Db.getInstance();
    public AdminController(AdminView adminView) {
//        view=adminView;
    }
    public void appoinmentSheduling() {
        int patienId;
        do{
            printNonAnappoinmentPatient();
            System.out.print("Enter patient id : ");
            patienId=scanner.nextInt();
            scanner.nextLine();
            if (!dataBase.getPatients().containsKey(patienId) ||dataBase.getPatients().get(patienId).isAllocated()) {
                System.out.println("Invalid data ");
            }else break;
        }while(true);
        int docterId;
         do{   
            printAvailableDocterForPatient(patienId);  
            System.out.print("Enter Docter id : ");
            docterId=scanner.nextInt();
            scanner.nextLine();
            if (!dataBase.getDocters().containsKey(docterId)|| !lookAvailability(dataBase.getPatients().get(patienId),dataBase.getDocters().get(docterId))) {
                System.out.println("NO DOCTER ID FOUND!");
            }else break;
         }while (true);
            
    }
    private boolean lookAvailability(Patient patient, Doctor docter) {
         Map<LocalDate,List<String>> avalabilSlot = docter.getAvalabilSlot();
         if (avalabilSlot.containsKey(patient.getDate())) {
            List<String> times = avalabilSlot.get(patient.getDate());
            for (String time : times) {
                if (time.equals(patient.getTime())) {
                    patient.setAllocated(true);
                    times.remove(time);
                    if (times.isEmpty()) {
                        avalabilSlot.remove(patient.getDate());
                    }
                    addAppoiment(patient.getId(),docter.getId(),time,patient.getDate());
                    System.out.println("SuccessFully Added");
                    return true;
                }
            }
         }
       return false;
    }
    private void addAppoiment(Integer patientId, Integer docterId, String time, LocalDate date) {
    	dataBase.setAppoinment(patientId,docterId,time,date);
       dataBase.addAppoinment(new Appoiment(date, time, patientId, docterId));
    }
    private void printAvailableDocterForPatient(int patienId) {
       Map<Integer,Doctor> docters = dataBase.getDocters();
       for(Map.Entry<Integer,Doctor> docter : docters.entrySet()){
           if (docter.getValue().getAvalabilSlot().containsKey(dataBase.getPatients().get(patienId).getDate())) {
             System.out.println(docter);
           }
       }
    }
    private void printNonAnappoinmentPatient() {
        Map<Integer,Patient> patients = dataBase.getPatients();
        for ( Map.Entry<Integer,Patient> patientEntry : patients.entrySet()) {
            if(!patientEntry.getValue().isAllocated())
            System.out.println(patientEntry.getKey()+" : "+patientEntry.getValue());
        }
    }
    public void removeReceptionList(int id) {
        if(dataBase.getReceptionLists().containsKey(id)){
            dataBase.getReceptionLists().remove(id);
            System.out.println("Remove Successfully ! ");
        }else{
            System.out.println("NO ID FOUND !");
        }
    }
    public void addToReceptionList(String name, String number, String password) {
      dataBase.addRecptionList(new ReceptionList(name, number, password));
    }
    public void addDocter(String name, byte age, String number) {
        dataBase.addDocter(new Doctor(name, age, number));
    }
    public void removeDocter() {
        do{   
            printDocters(dataBase.getDocters());
            System.out.print("Enter Docter id : ");
            int  docterId=scanner.nextInt();
            scanner.nextLine();
            
            if (!dataBase.getDocters().containsKey(docterId)) {
                System.out.println("NO DOCTER_ID FOUND!");
            }else break;
         }while (true);

    }
    private void printDocters(Map<Integer ,Doctor> docters) {
        for (Map.Entry<Integer,Doctor>   docter : docters.entrySet()) {
            System.out.println(docter.getValue());
        }
    }
 
    
}
