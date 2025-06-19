package login;

import java.util.Map;

import management.admin.AdminView;
import management.reception.ReceptionListView;
import model.Admin;
import model.ReceptionList;
import repository.Db;

class LoginControler {
    Db dataBase = Db.getInstance();
//    private LoginView view;
    public LoginControler(LoginView loginView) {
//        view=loginView;
    }
    public void receptionListPasswordCheckWithId(int id, String password) {
        Map<Integer,ReceptionList> receptionLists =dataBase.getReceptionLists();
        if(receptionLists.containsKey(id)){
            if (receptionLists.get(id).getPassword().equals(password)) {
                ReceptionListView.getInstance().init();
            }else{
                System.out.println("INVALID PASSWORD ! ");
            }
        }else{
            System.out.println("INVALID ID ! ");
        }
    }
    public void receptionListPasswordCheckWithNumber(String number, String password) {
        Map<Integer,ReceptionList> receptionLists =dataBase.getReceptionLists();
        for (Map.Entry<Integer,ReceptionList> receptionList : receptionLists.entrySet()) {
            if(receptionList.getValue().getNumber().equals(number) && receptionList.getValue().getPassword().equals(password) ){
                ReceptionListView.getInstance().init();
                return;
            }
        }
        System.out.println("INVALID DATA TRY AGAIN !");
 
    }
    public void adminPasswordCheckWithId(int id, String password) {
         Map<Integer,Admin> admin =dataBase.getAdmin();
        if(admin.containsKey(id)){
            if (admin.get(id).getPassword().equals(password)) {
                AdminView.getInstance().init();
            }else{
                System.out.println("INVALID PASSWORD ! ");
            }
        }else{
            System.out.println("INVALID ID ! ");
        }
    }
    public void adminPasswordCheckWithNumber(String number, String password) {
        Map<Integer,Admin> admins =dataBase.getAdmin();
        for (Map.Entry<Integer,Admin> admin : admins.entrySet()) {
            if(admin.getValue().getNumber().equals(number) && admin.getValue().getPassword().equals(password)){
                AdminView.getInstance().init();
                return;
            }
        }
         System.out.println("INVALID DATA TRY AGAIN !");
    }
    
}
