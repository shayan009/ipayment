package com.onetechsol.ipayment.pojo;

public class PageList {

    public int login;
    public int signup;
    public int forgot;
    public int home;

    public PageList(int login, int signup, int forgot, int home) {
        this.login = login;
        this.signup = signup;
        this.forgot = forgot;
        this.home = home;
    }

    public int login() {
        return login;
    }

    public int signup() {
        return signup;
    }

    public int forgot() {
        return forgot;
    }

    public int home() {
        return home;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "login=" + login +
                ", signup=" + signup +
                ", forgot=" + forgot +
                ", home=" + home +
                '}';
    }
}
