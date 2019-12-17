package co.edu.unal.tutorship.model;

public class Classroom {

    private long id;

    private String building;

    private int number;

    private int capacity;


    public Classroom(long id, String building, int number, int capacity) {
        super();
        this.id = id;
        this.building = building;
        this.number = number;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Classroom(String building, int number, int capacity) {
        this.building = building;
        this.number = number;
        this.capacity = capacity;
    }

    public Classroom() {
        super();
    }
}
