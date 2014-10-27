package edu.vt.arc.vis.osnap.rest.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.services.GraphService;
import edu.vt.arc.vis.osnap.core.services.ProjectService;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.events.RequestDeniedException;
import edu.vt.arc.vis.osnap.events.RequestFailedException;
import edu.vt.arc.vis.osnap.events.domain.ProjectDetails;


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 */
@Controller
@RequestMapping("/projects")
public class ProjectCommandController {

    private Logger               LOG = LoggerFactory
                                             .getLogger(ProjectQueryController.class);

    private final ProjectService projectService;
    private final GraphService   graphService;


    /**
     * Creates a new instance of the {@link ProjectCommandController} class.
     * 
     * @param projectService
     *            the project service.
     * @param graphService
     *            the graph service.
     */
    @Autowired
    public ProjectCommandController(final ProjectService projectService,
            final GraphService graphService) {

        this.projectService = projectService;
        this.graphService = graphService;
    }



    /**
     * Attempts to create a new {@link Project}
     * 
     * @param id
     *            id of the project to create.
     * @param builder
     *            the UriComponentsBuilder.
     * @return the newly created project.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Project> createProject(@RequestBody String id,
            UriComponentsBuilder builder) {

        LOG.debug("Attempting to create project " + id);

        Project project = new Project((Universe)null);
        project.setId(id);


        Project result;
        try {
            result = this.projectService.createProject(project);
        }
        catch (RequestFailedException e) {

            LOG.debug("Request failed!");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        catch (RequestDeniedException e) {

            LOG.debug("Access denied!");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        Project newProject = result;

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/osnap/projects/{id}")
                .buildAndExpand(newProject.getId()).toUri());

        return new ResponseEntity<>(newProject, headers, HttpStatus.CREATED);
    }

    /**
     * Attempts to create a new {@link Project}
     * 
     * @param project
     *            the project to create.
     * @param builder
     *            the UriComponentsBuilder.
     * @return the newly created project.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Project> updateProject(@RequestBody Project project,
            UriComponentsBuilder builder) {

        LOG.debug("Attempting to update project " + project);

        try {

            Project updatedProject = this.projectService.updateProject(project);

            LOG.debug("Updated!");
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        }
        catch (RequestFailedException e) {

            LOG.debug("Request failed (Project)!");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        catch (RequestDeniedException e) {

            LOG.debug("Access denied (Project)!");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NotFoundException e) {

            LOG.debug("Not found (Project)!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Attempts to delete the {@link Project} with the provided id.
     * 
     * @param id
     *            the id.
     * @return the {@link Project}, if found.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable String id) {

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


}
