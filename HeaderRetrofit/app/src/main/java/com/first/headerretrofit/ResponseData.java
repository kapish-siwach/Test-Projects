package com.first.headerretrofit;

public class ResponseData {
    String first_name;
    String middle_name;
    String last_name;
    String login_email_id;
    String emp_id;
    String state;
    String company_id;

    public ResponseData(String first_name, String middle_name, String last_name, String login_email_id, String emp_id, String state,String company_id) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.login_email_id = login_email_id;
        this.emp_id = emp_id;
        this.state = state;
        this.company_id=company_id;

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLogin_email_id() {
        return login_email_id;
    }

    public void setLogin_email_id(String login_email_id) {
        this.login_email_id = login_email_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
