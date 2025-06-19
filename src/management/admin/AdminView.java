package management.admin;

import java.util.Scanner;
import base.Base;
import management.appoiment.AppoimentView;

public class AdminView extends Base{
    private final Scanner scanner = new Scanner(System.in);
    private static AdminView view;
    private AdminController controller;
    private AdminView(){
        controller=new AdminController(this);
    }
    public static AdminView getInstance(){
        if(view==null){
            view = new AdminView();
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
            System.out.println("1.APPOINMENT SHEDULING");
            System.out.println("2.MANAGE RECEPTION LIST");
            System.out.println("3.ADD DOCTER");
            System.out.println("4.REMOVE DOCTER");
            System.out.println("5.VIEW APPOIMENTS");
            System.out.println("6.RETURN");
            System.out.println("7.EXIT");
            System.out.print("Enter your choice : ");

         try {
            int var1 = scanner.nextInt();
            scanner.nextLine();
            switch (var1) {
               case 1:controller.appoinmentSheduling();break;
               case 2:manageReceptionList();break;
               case 3:addDocter();break;
               case 4:removeDocter();break;
               case 5:AppoimentView.getInstance().init();
               case 6:return;
               case 7:
                  scanner.close();
                  exit();
            }
         } catch (Exception var2) {
            scanner.nextLine();
            System.out.println("Invalid input try again !");
         }
      }
      
    }
    
private void removeDocter() {
   controller.removeDocter();
		
		
}
private void manageReceptionList() {
      while (true) {
          System.out.println("---- MANAGE RECEPTION LIST -----");
       System.out.println();
       System.out.println("1.ADD RECEPTIONLIST");
       System.out.println("2.REMOVE RECEPTIONLIST");
        System.out.println("3.RETURN ");
        System.out.print("Enter your choice : ");

         try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
               case 1:addRecptionList();break;
               case 2:removeReceptionList();break;
               case 3:return;
            }
         } catch (Exception var2) {
            this.scanner.nextLine();
            System.out.println("Invalid input try again !");
         }
      }
        
    }
    private void removeReceptionList() {
      int id =getId();
      controller.removeReceptionList(id);
    }
    private int getId(){
      int id=0;
       while (true) {
           try {
            id= scanner.nextInt();
            break;
           } catch (Exception e) {
           System.out.println("Enter valid input ");
           }
        }
        return id;
    }
    private void addRecptionList() {
      
      String name = getName();
      String password=getPassword();
      String number = getNumber();
      controller.addToReceptionList(name,number,password);
      System.out.println(" ADDED SUCESSFULLY");
    }
    private void addDocter() {
    String name =getName();
    byte age =getAge();
    String number =getNumber();
    controller.addDocter(name,age,number);
   }
     private String getName() {
       System.out.println("Enter Your name ");
       String name= scanner.nextLine();
       return name.trim();
    }
      private String getPassword() {
       System.out.println("Enter Your password ");
       String password= scanner.nextLine();
       return password.trim();
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
    private byte getAge() {
       System.out.println("Enter Your age : ");
       byte age= Byte.parseByte(scanner.nextLine());
       return age;
    }
   
}
