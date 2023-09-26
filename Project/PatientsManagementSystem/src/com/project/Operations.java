package com.project;

public class Operations {
    static AVLTree idDb = new AVLTree();
    static AVLTree hmDB = new AVLTree();
    static AVLTree orderDB = new AVLTree();

    public static void insert(int id, int hm) {
        Patient newPatient = new Patient(id, hm);
        idDb.insert(newPatient, newPatient.getId());
        hmDB.insert(newPatient, newPatient.getHm());
        orderDB.insert(newPatient, newPatient.getOrder());
    }

    public static void serveFirst() {
        Patient foundPatient = orderDB.deleteMinimum();
        idDb.delete(foundPatient.getId(), foundPatient.getId());
        hmDB.delete(foundPatient.getId(), foundPatient.getHm());
        System.out.println(foundPatient.getId() + " " + foundPatient.getHm());
    }

    public static void serveSickest() {
        Patient foundPatient = hmDB.deleteMinimum();
        idDb.delete(foundPatient.getId(), foundPatient.getId());
        orderDB.delete(foundPatient.getId(), foundPatient.getOrder());
        System.out.println(foundPatient.getId() + " " + foundPatient.getHm());
    }

    public static void update(int id, int updatedHm) {
        idDb.delete(id, id);
        hmDB.delete(id, Patient.temp.getHm());
        orderDB.delete(id, Patient.temp.getOrder());
        Patient newPatient  = new Patient(id, updatedHm);
        newPatient.setOrder(Patient.temp.getOrder());
        idDb.insert(newPatient, newPatient.getId());
        hmDB.insert(newPatient, newPatient.getHm());
        orderDB.insert(newPatient, newPatient.getOrder());
    }

}
