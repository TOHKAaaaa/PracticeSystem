package cn.edu.zucc.practiceSystem.result;

public class ResultEntity {
    private int role;
    private String username;
    private String password;

    public ResultEntity(){
    }

    public ResultEntity(int role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
