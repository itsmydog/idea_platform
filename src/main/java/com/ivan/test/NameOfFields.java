package com.ivan.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameOfFields {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    public LocalDate departure_date;
    public LocalTime departure_time;
    public LocalDate arrival_date;
    public LocalTime arrival_time;
    private String carrier;
    private int stops;
    private double price;


}
