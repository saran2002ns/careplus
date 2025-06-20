package management.reception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReceptionListView {
    public static ReceptionListView view;
    private ReceptionListController controller;
    private final Scanner scanner = new Scanner(System.in);
    private ReceptionListView(){
        controller=new ReceptionListController(this);
    }
    public static ReceptionListView getInstance(){
        if(view==null){
            view = new ReceptionListView();
        }
        return view;
    }
    public void init(){
       desk();
    }
    private void desk() {
        while (true) {
       System.out.println("---- ADMIN -----");
       System.out.println();
       System.out.println("1.Add Patient");
       System.out.println("2.Search Patient");
        System.out.println("3.return");
        System.out.print("Enter your choice : ");

         try {
            int var1 = scanner.nextInt();
            scanner.nextLine();
            switch (var1) {
               case 1:addPatient();break;
               case 2:searchPatient();break;
               case 3:
                 return;
            }
         } catch (Exception var2) {
            this.scanner.nextLine();
            System.out.println("Invalid input try again !");
         }
      }
    }
    private void searchPatient() {
     String name = getName();
     controller.searchPatientByName(name);
   }
   private void addPatient() {
        String name =getName();
        byte age =getAge();
        String number =getNumber();
        String date = getDate();
        String time = getTime();
        String address = getAddress();
        String gender = getGender();
        controller.addPatient(name,age,number,date,time,gender,address);
   }
    private String getTime() {
        List<String> validTimes = Arrays.asList("9:00", "11:00", "14:00", "19:00");
        do {
            System.out.println("Choice should be: [ 9 || 11 || 14 || 19 ]");
            System.out.print("Enter your time: ");
            String input = scanner.nextLine().trim();
            if (validTimes.contains(input)) {
                return input;
            } else {
                System.out.println("Invalid time. Please enter one of the valid options.");
            }
        }while (true);
    }

    private String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         do{
            System.out.println("Date should be in the format: dd-MM-yyyy");
            System.out.print("Enter your date: ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate.parse(input, formatter); 
                return input;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }while (true);
    }
     private String getName() {
       System.out.print("Enter Your name : ");
       String name= scanner.nextLine();
       return name.trim();
    }
      private byte getAge() {
       System.out.print("Enter Your age : ");
       byte age= Byte.parseByte(scanner.nextLine());
       return age;
    }
      private String getAddress() {
          System.out.print("Enter Your Address ");
          String adress= scanner.nextLine();
          return adress.trim();
       }
      private String getGender() {
          System.out.print("Enter Your Gender [M/F] : ");
          String gender= scanner.nextLine();
          return gender.trim();
       }

    private String getNumber() {
        String number="";
        do{
            System.out.print("Enter Your number :");
            number=scanner.nextLine();
            if(!number.matches("\\d{10}")){
                System.out.println("Not a number  put a 10 digit number " );
            }else break;
        }while(true);
        return number;
    }
}
