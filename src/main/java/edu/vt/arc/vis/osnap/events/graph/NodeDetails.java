package edu.vt.arc.vis.osnap.events.graph;

import edu.vt.arc.vis.osnap.events.DomainObjectDetails;


/**
 * @author peter
 * @version
 * @since
 *
 */
public class NodeDetails
        extends DomainObjectDetails {

    // TODO: add the schema data.

    /**
     * Creates a new instance of the {@link NodeDetails} class.
     */
    public NodeDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link NodeDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public NodeDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link NodeDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public NodeDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link NodeDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public NodeDetails(final NodeDetails details) {

        super(details);
    }
}