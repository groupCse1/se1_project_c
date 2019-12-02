package co.edu.unal.tutorshipback.model;

import java.io.Serializable;

public class ClassroomID implements Serializable{
    private String building;

    private int number;

    public ClassroomID(int number, String building) {
        this.number = number;
        this.building = building;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((building == null) ? 0 : building.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ClassroomID other = (ClassroomID) obj;
        if (building == null) {
            if (other.building != null) return false;
        } else if (!building.equals(other.building)) return false;
        return true;
    }
}
