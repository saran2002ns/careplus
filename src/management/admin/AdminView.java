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
            System.out.println();
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
          System.out.println();
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
      int id;
       do{ 
            try {
               
              boolean bool=controller.printreceptionlist();
              if (bool) {
               System.out.println("No ReceptionList Found! ");
               return;
              }
              System.out.print("Enter id : ");
                id= scanner.nextInt();
                
               if (!controller.searchReceptionList(id)) {
                  System.out.println("IN VALID ID !");
                  System.out.println(" IF YOU WANT TO EXIT ENTER EXIT ! ");
               }else break;
              
            } catch (Exception e) {
               scanner.nextLine();
               System.out.println("Invalid data");
               return;
            }
            
        }while(true);
         controller.removeReceptionListById(id);
         scanner.nextLine();
    }
    
    private void addRecptionList() {
      String name = getName();
      String password=getPassword();
      String number = getNumber();
      controller.addToReceptionList(name,number,password);
      System.out.println(" ADDED SUCESSFULLY");
    }
    private void addDocter() {
      System.out.println("-------- ADD DOCTOR --------");
      System.out.println();
      String name =getName();
      byte age =getAge();
      String number =getNumber();
      controller.addDocter(name, age, number);
   }
     private String getName() {
       System.out.print("Enter Your name : ");
       String name= scanner.nextLine();
       return name.trim();
    }
      private String getPassword() {
       System.out.print("Enter Your password :  ");
       String password= scanner.nextLine();
       return password.trim();
    }

    private String getNumber() {
        String number="";
        do{
            System.out.print("Enter Your number : ");
            number=scanner.nextLine();
            if(!number.matches("\\d{10}")){
                System.out.println("Not a number  put a 10 digit number " );
            }else break;
        }while(true);
        return number;
    }
    private byte getAge() {
      byte age;
      do{
         try {
         System.out.print("Enter Your age : ");
         age=(byte) Byte.parseByte(scanner.nextLine());
         break;
         } catch (Exception e) {
            System.out.println("invalid data ! ");
         }
      }while(true);
       return age;
    }
   
}
