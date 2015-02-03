package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


// @formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseNodeLinkTreeLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseNodeLinkTreeLayout.Orientation;


/**
 * The {@code NodeLinkTreeConfiguration} class provides the
 * {@link IConfiguration Configuration} for the
 * {@link PrefuseNodeLinkTreeLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class PrefuseNodeLinkTreeLayoutConfiguration
        extends PrefuseTreeLayoutConfiguration<PrefuseNodeLinkTreeLayout> {

    private Orientation orientation;

    private double      depthSpacing;
    private double      spaceBetweenSiblings;
    private double      spaceBetweenNeighboringSubtrees;


    /**
     * Returns the orientation.
     * 
     * @return the orientation.
     */
    public Orientation getOrientation() {

        return this.orientation;
    }

    /**
     * Sets the orientation.
     * 
     * @param orientation
     *            the orientation.
     */
    public void setOrientation(final Orientation orientation) {

        if (orientation == null) {

            this.orientation = Orientation.LEFT_TO_RIGHT;
        }
        this.orientation = orientation;
    }


    /**
     * Returns the depth spacing.
     * 
     * @return the depth spacing.
     */
    public double getDepthSpacing() {

        return this.depthSpacing;
    }

    /**
     * Sets the depth spacing.
     * 
     * @param depthSpacing
     *            the depth spacing.
     */
    public void setDepthSpacing(final double depthSpacing) {

        this.depthSpacing = depthSpacing;
    }


    /**
     * Returns the space between siblings.
     * 
     * @return the space between siblings.
     */
    public double getSpaceBetweenSiblings() {

        return this.spaceBetweenSiblings;
    }

    /**
     * Sets the space between siblings.
     * 
     * @param spaceBetweenSiblings
     *            the space between siblings.
     */
    public void setSpaceBetweenSiblings(double spaceBetweenSiblings) {

        this.spaceBetweenSiblings = spaceBetweenSiblings;
    }


    /**
     * Returns the space between neighboring sub-trees.
     * 
     * @return the space between neighboring sub-trees.
     */
    public double getSpaceBetweenNeighboringSubtrees() {

        return this.spaceBetweenNeighboringSubtrees;
    }

    /**
     * Sets the space between neighboring sub-trees.
     * 
     * @param spaceBetweenNeighboringSubtrees
     *            the space between neighboring sub-trees.
     */
    public void setSpaceBetweenNeighboringSubtrees(
            double spaceBetweenNeighboringSubtrees) {

        this.spaceBetweenNeighboringSubtrees = spaceBetweenNeighboringSubtrees;
    }



    /**
     * Creates a new instance of the {@code NodeLinkTreeStatus} class.
     */
    public PrefuseNodeLinkTreeLayoutConfiguration() {

        super(PrefuseNodeLinkTreeLayout.name(), PrefuseNodeLinkTreeLayout
                .description());

        this.orientation = Orientation.LEFT_TO_RIGHT;
        this.depthSpacing = 50.0;
        this.spaceBetweenSiblings = 5.0;
        this.spaceBetweenNeighboringSubtrees = 25.0;
    }



    @Override
    public PrefuseNodeLinkTreeLayout createConfiguredObject() {

        return new PrefuseNodeLinkTreeLayout(this.getRootNode(),
                this.getOrientation(), this.getDepthSpacing(),
                this.getSpaceBetweenSiblings(),
                this.getSpaceBetweenNeighboringSubtrees());
    }
}
