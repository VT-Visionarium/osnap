package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


// @formatter:off
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
// @formatter:on

import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout;


/**
 * The {@code TieredOrbitalLayoutConfiguration} class provides the
 * {@link IConfiguration Configuration} for the {@link TieredOrbitalLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class TieredOrbitalLayoutConfiguration
        extends TreeLayoutConfiguration<TieredOrbitalLayout> {


    private Metadata metadata;
    private boolean  invertPathToRootNode;
    private boolean  ignoreEdgeDirection;

    private float    minimalDistance;
    private float    maximumNodeRadius;
    private float    depthModifier;

    /**
     * Returns the {@link Metadata}.
     * 
     * @return the {@link Metadata}.
     */
    public Metadata getMetadata() {

        return this.metadata;
    }

    /**
     * Sets the {@link Metadata}.
     * 
     * @param metadata
     *            the {@link Metadata}.
     */
    public void setMetadata(final Metadata metadata) {

        this.metadata = metadata;
    }


    /**
     * Returns whether the path to the root node is inverted.
     * 
     * @return whether the path to the root node is inverted.
     */
    public boolean isInvertPathToRootNode() {

        return this.invertPathToRootNode;
    }


    /**
     * Sets whether the path to the root node is inverted.
     * 
     * @param invertPathToRootNode
     *            whether the path to the root node is inverted.
     */
    public void setInvertPathToRootNode(boolean invertPathToRootNode) {

        this.invertPathToRootNode = invertPathToRootNode;
    }


    /**
     * Returns whether edge direction is ignored.
     * 
     * @return whether edge direction is ignored.
     */
    public boolean isIgnoreEdgeDirection() {

        return this.ignoreEdgeDirection;
    }


    /**
     * Sets whether edge direction is ignored.
     * 
     * @param ignoreEdgeDirection
     *            whether edge direction is ignored.
     */
    public void setIgnoreEdgeDirection(boolean ignoreEdgeDirection) {

        this.ignoreEdgeDirection = ignoreEdgeDirection;
    }


    /**
     * Returns the minimal distance.
     * 
     * @return the minimal distance.
     */
    public float getMinimalDistance() {

        return this.minimalDistance;
    }


    /**
     * Sets the minimal distance.
     * 
     * @param minimalDistance
     *            the minimal distance.
     */
    public void setMinimalDistance(final float minimalDistance) {

        this.minimalDistance = minimalDistance;
    }


    /**
     * Returns the maximum node radius.
     * 
     * @return the maximum node radius.
     */
    public float getMaximumNodeRadius() {

        return this.maximumNodeRadius;
    }


    /**
     * Sets the maximum node radius.
     * 
     * @param maximumNodeRadius
     *            the maximum node radius.
     */
    public void setMaximumNodeRadius(final float maximumNodeRadius) {

        this.maximumNodeRadius = maximumNodeRadius;
    }


    /**
     * Returns the depth modifier.
     * 
     * @return the depth modifier.
     */
    public float getDepthModifier() {

        return this.depthModifier;
    }


    /**
     * Sets the depth modifier.
     * 
     * @param depthModifier
     *            the depth modifier.
     */
    public void setDepthModifier(final float depthModifier) {

        this.depthModifier = depthModifier;
    }



    /**
     * Creates a new instance of the {@code TieredOrbitalLayoutConfiguration}
     * class.
     */
    public TieredOrbitalLayoutConfiguration() {

        super(TieredOrbitalLayout.name(), TieredOrbitalLayout.description());

        this.metadata = null;

        this.invertPathToRootNode = false;
        this.ignoreEdgeDirection = true;
        this.minimalDistance = 1f;
        this.maximumNodeRadius = 1f;
        this.depthModifier = 50f;
    }


    @Override
    public TieredOrbitalLayout createConfiguredObject() {

        return new TieredOrbitalLayout(this.getRootNode(), this.getMetadata(),
                this.getMinimalDistance(), this.getMaximumNodeRadius(),
                this.getDepthModifier(), this.isInvertPathToRootNode(),
                isIgnoreEdgeDirection());
    }
}
