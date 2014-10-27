package graphVisualizer.events.graph;

import graphVisualizer.events.DomainObjectDetails;


/**
 * @author peter
 * @version
 * @since
 *
 */
public class HyperEdgeDetails
        extends DomainObjectDetails {

    // TODO: add the schema data.

    /**
     * Creates a new instance of the {@link HyperEdgeDetails} class.
     */
    public HyperEdgeDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link HyperEdgeDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public HyperEdgeDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link HyperEdgeDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public HyperEdgeDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link HyperEdgeDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public HyperEdgeDetails(final HyperEdgeDetails details) {

        super(details);
    }
}