/**
 * 
 */
package graphVisualizer.rest.controllers;


import graphVisualizer.common.NotFoundException;
import graphVisualizer.core.services.VisualizationService;
import graphVisualizer.rest.controllers.fixtures.DataFixture;

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
 * @version 1.0.1
 * @since 1.0.1
 */
public class ProjectIntegrationTest {

    private MockMvc                  mockMvc;

    @InjectMocks
    private ProjectCommandController commandController;
    @InjectMocks
    private ProjectQueryController   queryController;


    private ObjectMapper             mapper;

    @Mock
    private VisualizationService     visualizationService;


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

    @Test
    public void thatRetrieveAllProjectsUsesHttpNotFound()
            throws Exception {

        Mockito.when(this.visualizationService.retrieveAllProjects())
                .thenThrow(new NotFoundException("There are no projects!"));

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/projects").accept(
                                MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // TODO: throw more exceptions, yo!

    @Test
    public void thatRetrieveAllProjectsUsesHttpOK()
            throws Exception {

        Mockito.when(this.visualizationService.retrieveAllProjects())
                .thenReturn(DataFixture.allProjects());

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/projects").accept(
                                MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void thatRetrieveProjectUsesHttpOK()
            throws Exception {

        Mockito.when(
                this.visualizationService.retrieveProject(Mockito
                        .any(String.class))).thenReturn(
                DataFixture.SingleProject());

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/projects/{id}", "dumbass")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
