/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.layout;


import edu.vt.arc.vis.osnap.graph.Universe;
import edu.vt.arc.vis.osnap.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.layout.complexComponents.SphereCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.layout.simpleComponents.SimpleColorLayoutComponent;
import edu.vt.arc.vis.osnap.layout.simpleComponents.SimpleLabelTextLayoutComponent;
import edu.vt.arc.vis.osnap.layout.simpleComponents.SimpleScaleLayoutComponent;
import edu.vt.arc.vis.osnap.layout.simpleComponents.SimpleShapeLayoutComponent;
import edu.vt.arc.vis.osnap.visualization.Shape;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.visualization.Visualization;

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
import org.jutility.math.geometry.Scale;


/**
 * Provides a layout (a set of visual properties) for a graph universe.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "Layout", propOrder = { "universe", "layoutComponents" })
@XmlAccessorType(XmlAccessType.NONE)
public class Layout {


    private final List<KeyValuePair<ILayoutComponent, VisualProperty>> layoutComponents;

    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final Universe                                             universe;

    private Visualization                                              visualization;


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
    private Layout() {

        this(null, true);
    }

    /**
     * Creates a new Layout for the universe provided.
     * 
     * @param universe
     *            the universe to be laid out.
     */
    public Layout(Universe universe) {

        this(universe, false);
    }

    /**
     * Creates a new Layout for the universe provided.
     * 
     * @param universe
     *            the universe to be laid out.
     * @param serialization
     *            <code>true</code>, if used for serialization;
     *            <code>false</code> otherwise.
     */
    private Layout(Universe universe, boolean serialization) {

        LayoutComponentRegistry.Instance();
        this.universe = universe;
        if (universe == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create layout without an associated Universe!");
        }

        this.layoutComponents = new LinkedList<>();

        if (universe != null) {
            this.visualization = new Visualization(universe);
        }
    }


    /**
     * Returns the List of Key-Value pairs of <code>ILayoutComponent</code> to
     * <code>VisualProperty</code>.
     * 
     * @return a list of <code>IVisualPropertyProviders</code>.
     */
    @XmlElementWrapper(name = "LayoutComponents")
    @XmlElement(name = "LayoutComponent", type = KeyValuePair.class)
    public List<KeyValuePair<ILayoutComponent, VisualProperty>> getLayoutComponents() {

//        System.out.println("Sonofa...");
        return this.layoutComponents;
    }

