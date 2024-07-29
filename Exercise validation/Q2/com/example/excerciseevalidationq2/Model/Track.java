package com.example.excerciseevalidationq2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Track {

    @NotEmpty(message = "id should be not empty!")
    @Size(min = 3, max = 10,message = "id should be 3 to 10 characters")
    private String id;

    @NotEmpty(message = "title should be not empty!")
    @Size(min = 9, max = 20,message = "title should be 9 to 20 characters")
    private String title;

    @NotEmpty(message = "description should be not empty!")
    @Size(min = 16, max = 30,message = "description should be 16 to 30 characters")
    private String description;

    @NotEmpty(message = "status should be not empty!")
    @Pattern(regexp = "^(not started|in progress|completed)$",message = "status should be not started OR in progress OR completed")
    private String status;

    @NotEmpty(message = "Company name should be not empty!")
    @Size(min = 6, max = 20,message = "Company name should be 6 to 20 characters")
    private String companyName;

}
