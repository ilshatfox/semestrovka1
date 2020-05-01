package com.example.demo.service;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {
    private Boolean status;
    private String message;
}
