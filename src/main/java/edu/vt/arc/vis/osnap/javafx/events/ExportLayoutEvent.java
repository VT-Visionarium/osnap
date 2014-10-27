/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


/**
 * 
 */
package edu.vt.arc.vis.osnap.javafx.events;

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


import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import javafx.event.Event;
import javafx.event.EventType;


/**
 * @author Shawn P Neuman
 * 
 */
public class ExportLayoutEvent
        extends Event {


    private static final long                        serialVersionUID = 1L;

    private final Visualization                      visualization;
    /**
     * Defines the event
     */
    public static final EventType<ExportLayoutEvent> EXPORT           = new EventType<>(
                                                                              "EXPORT");


    /**
     * @param event
     * @param visualization
     */
    public ExportLayoutEvent(EventType<? extends Event> event,
            Visualization visualization) {

        super(event);
        this.visualization = visualization;
    }

    /**
     * @return the visualization
     */
    public Visualization getVisualization() {

        return visualization;
    }
}
