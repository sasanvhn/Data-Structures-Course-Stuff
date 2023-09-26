package com.project;

import static java.lang.Integer.max;

public class AVLTree {

    private Node node;
    private int height;
    private int balance;

    public AVLTree() {
        this.node = null;
        this.height = -1;
        this.balance = 0;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private boolean isLeaf() {
        return this.height == 0;
    }


    public void insert(Patient patient, int key) {
        Node tree = this.node;

        Node newNode = new Node(patient,key);

        if (tree == null) {
            this.node = newNode;
            this.node.left = new AVLTree();
            this.node.right = new AVLTree();
        } else if (key <= tree.key) {
            this.node.left.insert(patient,key);
        } else {
            this.node.right.insert(patient, key);
        }
        reBalance();
    }

    private void reBalance() {

        this.updateHeights(false);
        this.updateBalances(false);

        while ((balance < -1) || balance > 1) {
            if (balance > 1) {
                if (node.left.balance < 0) {
                    node.left.leftRotate();
                    updateHeights();
                    updateBalances();
                }
                rightRotate();
                updateHeights();
                updateBalances();
            }

            if (balance < -1) {
                if (node.right.balance > 0) {
                    node.right.rightRotate();
                    updateHeights();
                    updateBalances();
                }
                leftRotate();
                updateHeights();
                updateBalances();
            }
        }
    }


    private void rightRotate() {
        Node A = node;
        Node B = node.left.node;
        Node T = B.right.node;

        node = B;
        B.right.node = A;
        A.left.node = T;
    }


    private void leftRotate() {
        Node A = node;
        Node B = node.right.node;
        Node T = B.left.node;

        node = B;
        B.left.node = A;
        A.right.node = T;
    }

    private void updateHeights() {
        updateHeights(true);
    }


    private void updateHeights(Boolean state) {
        if (node != null) {
            if (state) {
                if (node.left != null) {
                    node.left.updateHeights();
                }
                if (node.right != null) {
                    node.right.updateHeights();
                }
            }
            height = max(node.left.height, node.right.height) + 1;
        }
        else {
            height = -1;
        }
    }


    private void updateBalances() {
        updateBalances(true);
    }

    private void updateBalances(boolean state) {
        if (node != null) {
            if (state) {
                if (node.left != null) {
                    node.left.updateBalances();
                }
                if (node.right != null) {
                    node.right.updateBalances();
                }
            }
            balance = node.left.height - node.right.height;
        }
        else {
            balance = 0;
        }
    }

    public void delete(int id, int key) {
        delete(id, key, true);
    }


    private void delete(int id, int key, boolean flag) {
        if (node != null) {
            if ((node.key == key) && (node.patient.id == id)) {
                if (flag) {
                    Patient.temp = node.patient;
                }
                if ((node.left.node == null) && (node.right.node == null) ) {
                    node = null;
                } else if (node.left.node == null) {
                    node = node.right.node;
                } else if (node.right.node == null) {
                    node = node.left.node;
                } else {
                    Node replacement = logicalSuccessor(node);
                    if (replacement != null) {
                        node.key = replacement.key;
                        node.patient = replacement.patient;
                        node.right.delete(replacement.patient.id,replacement.key, false); // this
                    }
                }
                reBalance();
            }
            else if (key <= node.key) {
                node.left.delete(id, key);
            } else {
                node.right.delete(id, key);
            }
            reBalance();

        } else {
            return;
        }
    }

    public Patient deleteMinimum() {
        Node node = this.node;
        if ((node.left == null) && (node.right == null)){
            Patient n = node.patient;
            delete(node.patient.id, node.key);
            return n;
        }
        while (node.left.node != null) {
            node = node.left.node;
        }
        Node nn = node;
        delete(node.patient.id, node.key);
        return nn.patient;
    }

    private Node logicalSuccessor(Node node) {
        node = node.right.node;
        if (node != null) {
            while (node.left != null) {
                if (node.left.node == null) {
                    return node;
                }
                else {
                    node = node.left.node;
                }
            }

        }
        return node;
    }

    public void display() {
        display(0, "");
    }

    private void display(int level, String pref) {
        updateHeights();
        updateHeights();
        if (node != null) {
            System.out.println("-".repeat(level * 2) + pref + node.key + "[" + node.patient.toString() + "]" + (isLeaf() ? "L" : " "));
            if (node.left != null) {
                node.left.display(level+1, "<");
            }
            if (node.left != null) {
                node.right.display(level+1, ">");
            }
        }
    }


}
