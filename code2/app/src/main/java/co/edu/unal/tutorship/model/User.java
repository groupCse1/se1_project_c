package co.edu.unal.tutorship.model;

public class User {

    private long id;

    private String user;

    private String password;

    private String name;

    private String faculty;

    private String career;

    private long cellphone;


    public User(String user, String password, String name,
                String faculty, String career) {

        this.user = user;
        this.password = password;
        this.name = name;
        this.faculty = faculty;
        this.career = career;

    }

    public User() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public long getCellphone() {
        return cellphone;
    }

    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }
}