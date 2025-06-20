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

    private ReceptionListView() {
        controller = new ReceptionListController(this);
    }

    public static ReceptionListView getInstance() {
        if (view == null) {
            view = new ReceptionListView();
        }
        return view;
    }

    public void init() {
        desk();
    }

    private void desk() {
        while (true) {
            System.out.println();
            System.out.println("---- RECEPTION DESK ----\n");
            System.out.println("1. ADD PATIENT");
            System.out.println("2. SEARCH PATIENT");
            System.out.println("3. RETURN");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> addPatient();
                    case 2 -> searchPatient();
                    case 3 -> { return; }
                    default -> System.out.println("INVALID CHOICE! PLEASE TRY AGAIN.");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("INVALID INPUT! PLEASE ENTER A NUMBER.");
            }
        }
    }

    private void searchPatient() {
        String name = getName();
        controller.searchPatientByName(name);
    }

    private void addPatient() {
        String name = getName();
        byte age = getAge();
        String number = getNumber();
        String date = getDate();
        String time = getTime();
        String address = getAddress();
        String gender = getGender();

        controller.addPatient(name, age, number, date, time, gender, address);
    }

    private String getTime() {
        List<String> validTimes = Arrays.asList("9:00", "11:00", "14:00", "19:00");
        do {
            System.out.println("Choice should be: [9:00, 11:00, 14:00, 19:00]");
            System.out.print("Enter your preferred time: ");
            String input = scanner.nextLine().trim();
            if (validTimes.contains(input)) {
                return input;
            } else {
                System.out.println("INVALID TIME! PLEASE ENTER A VALID SLOT.");
            }
        } while (true);
    }

    private String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        do {
            System.out.println("Date format should be: dd-MM-yyyy");
            System.out.print("Enter the appointment date: ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate.parse(input, formatter);
                return input;
            } catch (Exception e) {
                System.out.println("INVALID DATE FORMAT! PLEASE TRY AGAIN.");
            }
        } while (true);
    }

    private String getName() {
        System.out.print("Enter patient name: ");
        return scanner.nextLine().trim();
    }

    private byte getAge() {
        while (true) {
            try {
                System.out.print("Enter patient age: ");
                return Byte.parseByte(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("INVALID AGE! PLEASE ENTER A NUMBER.");
            }
        }
    }

    private String getAddress() {
        System.out.print("Enter patient address: ");
        return scanner.nextLine().trim();
    }

    private String getGender() {
        System.out.print("Enter gender [M/F]: ");
        return scanner.nextLine().trim();
    }

    private String getNumber() {
        String number;
        do {
            System.out.print("Enter patient phone number: ");
            number = scanner.nextLine().trim();
            if (!number.matches("\\d{10}")) {
                System.out.println("INVALID NUMBER! PLEASE ENTER A 10-DIGIT NUMBER.");
            } else {
                break;
            }
        } while (true);
        return number;
    }
}
