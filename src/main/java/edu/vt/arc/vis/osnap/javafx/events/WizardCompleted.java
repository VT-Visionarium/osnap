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


import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.IStatus;
import javafx.event.Event;
import javafx.event.EventType;


/**
 * adding items to mapping details page through this event
 * @author Shawn P Neuman 
 * 
 */
public class WizardCompleted
        extends Event {

    private final IStatus                          statusObject;
    private static final long                      serialVersionUID = 1L;
    /**
     * 
     */
    public static final EventType<WizardCompleted> WIZARD_COMPLETED = new EventType<>(
                                                                            "WIZARD_COMPLETED");

    /**
     * @param eventType
     * @param status
     */
    public WizardCompleted(EventType<? extends Event> eventType, IStatus status) {

        super(eventType);
        this.statusObject = status;

    }

    /**
     * @return the status object which contains a mapping layout
     */
    public IStatus getStatusObject() {

        return statusObject;
    }



}
