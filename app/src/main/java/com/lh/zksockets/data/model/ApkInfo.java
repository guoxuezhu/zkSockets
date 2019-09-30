package com.lh.zksockets.data.model;

import com.google.gson.annotations.SerializedName;

public class ApkInfo {

    @SerializedName("title")
    public String title;
    @SerializedName("version_code")
    public String version_code;
    @SerializedName("version_name")
    public String version_name;
    @SerializedName("files")
    public String files;

    @Override
    public String toString() {
        return "ApkInfo{" +
                "title='" + title + '\'' +
                ", version_code='" + version_code + '\'' +
                ", version_name='" + version_name + '\'' +
                ", files='" + files + '\'' +
                '}';
    }
}
