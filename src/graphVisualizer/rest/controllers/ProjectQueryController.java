/**
 * 
 */
package graphVisualizer.rest.controllers;


import java.util.List;

import graphVisualizer.common.NotFoundException;
import graphVisualizer.common.Project;
import graphVisualizer.common.RequestDeniedException;
import graphVisualizer.common.RequestFailedException;
import graphVisualizer.core.services.VisualizationService;
import graphVisualizer.events.ProjectDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author whlund15
 *
 */
@Controller
@RequestMapping("/projects")
public class ProjectQueryController {

    private Logger                     LOG = LoggerFactory
                                                   .getLogger(ProjectQueryController.class);

    private final VisualizationService visualizationService;


    @Autowired
    public ProjectQueryController(
            final VisualizationService visualizationService) {

        this.visualizationService = visualizationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAllProjects() {

        try {
            this.visualizationService.retrieveAllProjects();
//            return new ResponseEntity<>(
//                    Project.fromDetails(this.visualizationService
//                            .retrieveAllProjects()), HttpStatus.OK);
            return null;
        }
        catch (RequestDeniedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Project> getProject(@PathVariable String id) {
        
        try {
            this.visualizationService.retrieveProject(id);
//          return new ResponseEntity<>(
//          Project.fromDetails(this.visualizationService
//                  .retrieveProject(id)), HttpStatus.OK);
            
            return null;
        }
        catch (RequestDeniedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
}
