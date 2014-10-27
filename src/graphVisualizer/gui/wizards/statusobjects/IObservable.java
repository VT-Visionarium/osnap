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


/**
 * 
 */
package graphVisualizer.gui.wizards.statusobjects;


import java.util.Observer;


/**
 * @author Shawn P Neuman
 * 
 */
public interface IObservable {

    /**
     * Adds an observer to the set of observers for this object, provided that
     * it is not the same as some observer already in the set.
     * 
     * @param o
     */
    void addObserver(Observer o);

    /**
     * Indicates that this object has no longer changed, or that it has already
     * notified all of its observers of its most recent change, so that the
     * hasChanged method will now return false.
     */
    public void clearChanged();

    /**
     * Returns the number of observers of this Observable object.
     * 
     * @return the number of observers.
     */
    public int countObservers();

    /**
     * 
     Deletes an observer from the set of observers of this object.
     * 
     * @param o
     *            the observer to delete.
     */
    public void deleteObserver(Observer o);

    /**
     * Clears the observer list so that this object no longer has any
     * {@link Observer Observers}.
     */
    public void deleteObservers();

    /**
     * 
     Tests if this object has changed.
     * 
     * @return <code>true</code>, if the object was changed; <code>false</code>
     *         otherwise.
     */
    public boolean hasChanged();

    /**
     * If this object has changed, as indicated by the hasChanged method, then
     * notify all of its observers and then call the clearChanged method to
     * indicate that this object has no longer changed.
     */
    public void notifyObservers();

    /**
     * If this object has changed, as indicated by the hasChanged method, then
     * notify all of its observers and then call the clearChanged method to
     * indicate that this object has no longer changed.
     * 
     * @param changedObject
     *            the changed object.
     */
    public void notifyObservers(Object changedObject);

    /**
     * Marks this Observable object as having been changed; the hasChanged
     * method will now return true.
     */
    public void setChanged();

}
