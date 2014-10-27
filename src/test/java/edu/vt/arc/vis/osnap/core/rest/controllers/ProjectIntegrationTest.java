/**
 * 
 */
package edu.vt.arc.vis.osnap.core.rest.controllers;


import edu.vt.arc.vis.osnap.core.rest.controllers.fixtures.DataFixture;
import edu.vt.arc.vis.osnap.core.services.ProjectService;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.rest.controllers.ProjectCommandController;
import edu.vt.arc.vis.osnap.rest.controllers.ProjectQueryController;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * @author William H. Lund, Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 */
public class ProjectIntegrationTest {

    private MockMvc                  mockMvc;

    @InjectMocks
    private ProjectCommandController commandController;
    @InjectMocks
    private ProjectQueryController   queryController;


    private ObjectMapper             mapper;

    @Mock
    private ProjectService           projectService;


    /**
     * Sets up the test.
     */
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        this.mapper = new ObjectMapper();
        this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setObjectMapper(mapper);
        converter.setPrettyPrint(true);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(commandController, queryController)
                .setMessageConverters(converter).build();
    }

    /**
     * @throws Exception
     */
    @Test
    public void thatRetrieveAllProjectsUsesHttpNotFound()
            throws Exception {

        Mockito.when(this.projectService.retrieveAllProjects()).thenThrow(
                new NotFoundException("There are no projects!"));

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/projects").accept(
                                MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // TODO: throw more exceptions, yo!

    /**
     * @throws Exception
     */
    @Test
    public void thatRetrieveAllProjectsUsesHttpOK()
            throws Exception {

//        Mockito.when(this.projectService.retrieveAllProjects()).thenReturn(
//                DataFixture.allProjects());
//
//        this.mockMvc
//                .perform(
//                        MockMvcRequestBuilders.get("/projects").accept(
//                                MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * @throws Exception
     */
    @Test
    public void thatRetrieveProjectUsesHttpOK()
            throws Exception {

//        Mockito.when(
//                this.projectService.retrieveProject(Mockito.any(String.class)))
//                .thenReturn(DataFixture.SingleProject());
//
//        this.mockMvc
//                .perform(
//                        MockMvcRequestBuilders.get("/projects/{id}", "dumbass")
//                                .accept(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
