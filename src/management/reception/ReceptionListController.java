package management.reception;

import java.util.Map;

import base.Base;
import model.Patient;
import repository.Db;

public class ReceptionListController extends Base {

    Db dataBase = Db.getInstance();

    public ReceptionListController(ReceptionListView receptionListView) {
       
    }
    public void searchPatientByName(String name){
        dataBase.searchPatientByName(name);
    }
    public void addPatient(String name, byte age, String number, String date, String time, String gender, String address) {
        Patient patient = new Patient(name, number, convertStringToDate(date), time, age, gender, address);
        dataBase.addPatient(patient);
    }
}
