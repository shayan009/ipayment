package com.onetechsol.ipayment.pojo;

public class StatusList {

    public int signup;
    public int forgot;
    public int pwd;

    public StatusList(Integer signup) {
        this.signup = signup;
    }

    public StatusList(int signup, int forgot, int pwd) {
        this.signup = signup;
        this.forgot = forgot;
        this.pwd = pwd;
    }

    public int signup() {
        return signup;
    }

    public int forgot() {
        return forgot;
    }

    public int pwd() {
        return pwd;
    }

    @Override
    public String toString() {
        return "StatusList{" +
                "signup=" + signup +
                ", forgot=" + forgot +
                ", pwd=" + pwd +
                '}';
    }
}
