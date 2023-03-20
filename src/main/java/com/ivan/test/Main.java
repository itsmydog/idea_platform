package com.ivan.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
       try {
           ObjectMapper mapper = new ObjectMapper();
           JavaTimeModule javaTimeModule = new JavaTimeModule();
           DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
           DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
           javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
           javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
           mapper.registerModule(javaTimeModule);

           TicketList ticketList = mapper.readValue(Paths.get("/Users/user/Downloads/idea_platform/src/main/java/com/ivan/test/tickets.json").toFile(), TicketList.class);
           long nano_one = 0;
           long nano_two = 0;
           long nano_list = 0;
           int count = 0;
           ArrayList <Long> time = new ArrayList<>();
           for (NameOfFields nameOfFields : ticketList.getTickets()) {
               LocalTime localTime = nameOfFields.getDeparture_time();
               LocalTime localTimeArival = nameOfFields.getArrival_time();
               nano_list = localTimeArival.toSecondOfDay() - localTime.toSecondOfDay();
               time.add(nano_list);
               nano_one += localTime.toSecondOfDay();
               nano_two += localTimeArival.toSecondOfDay();
               count++;
           }

           Collections.sort(time);
           int index = (int) (Math.round(time.size()*0.9) - 1);
           LocalTime procentil = LocalTime.ofSecondOfDay(time.get(index));
           long timeOne = (nano_two - nano_one);
           LocalTime time1 = LocalTime.ofSecondOfDay(timeOne/count);


           BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/user/Desktop/archive/log.txt", true));
           writer.write("90 процентиль " + procentil + "\n" + "Среднее время полета " + time1);
           writer.close();

       } catch (Exception e){
           e.printStackTrace();
       }

    }
}

