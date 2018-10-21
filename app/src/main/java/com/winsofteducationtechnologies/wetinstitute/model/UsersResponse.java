package com.winsofteducationtechnologies.wetinstitute.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
        class              :       SignUp
        description        :       Used to insert the input fields and register the valid user
        arguments          :       
        return type        :
*/public class UsersResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("user")
    private List<User> user;

    public UsersResponse(boolean error, List<User> user) {
        this.error = error;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public List<User> getUser() {
        return user;
    }
}
