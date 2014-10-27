package edu.vt.arc.vis.osnap.core.services;


/*
 * _ The Open Semantic Network Analysis Platform (OSNAP) _ Copyright (C) 2012 -
 * 2014 Visionarium at Virginia Tech _ Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. _
 */


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jutility.io.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.Layout;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.io.IOManager;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 *
 */
@Component
public class DataStore
        implements ResourceLoaderAware {

    private static Logger              LOG = LoggerFactory
                                                   .getLogger(DataStore.class);

    private ResourceLoader             resourceLoader;
    private final Map<String, Project> projects;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.ResourceLoaderAware#setResourceLoader(org
     * .springframework.core.io.ResourceLoader)
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

        this.resourceLoader = resourceLoader;

    }

    /**
     * Returns the resourceLoader.
     * 
     * @return the resourceLoader.
     */
    public ResourceLoader getResourceLoader() {

        return resourceLoader;
    }


    /**
     * Returns the projects.
     * 
     * @return the projects.
     */
    public Map<String, Project> getProjects() {


        Resource resource = this.resourceLoader
                .getResource("diseaseNetwork.osnap");

        if (this.projects.isEmpty()) {
            Project project;
            try {
                project = IOManager.Instance().deserialize(resource.getFile(),
                        Project.class);

                this.projects.put(project.getId(), project);
            }
            catch (SerializationException | IOException e) {

                LOG.error("Could not open stored project!", e);
            }
        }

        return projects;
    }



    /**
     * Creates a new instance of the {@link DataStore} class.
     */
    public DataStore() {


        this.projects = new LinkedHashMap<>();



    }


    /**
     * @param projectId
     * @return
     * @throws NotFoundException
     */
    public Project getProject(final String projectId)
            throws NotFoundException {

        Project project = this.projects.get(projectId);
        if (project == null) {

            throw new NotFoundException("Could not find project with id "
                    + projectId);
        }

        return project;
    }

    public Universe getUniverse(final Project project)
            throws NotFoundException {

        Universe universe = project.getUniverse();
        if (universe == null) {

            throw new NotFoundException("Project does not contain universe!");
        }

        return universe;
    }

    public Graph getGraph(final Universe universe, String id)
            throws NotFoundException {

        Graph graph = universe.getGraph(id);

        if (graph == null) {

            throw new NotFoundException("Universe " + universe.getId()
                    + " does not contain graph " + id);
        }

        return graph;
    }

    public Node getNode(final Universe universe, String id)
            throws NotFoundException {

        Node node = universe.getNode(id);

        if (node == null) {

            throw new NotFoundException("Universe " + universe.getId()
                    + " does not contain node " + id);
        }

        return node;
    }

    public Edge getEdge(final Universe universe, String id)
            throws NotFoundException {

        Edge edge = universe.getEdge(id);

        if (edge == null) {

            throw new NotFoundException("Universe " + universe.getId()
                    + " does not contain edge " + id);
        }

        return edge;
    }

    public HyperEdge getHyperEdge(final Universe universe, String id)
            throws NotFoundException {

        HyperEdge edge = universe.getHyperEdge(id);

        if (edge == null) {

            throw new NotFoundException("Universe " + universe.getId()
                    + " does not contain hyperedge " + id);
        }

        return edge;
    }


    public Layout getLayout(final Project project, String id)
            throws NotFoundException {

        for (Layout layout : project.getLayouts()) {

            if (layout.getId().equals(id)) {

                return layout;
            }
        }

        throw new NotFoundException("Project " + project.getId()
                + " does not contain layout " + id);
    }
}
