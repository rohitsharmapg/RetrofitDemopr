package com.example.rohit.retrofitdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelClass {

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("message")
    @Expose
    public String message;




}
