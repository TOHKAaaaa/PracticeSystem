package cn.edu.zucc.practiceSystem.result;

public class PracticeResultEntity {
    private String company;
    private String states;
    private int teacher;
    private String position;
    private String date1;
    private String date2;

    public PracticeResultEntity(){
    }

    public PracticeResultEntity(String company, String states, int teacher, String position, String date1, String date2) {
        this.company = company;
        this.states = states;
        this.teacher = teacher;
        this.position = position;
        this.date1 = date1;
        this.date2 = date2;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
}
