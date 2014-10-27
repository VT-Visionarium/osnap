package edu.vt.arc.vis.osnap.core.services;

/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
 * _
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * _
 */


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.events.RequestDeniedException;
import edu.vt.arc.vis.osnap.events.RequestFailedException;


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
@Service
public class ProjectServiceImpl
        implements ProjectService {

    private final DataStore dataStore;

    /**
     * Creates a new instance of the {@link ProjectServiceImpl} class.
     * 
     * @param dataStore
     *            the data store.
     */
    @Autowired
    public ProjectServiceImpl(final DataStore dataStore) {

        this.dataStore = dataStore;
    }

    @Override
    public Project createProject(final Project details)
            throws RequestDeniedException, RequestFailedException {


        Project project = details;

        if (this.dataStore.getProjects().containsKey(project.getId())) {

            throw new RequestFailedException("Project " + project
                    + " already exists!");
        }

        this.dataStore.getProjects().put(project.getId(), project);

        return project;
    }

    @Override
    public List<Project> retrieveAllProjects()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        if (this.dataStore.getProjects().isEmpty()) {

            throw new NotFoundException("There are no projects!");
        }

        List<Project> details = new ArrayList<>(this.dataStore
                .getProjects().size());

        for (Project project : this.dataStore.getProjects().values()) {

            details.add(project);
        }

        return details;
    }

    @Override
    public Project retrieveProject(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        if (this.dataStore.getProjects().containsKey(id)) {

            return this.dataStore.getProjects().get(id);
        }

        throw new NotFoundException("Project " + id + " does not exist!");

    }

    @Override
    public Project updateProject(final Project details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = details;

        if (this.dataStore.getProjects().containsKey(project.getId())) {

            this.dataStore.getProjects().put(project.getId(), project);
        }

        else {

            throw new NotFoundException("Project " + project.getId()
                    + " does not exist!");
        }

        return project;
    }

    @Override
    public Project deleteProject(final String id)
            throws NotFoundException, RequestDeniedException,
            RequestFailedException {

        if (this.dataStore.getProjects().containsKey(id)) {

            return (this.dataStore.getProjects().remove(id));
        }
        else {

            throw new NotFoundException("Project " + id + " does not exist!");
        }
    }
}
