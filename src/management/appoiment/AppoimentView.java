package management.appoiment;

import java.util.Scanner;

import base.Base;

public class AppoimentView extends Base{
   private final Scanner scanner = new Scanner(System.in);
   private static AppoimentView view;
   private AppoimentControll controller;
   private AppoimentView(){
     controller= new AppoimentControll(this);
   }
   public static AppoimentView getInstance(){
      if (view==null) {
         view= new AppoimentView();
      }
      return view;
   }
   public void init(){
      list();
   }
    public void list() {
      while (true) {
          System.out.println("---- ADMIN -----");
       System.out.println();
       System.out.println("1.VIEW SHEWING FOR PATIENT");
       System.out.println("2.VIEW SHEWING FOR DOCTERS");
       System.out.println("3.VIEW SHEWING FOR A SPECFIC DOCTER");
       System.out.println("4.VIEW FREE FOR DOCTERS");
        System.out.println("5.Return");
        System.out.println("6.Exit");
        System.out.print("Enter your choice : ");

         try {
            int choice = scanner.nextInt();
            switch (choice) {
               case 1:controller.sheduledForPatient();break;
               case 2:controller.sheduledForDocters();break;
               case 3:controller.sheduledForDocter();break;
               case 4:controller.freeDocters();break;
               case 5:return;
               case 6:
                  this.scanner.close();
                  this.exit();
            }
         } catch (Exception choice) {
            this.scanner.nextLine();
            System.out.println("Invalid input try again !");
         }
      }
       
    }
   
}
