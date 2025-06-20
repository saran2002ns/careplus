package management.appoiment;

import java.util.Scanner;
import base.Base;

public class AppoimentView extends Base {
    private final Scanner scanner = new Scanner(System.in);
    private static AppoimentView view;
    private AppoimentControll controller;

    private AppoimentView() {
        controller = new AppoimentControll(this);
    }

    public static AppoimentView getInstance() {
        if (view == null) {
            view = new AppoimentView();
        }
        return view;
    }

    public void init() {
        list();
    }

    public void list() {
        while (true) {
            System.out.println();
            System.out.println("---- SCHEDULING ----");
            System.out.println();
            System.out.println("1. VIEW SCHEDULING FOR PATIENT");
            System.out.println("2. VIEW SCHEDULING FOR DOCTORS");
            System.out.println("3. VIEW SCHEDULING FOR A SPECIFIC DOCTOR");
            System.out.println("4. VIEW FREE DOCTORS");
            System.out.println("5. RETURN");
            System.out.println("6. EXIT");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> controller.sheduledForPatient();
                    case 2 -> controller.sheduledForDocters();
                    case 3 -> controller.sheduledForDocter();
                    case 4 -> controller.freeDocters();
                    case 5 -> { return; }
                    case 6 -> {
                        scanner.close();
                        exit();
                    }
                    default -> System.out.println("INVALID CHOICE! PLEASE SELECT FROM 1 TO 6.");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("INVALID INPUT! PLEASE ENTER A NUMBER.");
            }
        }
    }
}
