package co.edu.unal.tutorship.model;


import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("code")
    private long code;

    @SerializedName("subject_name")
    private String subject_name;

    @SerializedName("subject_faculty")
    private String subject_faculty;



    public Subject(long code, String subject_name, String subject_faculty) {
        super();
        this.code = code;
        this.subject_name = subject_name;
        this.subject_faculty = subject_faculty;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_faculty() {
        return subject_faculty;
    }

    public void setSubject_faculty(String subject_faculty) {
        this.subject_faculty = subject_faculty;
    }

    public Subject() {
        super();
    }
}
