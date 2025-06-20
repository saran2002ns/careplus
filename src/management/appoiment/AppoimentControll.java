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
        String name =getName();
        Map<Integer,Appoiment> appoiments = dataBase.getAppoinments();
        for (Map.Entry<Integer,Appoiment> appoimentEntry : appoiments.entrySet()) {
            if (dataBase.getPatient(appoimentEntry.getValue().getPatientId()).getName().equals(name)) {
                 System.out.println(appoimentEntry.getValue());
            }
           
        }
    }

    public void sheduledForDocters() {
        Map<Integer,Appoiment> appoiments = dataBase.getAppoinments();
        for (Map.Entry<Integer,Appoiment> appoimentEntry : appoiments.entrySet()) {
            System.out.println(appoimentEntry.getValue());
        }
    }

    public void sheduledForDocter() {
        
       Map<Integer,Appoiment> appoiments = dataBase.getAppoinments();
       Map<Integer,Doctor> docters = dataBase.getDocters();
       int id=0;
       while (true) {
                  System.out.print("Enter id : ");
                  id = scanner.nextInt();
                  if (!docters.containsKey(id)) {
                    System.out.println(" INVALID ID ! ");
                  }else break;
       }
        for (Map.Entry<Integer,Appoiment> appoimentEntry : appoiments.entrySet()) {
           if (id==appoimentEntry.getValue().getDocterId()) 
            System.out.println(appoimentEntry.getValue());
        }
       
    }

    public void freeDocters() {
        Map<Integer,Doctor> docters=dataBase.getDocters();
        for(Map.Entry<Integer,Doctor> docter : docters.entrySet()){
            System.out.print(docter+" -> ");
            Map<LocalDate,List<String>> slots =docter.getValue().getAvalabilSlot();
            for(Map.Entry<LocalDate,List<String>> slot : slots.entrySet() ){
                System.out.print("date : "+slot.getKey()+" time : ");
                for (String time : slot.getValue()) {
                    System.out.print(time+"  ");
                }
            }
        }
    }

    private String getName() {
       System.out.print("Enter Your name : ");
       String name= scanner.nextLine();
       return name.trim();
    }
    
}
