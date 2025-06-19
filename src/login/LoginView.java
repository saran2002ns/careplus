package login;

import java.util.Scanner;

import base.Base;
import repository.Db;

public class LoginView extends Base {
    private final Scanner scanner;
    private LoginControler controller ;
    public LoginView(){
        scanner= new Scanner(System.in);
        controller = new LoginControler(this);
    }
    public void init() {
      showDesk();
    }
    private void showDesk() {
        System.out.println(Db.getInstance().getDocters());
        while (true) {
              System.out.println("----- DESK -----");
              System.out.println();
              System.out.println("1.ADMIN LOGIN");
              System.out.println("2.RECEPTIONLIST LOGIN");
              System.out.println("3.EXIT");
              try {
                System.out.print("Enter your choice : ");
                int choice = scanner.nextInt();
                 scanner.nextLine();
                switch (choice) {
                    case 1:adminLogin();break;
                    case 2:receptionListLogin();break;
                    case 3:scanner.close(); exit();break;
                    default:break;
                }
              } catch (Exception e) {
                  scanner.nextLine();
                  System.out.println("Invalid input try again !");
              }
        }
    }
    private void receptionListLogin() {
       while (true) {
            System.out.println("---- Reception LOGIN ----");
            System.out.println();

            System.out.println("1.Login with your id");
            System.out.println("2.Login with your number");
            System.out.println("3.Return to hompage ");
            try {
                System.out.print("Enter your choice : ");
                int choice = scanner.nextInt();
                 scanner.nextLine();
                switch (choice) {
                    case 1:receptionListLoginWithId();break;
                    case 2:receptionListLoginWithNumber();break;
                    case 3:return;
                    default:break;
                }
              } catch (Exception e) {
                  scanner.nextLine();
                  System.out.println("Invalid input try again !");
              }
        }
    }
    private void receptionListLoginWithId() {
        System.out.println("Id : 1");
        System.out.println("Password: gokul123");
        int id= getId();
        scanner.nextLine();
        String password=getPassword();
        controller.receptionListPasswordCheckWithId(id,password);
    }
    private void receptionListLoginWithNumber() {
        System.out.println("Number : 8094250748");
        System.out.println("Password: gokul123");
        String number = getNumber();
        String password=getPassword();
        controller.receptionListPasswordCheckWithNumber(number,password);
    }
 
    private void adminLogin() {
        while (true) {
            System.out.println("---- ADMIN LOGIN ----");
            System.out.println();
            System.out.println("1.Login with your id");
            System.out.println("2.Login with your number");
            System.out.println("3.Return to hompage ");
            try {
                 System.out.print("Enter your choice : ");
                int choice = scanner.nextInt();
                 scanner.nextLine();
                switch (choice) {
                    case 1:adminLoginWithId();break;
                    case 2:adminLoginWithNumber();break;
                    case 3:return;
                    default:break;
                }
              } catch (Exception e) {
                  scanner.nextLine();
                  System.out.println("Invalid input try again !");
              }
        }
    }
    private void adminLoginWithId() {
        System.out.println("Id : 1");
        System.out.println("Password: saran123");
        int id= getId();
        scanner.nextLine();
        String password=getPassword();
        controller.adminPasswordCheckWithId(id,password);
    }
    private void adminLoginWithNumber() {
        System.out.println("Number : 7094250807 ");
        System.out.println("Password: saran123");
       String number = getNumber();
        String password=getPassword();
        controller.adminPasswordCheckWithNumber(number,password);
    }
    private String getPassword() {
       System.out.print("Enter Your password : ");
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

    private int getId() {
        int id=0;
        do{
            System.out.print("Enter the Id number : ");
            try {
             id = scanner.nextInt();
            break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid Id ");
               
            }
        }while(true);
        return id;
    }

}
