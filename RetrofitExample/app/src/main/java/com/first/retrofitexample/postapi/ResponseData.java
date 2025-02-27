package com.first.retrofitexample.postapi;

import com.google.gson.annotations.SerializedName;

public class ResponseData {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("role_name")
    private String roleName;

    @SerializedName("company_id")
    private String companyId;

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getRoleName() {
        return roleName;
    }
    public String getCompanyId() {
        return companyId;
    }
}
