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



import java.util.List;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.events.RequestDeniedException;
import edu.vt.arc.vis.osnap.events.RequestFailedException;



/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 */
public interface ProjectService {

    /**
     * Creates a new {@link Project Project}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the created object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     */
    public abstract Project createProject(Project details)
            throws RequestDeniedException, RequestFailedException;

    /**
     * Returns a list of all projects.
     * 
     * @return returns list of all projects' details
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract List<Project> retrieveAllProjects()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns single project.
     * 
     * @param id
     *            unique identification for project
     * @return returns project details of given id
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract Project retrieveProject(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates user stated project.
     * 
     * @param details
     *            the details of the request.
     * @return returns updated project information
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract Project updateProject(final Project details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes project given unique project id from user.
     * 
     * @param id
     *            unique identification for project
     * @return returns the deleted project.
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract Project deleteProject(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

}