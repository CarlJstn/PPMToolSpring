package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Project;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.ProjectServices;


@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectServices projectService;
    
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/project")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){ 
    	
    	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
    	if(errorMap!=null) return errorMap;
    	
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
    	Project project = projectService.findProjectByIdentifier(projectId);
    	
    	return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
}