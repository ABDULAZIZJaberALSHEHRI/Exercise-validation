package com.example.excerciseevalidationq2.Controller;

import com.example.excerciseevalidationq2.ApiResponse.ApiResponse;
import com.example.excerciseevalidationq2.Model.Track;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/track")
public class TrackController {

    ArrayList<Track> projects = new ArrayList<Track>();

    @PostMapping("/add-project")
    public ResponseEntity addTracker(@Valid @RequestBody Track project, Errors errors) {
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("project added successfully"));
    }

    @GetMapping("/display-projects")
    public ResponseEntity displayProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    @PutMapping("/update-project/{index}")
    public ResponseEntity updateTracker(@PathVariable int index,@Valid @RequestBody Track project, Errors errors) {
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("project updated successfully"));
    }


    @DeleteMapping("/delete-project/{index}")
    public ResponseEntity deleteTracker(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("project removed successfully"));
    }

    @PutMapping("/update-status/{index}")
    public ResponseEntity updateProjectStatus(@PathVariable int index) {
        if(projects.get(index).getStatus().equals("not started")) {
            projects.get(index).setStatus("in progress");
        }else if(projects.get(index).getStatus().equals("in progress")) {
            projects.get(index).setStatus("completed");
        }else if(projects.get(index).getStatus().equals("completed")) {
            return ResponseEntity.status(400).body(new ApiResponse("Project is already completed"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Status changed successfully"));
    }

    @GetMapping("/search-title/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title){

        for (Track t : projects) {
            if(t.getTitle().equalsIgnoreCase(title)) {
                return ResponseEntity.status(200).body(t);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project not found"));

    }

    @GetMapping("/search-company/{companyname}")
        public ArrayList<Track> displayProjectByCompany(@PathVariable String companyname) {
            ArrayList<Track> company = new ArrayList<>();
            for (Track c : projects) {
                if(c.getCompanyName().equalsIgnoreCase(companyname)) {
                    company.add(c);
                }
            }
            return company;
        }

}
