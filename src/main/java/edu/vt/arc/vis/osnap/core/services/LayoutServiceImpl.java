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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.Layout;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.events.RequestDeniedException;
import edu.vt.arc.vis.osnap.events.RequestFailedException;
import edu.vt.arc.vis.osnap.events.domain.layout.LayoutDetails;


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
@Service
public class LayoutServiceImpl
        implements LayoutService {

    private final DataStore dataStore;

    /**
     * Creates a new instance of the {@link LayoutServiceImpl} class.
     * 
     * @param dataStore
     *            the data store.
     */
    @Autowired
    public LayoutServiceImpl(final DataStore dataStore) {

        this.dataStore = dataStore;

    }

    public Layout createLayout(String projectId, String universeId,
            String layoutId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);


        Layout layout = new Layout(universe);
        layout.setId(layoutId);

        project.addLayout(layout);

        return layout;
    }

    public List<Layout> retrieveAllLayouts(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);

        return project.getLayouts();
    }

    public Layout retrieveLayout(String projectId, String layoutId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);

        return this.dataStore.getLayout(project, layoutId);

    }

    public Layout deleteLayout(String projectId, String layoutId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);

        Layout layout = this.dataStore.getLayout(project, layoutId);

        project.removeLayout(layout);

        return layout;
    }
}
