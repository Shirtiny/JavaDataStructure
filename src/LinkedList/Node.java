package LinkedList;

public class Node {

    int id;
    String name;
    Node next;

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Node(int id, String name, Node next) {
        this.id = id;
        this.name = name;
        this.next = next;
    }

    public Node() {
    }
}
