package com.project;

public class Patient {

    public static Patient temp;
    private static int orderNumber = 1;

    int id;
    int hm;
    int order;

    public Patient(int id, int hm) {
        this.id = id;
        this.hm = hm;
        this.order = orderNumber++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHm() {
        return hm;
    }

    public void setHm(int hm) {
        this.hm = hm;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", hm=" + hm +
                ", order=" + order +
                '}';
    }
}
