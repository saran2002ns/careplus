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
        Patient patient;
        do{
           boolean bool= printNonAnappoinmentPatient();
            if (bool) {
                System.out.println(" there is no patient");
                return;
            }
            try {
                System.out.print("Enter patient id : ");
                patienId=scanner.nextInt();
                scanner.nextLine();
                patient= dataBase.getPatient(patienId);
                if (patient==null || patient.isAllocated()) {
                System.out.println("Invalid data ");
                System.out.println(" IF YOU WANT TO EXIT ENTER EXIT ! ");
                }else break;
            } catch (Exception e) {
               return;
            }
            
        }while(true);
        int docterId;
        Doctor docter;
         do{   
             try {
                   boolean bool= printAvailableDocterForPatient(patient);  
                   if (bool) {
                    System.out.println(" no doctor available ");
                    return;
                   }
                    System.out.print("Enter Docter id : ");
                    docterId=scanner.nextInt();
                    scanner.nextLine();
                    docter=dataBase.getDocterById(docterId);
                    if (docter==null| !lookAvailability(patient,docter)) {
                        System.out.println("NO MATCHING DATA PLEASE TRY AGAIN !");
                        System.out.println(" IF YOU WANT TO EXIT ENTER EXIT ! ");
                    }else break;
             } catch (Exception e) {
               return;
            }
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
       dataBase.addAppoinment(new Appoiment(date, time, patientId, docterId));
    }
    private boolean printAvailableDocterForPatient(Patient patient) {
       Map<Integer,Doctor> docters = dataBase.getDocters();
      boolean bool = true;
       for(Map.Entry<Integer,Doctor> docter : docters.entrySet()){
           if (docter.getValue().getAvalabilSlot().containsKey(patient.getDate())) {
             System.out.println(docter);
             bool = false;
           }
       }
       return bool;
    }
    private boolean printNonAnappoinmentPatient() {
        Map<Integer,Patient> patients = dataBase.getPatients();
       boolean bool=true;
        for ( Map.Entry<Integer,Patient> patientEntry : patients.entrySet()) {
            if(!patientEntry.getValue().isAllocated()){
            System.out.println(patientEntry.getKey()+" : "+patientEntry.getValue());
            bool=false;
            }
        }
        return bool;
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
        int docterId;
        Doctor docter;
         
         do{   
             try {
                   boolean bool=printDocters(dataBase.getDocters());
                   if (bool) {
                    System.out.println("there is no doctor");
                   }
                    System.out.print("Enter Docter id : ");
                    docterId=scanner.nextInt();
                    scanner.nextLine();
                    docter=dataBase.getDocterById(docterId);
                    if (docter==null) {
                        System.out.println("NO DOCTER ID FOUND!");
                        System.out.println(" IF YOU WANT TO EXIT ENTER EXIT ! ");
                        return;
                    }else break;
             } catch (Exception e) {
               return;
            }
         }while (true);
         dataBase.removeDocterById(docterId);

    }
   
    private boolean printDocters(Map<Integer ,Doctor> docters) {
        if (docters.isEmpty()) {
            return true;
        }
        for (Map.Entry<Integer,Doctor>   docter : docters.entrySet()) {
            System.out.println(docter.getValue());
        }
        return false;
    }

    public boolean  printreceptionlist() {
       
       Map<Integer,ReceptionList> receptionLists = dataBase.getReceptionLists();
        if (receptionLists.isEmpty()) {
            return true;
        }
       for(  Map.Entry<Integer,ReceptionList> receptionList : receptionLists.entrySet()){
          System.out.println(receptionList);
       }
       return false;
    }

    public boolean searchReceptionList(int id) {
       ReceptionList receptionList =dataBase.getReceptionListById(id);
       return receptionList!=null;
    }

    public void removeReceptionListById(int id) {
        dataBase.removeRecptionListById(id);
    }

   
 
    
}
