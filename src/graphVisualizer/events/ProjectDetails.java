/**
 * 
 */
package graphVisualizer.events;


import graphVisualizer.events.graph.UniverseDetails;


/**
 * @author Peter J. Radics
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class ProjectDetails
        extends DomainObjectDetails {

    private UniverseDetails universe;


    /**
     * @return the universe
     */
    public UniverseDetails getUniverse() {

        return universe;
    }

    /**
     * @param universe
     *            the universe to set
     */
    public void setUniverse(UniverseDetails universe) {

        this.universe = universe;
    }

    // TODO: add the visualization and layout details.

    /**
     * Creates a new instance of the {@link ProjectDetails} class.
     */
    public ProjectDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link ProjectDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public ProjectDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link ProjectDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public ProjectDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link ProjectDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public ProjectDetails(final ProjectDetails details) {

        super(details);
        this.setUniverse(details.getUniverse());
    }
}
