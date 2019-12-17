package co.edu.unal.tutorship.model;

import java.sql.Date;
import java.sql.Time;

public class Tutorship {
    private long idtutorship;

    private String date;

    private String init_Hour;

    private int length;

    private int limit_Number;

    private int viability;

    private Long tutor;

    private Long subject;

    private Long classroom;





    public Tutorship(long idtutorship, String date, String init_Hour, int length, int limit_Number, int viability,
                     Long tutor, Long subject, Long classroom) {
        super();
        this.idtutorship = idtutorship;
        this.date = date;
        this.init_Hour = init_Hour;
        this.length = length;
        this.limit_Number = limit_Number;
        this.viability = viability;
        this.tutor = tutor;
        this.subject = subject;
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Tutorship{" +
                "idtutorship=" + idtutorship +
                ", date=" + date +
                ", init_Hour=" + init_Hour +
                ", length=" + length +
                ", limit_Number=" + limit_Number +
                ", viability=" + viability +
                ", tutor=" + tutor +
                ", subject=" + subject +
                ", classroom=" + classroom +
                '}';
    }

    public long getIdtutorship() {
        return idtutorship;
    }

    public void setIdtutorship(long idtutorship) {
        this.idtutorship = idtutorship;
    }

    public void setTutor(Long tutor) {
        this.tutor = tutor;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public Long getClassroom() {
        return classroom;
    }

    public void setClassroom(Long classroom) {
        this.classroom = classroom;
    }

    private long getIdTutorship() {
        return idtutorship;
    }
    private void setIdTutorship(long idtutorship) {
        this.idtutorship=idtutorship;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getViability() {
        return viability;
    }
    public void setViability(int viability) {
        this.viability = viability;
    }
    public String getInit_Hour() {
        return init_Hour;
    }
    public void setInit_Hour(String init_Hour) {
        this.init_Hour = init_Hour;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getLimit_Number() {
        return limit_Number;
    }
    public void setLimit_Number(int limit_Number) {
        this.limit_Number = limit_Number;
    }
    public Long getTutor() {
        return tutor;
    }

    public Tutorship() {

    }
}