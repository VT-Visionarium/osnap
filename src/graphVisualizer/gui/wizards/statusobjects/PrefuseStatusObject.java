/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package graphVisualizer.gui.wizards.statusobjects;


import org.jutility.math.geometry.IRectangle2;
import org.jutility.math.vectorAlgebra.IPoint2;

import graphVisualizer.layout.prefuseComponents.IPrefuseLayoutComponent;


/**
 * The abstract{@link PrefuseStatusObject} class provides a wrapper around the
 * common parameters of {@link IPrefuseLayoutComponent Prefuse layout
 * componentsF}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public abstract class PrefuseStatusObject
        extends BaseStatusObject {

    /**
     * Returns the duration.
     * 
     * @return the duration.
     */
    public Long getDuration() {

        if (this.getLayoutComponent() != null) {
            return this.getLayoutComponent().getLayout().getDuration();
        }
        return null;
    }

    /**
     * Sets the duration.
     * 
     * @param duration
     *            the duration.
     */
    public void setDuration(long duration) {

        if (this.getLayoutComponent() != null) {
            this.getLayoutComponent().getLayout().getDuration();

            setChanged();
            notifyObservers(getDuration());
        }
    }

    /**
     * Returns the layout anchor.
     * 
     * @return the layout anchor.
     */
    public IPoint2<Double> getLayoutAnchor() {

        if (this.getLayoutComponent() != null) {
            return this.getLayoutComponent().getLayoutAnchor();

        }
        return null;
    }

    /**
     * Sets the layout anchor.
     * 
     * @param x
     *            the x coordinate of the layout anchor.
     * @param y
     *            the y coordinate of the layout anchor.
     */
    public void setLayoutAnchor(double x, double y) {

        if (this.getLayoutComponent() != null) {
            this.getLayoutComponent().setLayoutAnchor(x, y);
            setChanged();
            notifyObservers(getLayoutAnchor());
        }
    }

    /**
     * Returns the layout bounds.
     * 
     * @return the layout bounds.
     */
    public IRectangle2<Double> getLayoutBounds() {

        if (this.getLayoutComponent() != null) {
            return this.getLayoutComponent().getLayoutBounds();
        }
        return null;
    }

    /**
     * Sets the layout bounds.
     * 
     * @param x
     *            the x coordinate of the top left corner of the layout bounds.
     * @param y
     *            the y coordinate of the top left corner of the layout bounds.
     * @param width
     *            the width of the layout bounds.
     * @param height
     *            the height of the layout bounds.
     */
    public void setLayoutBounds(double x, double y, double width, double height) {

        if (this.getLayoutComponent() != null) {
            this.getLayoutComponent().setLayoutBounds(x, y, width, height);
            setChanged();
            notifyObservers(getLayoutBounds());

        }
    }

    /**
     * Returns whether or not the margins are set.
     * 
     * @return <code>true</code>, if all four margins are set;
     *         <code>false</code> otherwise.
     */
    public boolean areMarginsSet() {

        boolean topMarginSet = this.getTopMargin() != null;
        boolean bottomMarginSet = this.getBottomMargin() != null;
        boolean leftMarginSet = this.getLeftMargin() != null;
        boolean rightMarginSet = this.getRightMargin() != null;

        return topMarginSet && bottomMarginSet && leftMarginSet
                && rightMarginSet;
    }

    /**
     * Returns the top margin.
     * 
     * @return the top margin.
     */
    public Integer getTopMargin() {

        if (this.getLayoutComponent() != null) {
            return this.getLayoutComponent().getTopMargin();
        }
        return null;
    }

    /**
     * Returns the bottom margin.
     * 
     * @return the bottom margin.
     */
    public Integer getBottomMargin() {

        if (this.getLayoutComponent() != null) {
            return this.getLayoutComponent().getBottomMargin();
        }
        return null;
    }

    /**
     * Returns the left margin.
     * 
     * @return the left margin.
     */
    public Integer getLeftMargin() {

        if (this.getLayoutComponent() != null) {
            return this.getLayoutComponent().getLeftMargin();
        }
        return null;
    }

    /**
     * Returns the right margin.
     * 
     * @return the right margin.
     */
    public Integer getRightMargin() {

        if (this.getLayoutComponent() != null) {
            return this.getLayoutComponent().getRightMargin();
        }
        return null;
    }

    /**
     * Sets the margin of the layout within the layout bounds.
     * 
     * @param topMargin
     *            the top margin.
     * @param bottomMargin
     *            the bottom margin.
     * @param leftMargin
     *            the left margin.
     * @param rightMargin
     *            the right margin.
     */
    public void setMargins(int topMargin, int bottomMargin, int leftMargin,
            int rightMargin) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setMargin(topMargin, bottomMargin,
                    leftMargin, rightMargin);
            setChanged();
            notifyObservers(getLayoutBounds());

        }
    }



    /**
     * Overrides the getter for the layout component for convenience.
     */
    @Override
    public IPrefuseLayoutComponent getLayoutComponent() {

        if (super.getLayoutComponent() instanceof IPrefuseLayoutComponent) {

            return (IPrefuseLayoutComponent) super.getLayoutComponent();
        }
        return null;
    }
}
