package com.example.projetoagenda;

public class Appointment {
    private String time;
    private String description;

    public Appointment(String time, String description) {
        this.time = time;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}

