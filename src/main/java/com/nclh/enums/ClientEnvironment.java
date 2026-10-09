package com.nclh.enums;

public enum ClientEnvironment {

    SHIPLAB("shiplab", "Email", "Password"),
    LUNA("luna", "LunaEmail", "LunaPassword"),
    OCI("oci", "AlluraEmail", "AlluraPassword"),
    RSSC("rssc", "RsscEmail", "RsscPassword"),
    VOYAGER("voyager", "VoyagerEmail", "VoyagerPassword");

    private final String key;
    private final String emailColumn;
    private final String passwordColumn;


    ClientEnvironment(String key, String emailColumn, String passwordColumn) {
        this.key = key;
        this.emailColumn = emailColumn;
        this.passwordColumn = passwordColumn;
    }

    public String getEmailColumn() {
        return emailColumn;
    }

    public String getPasswordColumn() {
        return passwordColumn;
    }

    public static ClientEnvironment fromServerName(String serverName) {
        for (ClientEnvironment env : values()) {
            if (serverName.toLowerCase().contains(env.key)) {
                return env;
            }
        }
        throw new IllegalArgumentException("Unknown ClientEnvironment: " + serverName);
    }
}