//    @SuppressWarnings("unused")
//    private void setLayoutComponents(
//            final List<KeyValuePair<ILayoutComponent, VisualProperty>> layoutComponents) {
//
//        System.out.println("In harr");
//        for (KeyValuePair<ILayoutComponent, VisualProperty> pair : layoutComponents) {
//
//            this.addLayoutProviderForVisualProperty(pair.getKey(),
//                    pair.getValue());
//        }
//    }

    /**
     * Adds a layout provider for a visual property.
     * 
     * @param provider
     *            the provider to be added.
     * @param property
     *            the property for which to add the provider.
     */
    public void addLayoutProviderForVisualProperty(ILayoutComponent provider,
            VisualProperty property) {

        if (provider == null) {
            throw new IllegalArgumentException("No layout component provided!");
        }

        if (property == null) {
            throw new IllegalArgumentException(
                    "Cannot register layout component without a visual property.");
        }

        if (!provider.providesCapabilities().contains(property)) {
            throw new IllegalArgumentException(
                    "Provided layout component cannot handle provided visual "
                            + "property!");
        }

        KeyValuePair<ILayoutComponent, VisualProperty> pair = new KeyValuePair<>(
                provider, property);
        if (!this.layoutComponents.contains(pair)) {

            this.layoutComponents.add(pair);
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
    public void removeLayoutProviderForVisualProperty(
            ILayoutComponent provider, VisualProperty property) {

        if (provider != null && property != null) {

            KeyValuePair<ILayoutComponent, VisualProperty> pair = new KeyValuePair<>(
                    provider, property);
            if (this.layoutComponents.remove(pair)) {

                provider.disable(property);
            }
        }

    }

    /**
     * Clears all layout providers.
     */
    public void clearLayoutProviders() {

        this.layoutComponents.clear();
    }


    /**
     * Applies the visual properties defined by this layout'scale
     * VisualPropertyProviders.
     */
    public void layout() {

        this.visualization = new Visualization(this.universe);


        Set<ILayoutComponent> activeLayoutComponents = new LinkedHashSet<>();

        for (KeyValuePair<ILayoutComponent, VisualProperty> pair : this.layoutComponents) {

            ILayoutComponent provider = pair.getKey();

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
    public static Layout defaultLayout(Universe universe) {

        Layout layout = new Layout(universe);


        ILayoutComponent defaultNodeColor = new SimpleColorLayoutComponent(
                Color.RED);
        defaultNodeColor.setName("Default Node Color (red)");

        ILayoutComponent defaultEdgeColor = new SimpleColorLayoutComponent(
                Color.GREEN);
        defaultEdgeColor.setName("Default Edge Color (green)");

        // ILayoutComponent defaultHyperEdgeColor = new
        // SimpleColorLayoutComponent(
        // Color.BLUE);
        // defaultHyperEdgeColor.setName("Default HyperEdge Color + (blue)");


        ILayoutComponent nodeLabelText = new SimpleLabelTextLayoutComponent();
        nodeLabelText.setName("Default Node Label Text (id)");

        // ILayoutComponent edgeLabelText = new
        // SimpleLabelTextLayoutComponent();
        // edgeLabelText.setName("Default Edge Label Text (id)");

        // ILayoutComponent hyperEdgeLabelText = new
        // SimpleLabelTextLayoutComponent();
        // hyperEdgeLabelText.setName("Default HyperEdge Label Text (id)");


        ILayoutComponent nodeScale = new SimpleScaleLayoutComponent(
                new Scale<>(layout.getVisualization().getPrecision()));
        nodeScale.setName("Default Node Scale (1, 1, 1)");
        ILayoutComponent edgeScale = new SimpleScaleLayoutComponent(
                new Scale<>(layout.getVisualization().getPrecision()));
        edgeScale.setName("Default Edge Scale (1, 1, 1)");
        // ILayoutComponent hyperEdgeScale = new SimpleScaleLayoutComponent(
        // new Scalef());
        // hyperEdgeScale.setName("Default HyperEdge Scale (1, 1, 1)");


        ILayoutComponent nodeShape = new SimpleShapeLayoutComponent(
                Shape.SPHERE);
        nodeShape.setName("Default Node Shape (Sphere)");
        ILayoutComponent edgeShape = new SimpleShapeLayoutComponent(
                Shape.CYLINDER);
        edgeShape.setName("Default Edge Shape (Cylinder)");
        // ILayoutComponent hyperEdgeShape = new SimpleShapeLayoutComponent(
        // Shape.CONE);
        // hyperEdgeShape.setName("Default HyperEdge Shape (Cone)");


        ILayoutComponent nodeCoordinates = new SphereCoordinateLayoutComponent();
        nodeCoordinates.setName("Default Node Coordinates (Spherical Layout)");


        layout.addLayoutProviderForVisualProperty(defaultNodeColor,
                VisualProperty.NODE_COLOR);
        layout.addLayoutProviderForVisualProperty(defaultEdgeColor,
                VisualProperty.EDGE_COLOR);
        // layout.addLayoutProviderForVisualProperty(defaultHyperEdgeColor,
        // VisualProperty.HYPEREDGE_COLOR);

        layout.addLayoutProviderForVisualProperty(nodeLabelText,
                VisualProperty.NODE_LABEL_TEXT);
        // layout.addLayoutProviderForVisualProperty(edgeLabelText,
        // VisualProperty.EDGE_LABEL_TEXT);
        // layout.addLayoutProviderForVisualProperty(hyperEdgeLabelText,
        // VisualProperty.HYPEREDGE_LABEL_TEXT);

        layout.addLayoutProviderForVisualProperty(nodeScale,
                VisualProperty.NODE_SCALE);
        layout.addLayoutProviderForVisualProperty(edgeScale,
                VisualProperty.EDGE_SCALE);
        // layout.addLayoutProviderForVisualProperty(hyperEdgeScale,
        // VisualProperty.HYPEREDGE_SCALE);

        layout.addLayoutProviderForVisualProperty(nodeShape,
                VisualProperty.NODE_SHAPE);
        layout.addLayoutProviderForVisualProperty(edgeShape,
                VisualProperty.EDGE_SHAPE);
        // layout.addLayoutProviderForVisualProperty(hyperEdgeShape,
        // VisualProperty.HYPEREDGE_SHAPE);


        layout.addLayoutProviderForVisualProperty(nodeCoordinates,
                VisualProperty.NODE_X_POSITION);
        layout.addLayoutProviderForVisualProperty(nodeCoordinates,
                VisualProperty.NODE_Y_POSITION);
        layout.addLayoutProviderForVisualProperty(nodeCoordinates,
                VisualProperty.NODE_Z_POSITION);

        return layout;

    }

    /**
     * Determines whether or not this <code>Layout</code> is identical to the
     * one provided.
     * 
     * @param other
     *            the other <code>Layout</code>.
     * @return <code>true</code> if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(Layout other) {

        if (this.equals(other)) {

            boolean sameUniverse = this.getUniverse().isIdentical(
                    other.getUniverse());

            boolean sameNumberOfLayoutComponents = this.getLayoutComponents()
                    .size() == other.getLayoutComponents().size();

            return sameUniverse && sameNumberOfLayoutComponents;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Layout) {
            Layout other = (Layout) obj;

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
