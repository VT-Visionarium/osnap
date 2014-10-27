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


package edu.vt.arc.vis.osnap.javafx.events;


import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * custom event to handle changes to meta data
 * 
 * @author Shawn P Neuman
 * 
 */
public class MetadataChangedEvent
        extends Event {

    /**
     * event definition
     */
    public static final EventType<MetadataChangedEvent> METADATA_CHANGED = new EventType<>(
                                                                                 ANY,
                                                                                 "METADATA_CHANGED");

    /**
     * @author spn2460 enumerated types
     */
    public enum Change {
        /**
         * add new meta data
         */
        ADD,
        /**
         * remove existing meta data
         */
        REMOVE;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final String      key;
    private final Change      change;



    /**
     * @return the id
     */
    public String getKey() {

        return key;
    }


    /**
     * @return the change type
     */
    public Change getChange() {

        return change;
    }


    /**
     * event
     * 
     * @param id
     *            key value
     * @param change
     *            change type
     */
    public MetadataChangedEvent(String id, Change change) {

        this(METADATA_CHANGED, id, change);
    }



    /**
     * @param eventType
     * @param key
     * @param change
     */
    public MetadataChangedEvent(EventType<? extends Event> eventType,
            String key, Change change) {

        super(eventType);

        this.key = key;
        this.change = change;
    }



    /**
     * @param source
     * @param target
     * @param eventType
     * @param key
     * @param change
     */
    public MetadataChangedEvent(Object source, EventTarget target,
            EventType<? extends Event> eventType, String key, Change change) {

        super(source, target, eventType);
        this.key = key;
        this.change = change;
    }
}
