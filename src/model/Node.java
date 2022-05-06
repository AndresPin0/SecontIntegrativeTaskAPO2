package model;

public class Node {

    private boolean seed;
    private int value;
    private Node next;
    private Node portal;
    private Node prev;
    private boolean Rick;
    private boolean Morty;

    public boolean isRick() {
        return Rick;
    }

    public void setRick(boolean rick) {
        Rick = rick;
    }

    public boolean isMorty() {
        return Morty;
    }

    public void setMorty(boolean morty) {
        Morty = morty;
    }

    public Node(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSeed() {
        return seed;
    }

    public void setSeed(boolean seed) {
        this.seed = seed;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getPortal() {
        return portal;
    }

    public void setPortal(Node portal) {
        this.portal = portal;
    }
}
