package login;

import java.util.Map;
import management.admin.AdminView;
import management.reception.ReceptionListView;
import model.Admin;
import model.ReceptionList;
import repository.Db;

class LoginController {
    Db dataBase = Db.getInstance();

    public LoginController(LoginView loginView) {}

    public void receptionListPasswordCheckWithId(int id, String password) {
        Map<Integer, ReceptionList> receptionLists = dataBase.getReceptionLists();
        ReceptionList reception = receptionLists.get(id);
        if (reception != null) {
            if (reception.getPassword().equals(password)) {
                ReceptionListView.getInstance().init();
            } else {
                System.out.println("INVALID PASSWORD!");
            }
        } else {
            System.out.println("INVALID ID!");
        }
    }

    public void receptionListPasswordCheckWithNumber(String number, String password) {
        Map<Integer, ReceptionList> receptionLists = dataBase.getReceptionLists();
        boolean isValid = false;
        for (ReceptionList reception : receptionLists.values()) {
            if (reception.getNumber().equals(number) && reception.getPassword().equals(password)) {
                ReceptionListView.getInstance().init();
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            System.out.println("INVALID NUMBER OR PASSWORD. TRY AGAIN!");
        }
    }

    public void adminPasswordCheckWithId(int id, String password) {
        Map<Integer, Admin> admins = dataBase.getAdmin();
        Admin admin = admins.get(id);
        if (admin != null) {
            if (admin.getPassword().equals(password)) {
                AdminView.getInstance().init();
            } else {
                System.out.println("INVALID PASSWORD!");
            }
        } else {
            System.out.println("INVALID ID!");
        }
    }

    public void adminPasswordCheckWithNumber(String number, String password) {
        Map<Integer, Admin> admins = dataBase.getAdmin();
        boolean isValid = false;
        for (Admin admin : admins.values()) {
            if (admin.getNumber().equals(number) && admin.getPassword().equals(password)) {
                AdminView.getInstance().init();
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            System.out.println("INVALID NUMBER OR PASSWORD. TRY AGAIN!");
        }
    }
}
