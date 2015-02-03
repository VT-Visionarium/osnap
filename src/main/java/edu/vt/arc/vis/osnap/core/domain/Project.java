package edu.vt.arc.vis.osnap.core.domain;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Endpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.base.EdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.EndpointBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.GraphBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.HyperEdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.NodeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.UniverseBase;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutVisualizer;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.events.domain.ProjectDetails;


/**
 * The {@link Project} class provides a container for a visualization project,
 * namely a {@link Universe}, a number of {@link LayoutVisualizer Layouts}, and the
 * associated {@link Visualization Visualizations}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 */
@XmlRootElement(name = "OSNAP_Project")
@XmlSeeAlso({ UniverseBase.class, Universe.class, GraphBase.class, Graph.class,
        NodeBase.class, Node.class, EdgeBase.class, Edge.class,
        HyperEdgeBase.class, HyperEdge.class, EndpointBase.class,
        Endpoint.class })
@XmlType(name = "OSNAP_Project", propOrder = { "universe", "layoutVisualizers",
        "visualizations" })
public class Project
        extends DomainObject {

    @XmlElement(name = "Universe", type = Universe.class)
    private Universe                  universe;

    @XmlElements({ @XmlElement(name = "LayoutVisualizer", type = LayoutVisualizer.class) })
    @XmlElementWrapper(name = "Layouts")
    private final List<LayoutVisualizer>        layoutVisualizers;
    @XmlElements({ @XmlElement(name = "Visualization",
            type = Visualization.class) })
    @XmlElementWrapper(name = "Visualizations")
    private final List<Visualization> visualizations;

    /**
     * Returns the universe.
     * 
     * @return the universe.
     */
    public Universe getUniverse() {

        return universe;
    }


    /**
     * Sets the universe.
     * 
     * @param universe
     *            the universe.
     */
    public void setUniverse(Universe universe) {

        this.universe = universe;
    }



    /**
     * Returns the list of {@link LayoutVisualizer Layouts}..
     * 
     * @return the list of {@link LayoutVisualizer Layouts}.
     */
    public List<LayoutVisualizer> getLayouts() {

        return this.layoutVisualizers;
    }


    @SuppressWarnings("unused")
    private void setLayouts(List<LayoutVisualizer> layoutVisualizers) {

        for (LayoutVisualizer layoutVisualizer : layoutVisualizers) {

            this.addLayout(layoutVisualizer);
        }
    }

    /**
     * Adds the {@link LayoutVisualizer} to the list of layoutVisualizers.
     * 
     * @param layoutVisualizer
     *            the {@link LayoutVisualizer} to add.
     * @return {@code true}, if the collection is changed by the operation.
     */
    public boolean addLayout(LayoutVisualizer layoutVisualizer) {

        return this.layoutVisualizers.add(layoutVisualizer);
    }

    /**
     * Removes the {@link LayoutVisualizer} from the list of layoutVisualizers.
     * 
     * @param layoutVisualizer
     *            the {@link LayoutVisualizer} to remove.
     * @return {@code true}, if the collection is changed by the operation.
     */
    public boolean removeLayout(LayoutVisualizer layoutVisualizer) {

        return this.layoutVisualizers.remove(layoutVisualizer);
    }

    /**
     * Clears the list of {@link LayoutVisualizer Layouts}.
     */
    public void clearLayouts() {

        this.layoutVisualizers.clear();
    }



    /**
     * Returns the list of {@link Visualization Visualizations}.
     * 
     * @return the list of {@link Visualization Visualizations}.
     */
    public List<Visualization> getVisualizations() {

        return this.visualizations;
    }


    @SuppressWarnings("unused")
    private void setVisualizations(List<Visualization> visualizations) {

        for (Visualization visualization : visualizations) {

            this.addVisualization(visualization);
        }
    }

    /**
     * Adds the {@link Visualization} to the list of visualizations.
     * 
     * @param visualization
     *            the {@link Visualization} to add.
     * @return {@code true}, if the collection is changed by the operation.
     */
    public boolean addVisualization(Visualization visualization) {

        return this.visualizations.add(visualization);
    }

    /**
     * Removes the {@link Visualization} from the list of visualizations.
     * 
     * @param visualization
     *            the {@link Visualization} to remove.
     * @return {@code true}, if the collection is changed by the operation.
     */
    public boolean removeVisualization(Visualization visualization) {

        return this.visualizations.remove(visualization);
    }

    /**
     * Clears the list of {@link Visualization Visualizations}.
     */
    public void clearVisualizations() {

        this.visualizations.clear();
    }

    /**
     * Creates a new instance of the {@link Project} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Project() {

        this(null, true);
    }

    /**
     * Creates a new instance of the {@link Project} class.
     * 
     * @param universe
     *            the universe.
     */
    public Project(Universe universe) {

        this(universe, false);

    }

    /**
     * Creates a new instance of the {@link Project} class.
     * 
     * @param universe
     *            the universe.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public Project(Universe universe, boolean serialization) {


        if (universe != null) {

            this.setId("osnap--" + universe.getId());
        }
        else {

            this.setId(null);
        }
        this.setUniverse(universe);
        this.layoutVisualizers = new LinkedList<>();
        this.visualizations = new LinkedList<>();
    }


    public Project(Project projectToCopy) {

        this.setUniverse(new Universe());
        
        this.getUniverse().setId(projectToCopy.getUniverse().getId());
        
        this.setId(projectToCopy.getId());

        this.layoutVisualizers = new LinkedList<>();
        this.visualizations = new LinkedList<>();
    }

    /**
     * Creates a new instance of the {@link Project} class from the provided
     * details.
     * 
     * @param details
     *            the details
     */
    protected Project(final ProjectDetails details) {

        super(details);

        this.layoutVisualizers = new LinkedList<>();
        this.visualizations = new LinkedList<>();
    }

    @Override
    public ProjectDetails toDetails() {

        ProjectDetails details = new ProjectDetails(super.toDetails());



        return details;
    }

    /**
     * Returns a {@link Project} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link Project} corresponding to the provided details.
     */
    public static Project fromDetails(final ProjectDetails details) {

        return new Project(details);
    }

    /**
     * Returns a {@link Project} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link Project} corresponding to the provided details.
     */
    public static List<Project> fromDetails(final List<ProjectDetails> details) {

        List<Project> projects = new ArrayList<>(details.size());

        for (ProjectDetails project : details) {

            projects.add(new Project(project));
        }

        return projects;
    }
}
