package graphVisualizer.events.graph;

import graphVisualizer.events.DomainObjectDetails;


/**
 * @author peter
 * @version
 * @since
 *
 */
public class EdgeDetails
        extends DomainObjectDetails {

    // TODO: add the schema data.

    /**
     * Creates a new instance of the {@link EdgeDetails} class.
     */
    public EdgeDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link EdgeDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public EdgeDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link EdgeDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public EdgeDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link EdgeDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public EdgeDetails(final EdgeDetails details) {

        super(details);
    }
}