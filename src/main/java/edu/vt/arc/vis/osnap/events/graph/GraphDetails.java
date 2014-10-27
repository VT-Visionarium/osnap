package edu.vt.arc.vis.osnap.events.graph;

import edu.vt.arc.vis.osnap.events.DomainObjectDetails;


/**
 * @author peter
 * @version
 * @since
 *
 */
public class GraphDetails
        extends DomainObjectDetails {

    // TODO: add the schema data.

    /**
     * Creates a new instance of the {@link GraphDetails} class.
     */
    public GraphDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link GraphDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public GraphDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link GraphDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public GraphDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link GraphDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public GraphDetails(final GraphDetails details) {

        super(details);
    }
}