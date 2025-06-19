package management.reception;

import java.util.Map;

import base.Base;
import model.Patient;
import repository.Db;

public class ReceptionListController extends Base {

    Db dataBase = Db.getInstance();

    public ReceptionListController(ReceptionListView receptionListView) {
       
    }

    public void searchPatient(String name) {
        Map<Integer, Patient> patients = dataBase.getPatients();
        
        for (Map.Entry<Integer, Patient> patient : patients.entrySet()) {
            if (patient.getValue().getName().equalsIgnoreCase(name)) {
                System.out.println(patient.getValue());
            }
        }
    }

    public void addPatient(String name, byte age, String number, String date, String time, String gender, String address) {
        Patient patient = new Patient(name, number, convertStringToDate(date), time, age, gender, address);
        dataBase.addPatient(patient);
    }
}
