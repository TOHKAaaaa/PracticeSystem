package cn.edu.zucc.practiceSystem.entity;

public class PasswordEntity {
    private String oldpwd;
    private String newpwd;

    public PasswordEntity(){
    }

    public PasswordEntity(String oldpwd, String newpwd) {
        this.oldpwd = oldpwd;
        this.newpwd = newpwd;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }
}
