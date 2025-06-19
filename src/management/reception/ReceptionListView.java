package management.reception;

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
     controller.searchPatient(name);
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
     System.out.println("Enter Your time ");
       String name= scanner.nextLine();
       return name.trim();
  }
   private String getDate() {
      System.out.println("Enter Your date ");
       String name= scanner.nextLine();
       return name.trim();
  }
     private String getName() {
       System.out.println("Enter Your name ");
       String name= scanner.nextLine();
       return name.trim();
    }
      private byte getAge() {
       System.out.println("Enter Your age : ");
       byte age= Byte.parseByte(scanner.nextLine());
       return age;
    }
      private String getAddress() {
          System.out.println("Enter Your Address ");
          String adress= scanner.nextLine();
          return adress.trim();
       }
      private String getGender() {
          System.out.println("Enter Your Gender [M/F] : ");
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
