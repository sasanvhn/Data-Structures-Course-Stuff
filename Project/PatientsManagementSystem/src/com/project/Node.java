package com.project;

public class Node {

    Patient patient;
    int key;
    AVLTree right;
    AVLTree left;

    public Node(Patient patient, int key) {
        this.patient = patient;
        this.key = key;
        this.right = null;
        this.left = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "patient=" + patient.toString() +
                '}';
    }
}
