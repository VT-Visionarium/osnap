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


package graphVisualizer.graph.common;


import java.util.Set;


/**
 * The {@link IGraphObject} interface provides common functionality of all
 * graph-related objects. Currently, this only contains a unique identifier.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IGraphObject {

    /**
     * Returns the unique identifier of this IGraphObject.
     * 
     * @return the unique identifier.
     */
    public abstract String getID();

    /**
     * Sets the unique identifier of this IGraphObject.
     * 
     * @param id
     *            the unique identifier.
     */
    public void setID(String id);

    /**
     * Returns the GraphObjectProperties supported by this {@link IGraphObject}.
     * 
     * @return a {@link Set} of supported {@link GraphObjectProperty
     *         GraphObjectProperties}.
     */
    public Set<GraphObjectProperty> hasGraphProperties();

    /**
     * Returns the value of the provided {@link GraphObjectProperty} (if
     * supported).
     * 
     * @param graphObjectProperty
     *            the graphObjectProperty for which the value is queried.
     * @return the value of the {@link GraphObjectProperty} or <code>null</code>
     *         , if the GraphObjectProperty is not supported by the
     *         IGraphObject.
     */
    public Object getValueOfGraphProperty(
            GraphObjectProperty graphObjectProperty);


    /**
     * Determines whether this {@link IGraphObject} is identical to the
     * {@link IGraphObject} provided.
     * 
     * @param other
     *            the other {@link IGraphObject}.
     * @return <code>true</code>, if the {@link IGraphObject IGraphObjects} are
     *         identical; <code>false</code> otherwise.
     */
    public abstract boolean isIdentical(IGraphObject other);
}
