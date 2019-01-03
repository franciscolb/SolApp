package com.example.francisco.solapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


class DataLocation {
    @SerializedName("globalIdLocal")
    @Expose
    private int globalIdLocal;

    @SerializedName("local")
    @Expose
    private String local;

    public DataLocation(int globalIdLocal, String local) {
        this.globalIdLocal = globalIdLocal;
        this.local = local;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
