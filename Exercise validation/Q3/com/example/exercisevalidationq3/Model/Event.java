package com.example.exercisevalidationq3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "id should be not empty!")
    @Size(min = 3, max = 10,message = "id should be 3 to 10 characters")
    private String id;

    @NotEmpty(message = "description should be not empty!")
    @Size(min = 16, max = 30,message = "description should be 16 to 30 characters")
    private String description;

    @NotNull(message = "capacity should be not empty!")
    @Positive(message = "capacity should be number!")
    @Min(value = 25,message = "capacity must be greater then 25")
    private int capacity;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
