package com.example.application.entity;

import lombok.Data;

@Data
public class DVD {
    private String id;
    private String title;
    private float imdbScore;
    private int year;
    private int runtime;
}
