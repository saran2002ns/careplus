package login;

import java.util.Scanner;
import base.Base;

public class LoginView extends Base {
    private final Scanner scanner;
    private final LoginController controller;

    public LoginView() {
        scanner = new Scanner(System.in);
        controller = new LoginController(this);
    }

    public void init() {
        showDesk();
    }

    private void showDesk() {
        while (true) {
            System.out.println("----- DESK -----\n");
            System.out.println("1.ADMIN LOGIN");
            System.out.println("2.RECEPTIONLIST LOGIN");
            System.out.println("3.EXIT");
            System.out.print("Enter Your Choice:: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1 -> adminLogin();
                    case 2 -> receptionListLogin();
                    case 3 -> {
                        scanner.close();
                        exit();
                    }
                    default -> System.out.println("INVALID CHOICE PLEASE TRY AGAIN !");
                }
            } catch (Exception e) {
                scanner.nextLine(); 
                System.out.println("INVALID INPUT. PLEASE ENTER A NUMBER!");
            }
        }
    }

    private void receptionListLogin() {
        while (true) {
            System.out.println("---- RECEPTION LOGIN ----\n");
            System.out.println("1.LOGIN WITH YOUR ID");
            System.out.println("2.LOGIN WITH YOUR NUMBER");
            System.out.println("3.RETURN TO HOMEPAGE");
            System.out.print("Enter Your Choice:: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> receptionListLoginWithId();
                    case 2 -> receptionListLoginWithNumber();
                    case 3 -> { return; }
                    default -> System.out.println("INVALID CHOICE..! PLEASE TRY AGAIN!");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("INVALID INPUT..! PLEASE ENTER A NUMBER ");
            }
        }
    }

    private void receptionListLoginWithId() {
        System.out.println("Id : 1");
        System.out.println("Password: gokul123");
        int id = getId();
        scanner.nextLine(); 
        String password = getPassword();
        controller.receptionListPasswordCheckWithId(id, password);
    }

    private void receptionListLoginWithNumber() {
        System.out.println("Number : 8094250748");
        System.out.println("Password: gokul123");
        String number = getNumber();
        String password = getPassword();
        controller.receptionListPasswordCheckWithNumber(number, password);
    }
    private void adminLogin() {
        while (true) {
            System.out.println("---- ADMIN LOGIN ----\n");
            System.out.println("1.LOGIN WITH YOUR ID");
            System.out.println("2.LOGIN WITH YOUR NUMBER");
            System.out.println("3.RETURN TO HOMEPAGE");
            System.out.print("Enter Your Choice:: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> adminLoginWithId();
                    case 2 -> adminLoginWithNumber();
                    case 3 -> { return; }
                    default -> System.out.println("INVALID CHOICE..! PLEASE TRY AGAIN ");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("INVALID INPUT..! PLEASE ENTER A NUMBER ");
            }
        }
    }
    private void adminLoginWithId() {
        System.out.println("Id : 1");
        System.out.println("Pasword: saran123");
        int id = getId();
        scanner.nextLine();
        String password = getPassword();
        controller.adminPasswordCheckWithId(id, password);
    }
    private void adminLoginWithNumber() {
        System.out.println("Number : 7094250807");
        System.out.println("Password: saran123");
        String number = getNumber();
        String password = getPassword();
        controller.adminPasswordCheckWithNumber(number, password);
    }
    private String getPassword() {
        System.out.print("Enter Your Password : ");
        return scanner.nextLine().trim();
    }
    private String getNumber() {
        String number;
        while (true) {
            System.out.print("Enter Your Number : ");
            number = scanner.nextLine().trim();
            if (!number.matches("\\d{10}")) {
                System.out.println(" INVALID NUMBER. PLEASE ENTER A 10-DIGIT NUMBER.");
            } else {
                break;
            }
        }
        return number;
    }
    private int getId() {
        while (true) {
            System.out.print("Enter Your Id: ");
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("INVALID ID! PLEASE ENTER A VALID NUMBER");
            }
        }
    }
}
