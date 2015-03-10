package edu.vt.arc.vis.osnap.core.domain.layout;


import edu.vt.arc.vis.osnap.core.domain.DomainObject;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SphericalLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleLabelTextLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleScaleLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleShapeLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.Shape;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javafx.scene.paint.Color;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.map.KeyValuePair;
import org.jutility.math.geometry.ScaleFactor;;


/**
 * Provides a layout set (a set of visual properties) for a graph universe.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "LayoutSet", propOrder = { "universe", "layouts" })
@XmlAccessorType(XmlAccessType.NONE)
public class LayoutSet
        extends DomainObject {


    private final List<KeyValuePair<ILayout, VisualProperty>> layouts;

    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final Universe                                    universe;

    private Visualization                                     visualization;


    /**
     * Returns the universe for which the layout is generated.
     * 
     * @return the universe for which the layout is generated.
     */
    public Universe getUniverse() {

        return this.universe;
    }

    /**
     * Returns the visualization generated by this layout.
     * 
     * @return the visualization.
     */
    public Visualization getVisualization() {

        if (this.visualization == null) {

            this.visualization = new Visualization(this.universe);
        }
        return this.visualization;
    }

    @SuppressWarnings("unused")
    private LayoutSet() {

        this(null, true);
    }

    /**
     * Creates a new {@code LayoutSet} for the universe provided.
     * 
     * @param universe
     *            the universe to be laid out.
     */
    public LayoutSet(Universe universe) {

        this(universe, false);
    }

    /**
     * Creates a new LayoutSet for the universe provided.
     * 
     * @param universe
     *            the universe to be laid out.
     * @param serialization
     *            {@code true}, if used for serialization; {@code false}
     *            otherwise.
     */
    private LayoutSet(Universe universe, boolean serialization) {

        LayoutRegistry.Instance();
        this.universe = universe;
        if (universe == null && !serialization) {
            
            throw new IllegalArgumentException(
                    "Cannot create layout without an associated Universe!");
        }

        this.layouts = new LinkedList<>();

        if (universe != null) {

            this.visualization = new Visualization(universe);
        }
    }


    /**
     * Returns the List of Key-Value pairs of {@link ILayout} to
     * {@link VisualProperty}.
     * 
     * @return the List of Key-Value pairs of {@link ILayout} to
     *         {@link VisualProperty}.
     */
    @XmlElementWrapper(name = "Layouts")
    @XmlElement(name = "Layout", type = KeyValuePair.class)
    public List<KeyValuePair<ILayout, VisualProperty>> getLayouts() {

        return this.layouts;
    }


    /**
     * Adds a {@link ILayout} for a {@link VisualProperty}.
     * 
     * @param provider
     *            the {@link ILayout}.
     * @param property
     *            the {@link VisualProperty}.
     */
    public void addLayoutForVisualProperty(ILayout provider,
            VisualProperty property) {

        if (provider == null) {

            throw new IllegalArgumentException("No layout provided!");
        }

        if (property == null) {

            throw new IllegalArgumentException(
                    "Cannot register layout without a visual property.");
        }

        if (!provider.providesCapabilities().contains(property)) {

            throw new IllegalArgumentException(
                    "Provided layout cannot handle provided visual "
                            + "property!");
        }

        KeyValuePair<ILayout, VisualProperty> pair = new KeyValuePair<>(
                provider, property);
        if (!this.layouts.contains(pair)) {

            this.layouts.add(pair);
            provider.enable(property);
        }

    }


    /**
     * Removes a layout provider for a visual property.
     * 
     * @param provider
     *            the provider to be removed.
     * @param property
     *            the property for which to remove the provider.
     */
    public void removeLayoutProviderForVisualProperty(ILayout provider,
            VisualProperty property) {

        if (provider != null && property != null) {

            KeyValuePair<ILayout, VisualProperty> pair = new KeyValuePair<>(
                    provider, property);
            if (this.layouts.remove(pair)) {

                provider.disable(property);
            }
        }

    }

    /**
     * Clears all layout providers.
     */
    public void clearLayoutProviders() {

        this.layouts.clear();
    }


    /**
     * Applies the visual properties defined by this layout'scale
     * VisualPropertyProviders.
     */
    public void layout() {

        this.visualization = new Visualization(this.universe);


        Set<ILayout> activeLayoutComponents = new LinkedHashSet<>();

        for (KeyValuePair<ILayout, VisualProperty> pair : this.layouts) {

            ILayout provider = pair.getKey();

            if (!activeLayoutComponents.contains(provider)) {

                activeLayoutComponents.add(provider);


                provider.layout(this.visualization);
            }
        }


    }


    /**
     * Creates a layout with default values.
     * 
     * @param universe
     *            the universe to layout visually.
     * @return the default layout.
     */
    public static LayoutSet defaultLayout(Universe universe) {

        LayoutSet layoutSet = new LayoutSet(universe);


        ILayout defaultNodeColor = new SimpleColorLayout(Color.RED);
        defaultNodeColor.setName("Default Node Color (red)");

        ILayout defaultEdgeColor = new SimpleColorLayout(Color.GREEN);
        defaultEdgeColor.setName("Default Edge Color (green)");

        // ILayout defaultHyperEdgeColor = new
        // SimpleColorLayout(
        // Color.BLUE);
        // defaultHyperEdgeColor.setName("Default HyperEdge Color + (blue)");


        ILayout nodeLabelText = new SimpleLabelTextLayout();
        nodeLabelText.setName("Default Node Label Text (id)");

        // ILayout edgeLabelText = new
        // SimpleLabelTextLayout();
        // edgeLabelText.setName("Default Edge Label Text (id)");

        // ILayout hyperEdgeLabelText = new
        // SimpleLabelTextLayout();
        // hyperEdgeLabelText.setName("Default HyperEdge Label Text (id)");


        ILayout nodeScale = new SimpleScaleLayout(
                new ScaleFactor<>(layoutSet.getVisualization().getPrecision()));

        nodeScale.setName("Default Node Scale (1, 1, 1)");
        ILayout edgeScale = new SimpleScaleLayout(
                new ScaleFactor<>(layoutSet.getVisualization().getPrecision()));
        edgeScale.setName("Default Edge Scale (1, 1, 1)");
        // ILayout hyperEdgeScale = new SimpleScaleLayout(
        // new Scalef());
        // hyperEdgeScale.setName("Default HyperEdge Scale (1, 1, 1)");


        ILayout nodeShape = new SimpleShapeLayout(Shape.SPHERE);
        nodeShape.setName("Default Node Shape (Sphere)");
        ILayout edgeShape = new SimpleShapeLayout(Shape.CYLINDER);
        edgeShape.setName("Default Edge Shape (Cylinder)");
        // ILayout hyperEdgeShape = new SimpleShapeLayout(
        // Shape.CONE);
        // hyperEdgeShape.setName("Default HyperEdge Shape (Cone)");


        ILayout nodeCoordinates = new SphericalLayout();
        nodeCoordinates
                .setName("Default Node Coordinates (Spherical LayoutSet)");


        layoutSet.addLayoutForVisualProperty(defaultNodeColor,
                VisualProperty.NODE_COLOR);
        layoutSet.addLayoutForVisualProperty(defaultEdgeColor,
                VisualProperty.EDGE_COLOR);
        // layout.addLayoutProviderForVisualProperty(defaultHyperEdgeColor,
        // VisualProperty.HYPEREDGE_COLOR);

        layoutSet.addLayoutForVisualProperty(nodeLabelText,
                VisualProperty.NODE_LABEL_TEXT);
        // layout.addLayoutProviderForVisualProperty(edgeLabelText,
        // VisualProperty.EDGE_LABEL_TEXT);
        // layout.addLayoutProviderForVisualProperty(hyperEdgeLabelText,
        // VisualProperty.HYPEREDGE_LABEL_TEXT);

        layoutSet.addLayoutForVisualProperty(nodeScale,
                VisualProperty.NODE_SCALE);
        layoutSet.addLayoutForVisualProperty(edgeScale,
                VisualProperty.EDGE_SCALE);
        // layout.addLayoutProviderForVisualProperty(hyperEdgeScale,
        // VisualProperty.HYPEREDGE_SCALE);

        layoutSet.addLayoutForVisualProperty(nodeShape,
                VisualProperty.NODE_SHAPE);
        layoutSet.addLayoutForVisualProperty(edgeShape,
                VisualProperty.EDGE_SHAPE);
        // layout.addLayoutProviderForVisualProperty(hyperEdgeShape,
        // VisualProperty.HYPEREDGE_SHAPE);


        layoutSet.addLayoutForVisualProperty(nodeCoordinates,
                VisualProperty.NODE_X_POSITION);
        layoutSet.addLayoutForVisualProperty(nodeCoordinates,
                VisualProperty.NODE_Y_POSITION);
        layoutSet.addLayoutForVisualProperty(nodeCoordinates,
                VisualProperty.NODE_Z_POSITION);

        return layoutSet;

    }

    /**
     * Determines whether or not this {@code LayoutSet} is identical to the one
     * provided.
     * 
     * @param other
     *            the other {@code LayoutSet}.
     * @return {@code true} if they are identical; {@code false} otherwise.
     */
    public boolean isIdentical(LayoutSet other) {

        if (this.equals(other)) {

            boolean sameUniverse = this.getUniverse().isIdentical(
                    other.getUniverse());

            boolean sameNumberOfLayoutComponents = this.getLayouts().size() == other
                    .getLayouts().size();

            return sameUniverse && sameNumberOfLayoutComponents;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof LayoutSet) {
            LayoutSet other = (LayoutSet) obj;

            boolean sameUniverse = this.getUniverse().equals(
                    other.getUniverse());

            return sameUniverse;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        hash += 37 * universe.hashCode();

        return hash;
    }
}
