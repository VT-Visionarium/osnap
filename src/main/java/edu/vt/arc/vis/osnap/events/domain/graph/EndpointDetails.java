package edu.vt.arc.vis.osnap.events.domain.graph;

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


import edu.vt.arc.vis.osnap.events.domain.DomainObjectDetails;


/**
 * 
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
public class EndpointDetails
        extends DomainObjectDetails {

    private NodeDetails node;

    private boolean         isIncoming;
    private boolean         isOutgoing;

    /**
     * Returns the node.
     * 
     * @return the node.
     */
    public NodeDetails getNode() {

        return node;
    }


    /**
     * Sets the node.
     * 
     * @param node
     *            the node.
     */
    public void setNode(NodeDetails node) {

        this.node = node;
    }


    /**
     * Returns the isIncoming.
     * 
     * @return the isIncoming.
     */
    public boolean isIncoming() {

        return isIncoming;
    }


    /**
     * Sets the isIncoming.
     * 
     * @param isIncoming
     *            the isIncoming.
     */
    public void setIncoming(boolean isIncoming) {

        this.isIncoming = isIncoming;
    }


    /**
     * Returns the isOutgoing.
     * 
     * @return the isOutgoing.
     */
    public boolean isOutgoing() {

        return isOutgoing;
    }


    /**
     * Sets the isOutgoing.
     * 
     * @param isOutgoing
     *            the isOutgoing.
     */
    public void setOutgoing(boolean isOutgoing) {

        this.isOutgoing = isOutgoing;
    }

    /**
     * Creates a new instance of the {@link EndpointDetails} class.
     */
    public EndpointDetails() {

        super();
    }

    /**
     * Creates a new instance of the {@link EndpointDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public EndpointDetails(String id) {

        super(id);
    }


    /**
     * Creates a new instance of the {@link EndpointDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public EndpointDetails(DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link EndpointDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public EndpointDetails(EndpointDetails details) {

        super(details);

        this.setNode(details.getNode());
        this.setIncoming(details.isIncoming);
        this.setOutgoing(details.isOutgoing);
    }

}
