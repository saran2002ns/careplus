package management.admin;

import java.util.Scanner;
import base.Base;
import management.appoiment.AppoimentView;

public class AdminView extends Base {
    private final Scanner scanner = new Scanner(System.in);
    private static AdminView view;
    private AdminController controller;

    private AdminView() {
        controller = new AdminController(this);
    }

    public static AdminView getInstance() {
        if (view == null) {
            view = new AdminView();
        }
        return view;
    }

    public void init() {
        desk();
    }

    private void desk() {
        while (true) {
            System.out.println();
            System.out.println("---- ADMIN ----");
            System.out.println();
            System.out.println("1. APPOINTMENT SCHEDULING");
            System.out.println("2. MANAGE RECEPTION LIST");
            System.out.println("3. ADD DOCTOR");
            System.out.println("4. REMOVE DOCTOR");
            System.out.println("5. VIEW APPOINTMENTS");
            System.out.println("6. RETURN");
            System.out.println("7. EXIT");
            System.out.print("Enter your choice: ");

            try {
                int var1 = scanner.nextInt();
                scanner.nextLine();
                switch (var1) {
                    case 1 -> controller.appoinmentSheduling();
                    case 2 -> manageReceptionList();
                    case 3 -> addDocter();
                    case 4 -> removeDocter();
                    case 5 -> AppoimentView.getInstance().init();
                    case 6 -> { return; }
                    case 7 -> {
                        scanner.close();
                        exit();
                    }
                    default -> System.out.println("INVALID CHOICE! PLEASE TRY AGAIN.");
                }
            } catch (Exception var2) {
                scanner.nextLine();
                System.out.println("INVALID INPUT! PLEASE ENTER A NUMBER.");
            }
        }
    }

    private void removeDocter() {
        controller.removeDocter();
    }

    private void manageReceptionList() {
        while (true) {
            System.out.println();
            System.out.println("---- MANAGE RECEPTION LIST ----");
            System.out.println();
            System.out.println("1. ADD RECEPTIONIST");
            System.out.println("2. REMOVE RECEPTIONIST");
            System.out.println("3. RETURN");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> addRecptionList();
                    case 2 -> removeReceptionList();
                    case 3 -> { return; }
                    default -> System.out.println("INVALID CHOICE! PLEASE TRY AGAIN.");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("INVALID INPUT! PLEASE ENTER A NUMBER.");
            }
        }
    }

    private void removeReceptionList() {
        int id;
        do {
            try {
                boolean bool = controller.printreceptionlist();
                if (bool) {
                    System.out.println("NO RECEPTIONIST FOUND!");
                    return;
                }
                System.out.print("Enter receptionist ID: ");
                id = scanner.nextInt();
                if (!controller.searchReceptionList(id)) {
                    System.out.println("INVALID ID!");
                    System.out.println("TO EXIT, ENTER EXIT.");
                } else break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("INVALID DATA!");
                return;
            }
        } while (true);
        controller.removeReceptionListById(id);
        scanner.nextLine();
    }

    private void addRecptionList() {
        String name = getName();
        String password = getPassword();
        String number = getNumber();
        controller.addToReceptionList(name, number, password);
        System.out.println("RECEPTIONIST ADDED SUCCESSFULLY!");
    }

    private void addDocter() {
        System.out.println("---- ADD DOCTOR ----");
        System.out.println();
        String name = getName();
        byte age = getAge();
        String number = getNumber();
        controller.addDocter(name, age, number);
        System.out.println(" DOCTOR ADDED SUCCESSFULLY!");
    }

    private String getName() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        return name.trim();
    }

    private String getPassword() {
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return password.trim();
    }

    private String getNumber() {
        String number = "";
        do {
            System.out.print("Enter 10-digit number: ");
            number = scanner.nextLine();
            if (!number.matches("\\d{10}")) {
                System.out.println("Invalid number. Please Enter  10-Digit Number.");
            } else break;
        } while (true);
        return number;
    }

    private byte getAge() {
        byte age;
        do {
            try {
                System.out.print("Enter age: ");
                age = (byte) Byte.parseByte(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid age! Please enter a number.");
            }
        } while (true);
        return age;
    }
}
