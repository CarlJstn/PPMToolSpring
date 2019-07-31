package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Project;
import com.example.demo.exceptions.ProjectIdException;
import com.example.demo.repositories.ProjectRepository;


@Service
public class ProjectServices {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	//	Create and Update 
	public Project saveOrUpdateProject(String projectId, Project project) {
		if(!(projectId == "none")) {
			// Update
			Project projectUpdate = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
			
			if(projectUpdate == null) {
				throw new ProjectIdException("Project ID '"+projectId+"' does not exists");
			}
			
			projectUpdate.setDescription(project.getDescription());
			projectUpdate.setProjectName(project.getProjectName());
			projectUpdate.setStart_date(project.getStart_date());
			projectUpdate.setEnd_date(project.getEnd_date());
			projectUpdate.setProjectIdentifier(projectId);
			
			return projectRepository.save(projectUpdate);
		}else {
			// Create
			try {
				project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
				
				return projectRepository.save(project);
			} catch(Exception e) {
				throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
			}
		}
	}
	
	// Get Project by Id
	public Project findProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Project ID '"+projectId+"' does not exists");
		}
		
		return project;
	}
	
	// Get all projects
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	// Delete project by Id
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Cannot delete Project with ID '"+projectId+"'. This project does not exists");
		}
		
		projectRepository.delete(project);
	}
}
