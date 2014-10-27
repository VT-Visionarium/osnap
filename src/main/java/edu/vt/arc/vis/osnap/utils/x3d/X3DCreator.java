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


/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.vt.arc.vis.osnap.utils.x3d;


import java.util.Arrays;

import x3d.fields.*;
import x3d.model.*;



/**
 * 
 * @author peter
 * @deprecated
 */
public class X3DCreator {


    /**
     * Creates the angle-axis rotation projecting the firstVector onto the secondVector.
     *
     * @param firstVector The vector to be projected.
     * @param secondVector The vector to be projected on.
     * @return The angle-axis rotation.
     */
    public static SFRotation getAngleAxisRotationBetweenVectors (SFVec3d firstVector, SFVec3d secondVector) {
        
        SFVec3d normalizedFirstVector = firstVector.clone();
        SFVec3d normalizedSecondVector = secondVector.clone();

        if (normalizedFirstVector.length() != 1.0) {
            normalizedFirstVector.normalize();
        }

        if (normalizedSecondVector.length() != 1.0) {
            normalizedSecondVector.normalize();
        }

        Double angle = Math.acos(normalizedFirstVector.dotProduct(normalizedSecondVector));

        SFVec3d axis = normalizedFirstVector.crossProduct(normalizedSecondVector);
        return new SFRotation(SFVec3f.convert(axis), angle.floatValue());

   }

        /**
     * Creates the angle-axis rotation projecting the firstVector onto the secondVector.
     *
     * @param firstVector The vector to be projected.
     * @param secondVector The vector to be projected on.
     * @return The angle-axis rotation.
     */
    public static SFRotation getAngleAxisRotationBetweenVectors (
            SFVec3f firstVector, SFVec3f secondVector) {
        return X3DCreator.getAngleAxisRotationBetweenVectors (
                SFVec3d.convert(firstVector), SFVec3d.convert(secondVector));

   }

        /**
     * Creates the angle-axis rotation projecting the firstVector onto the secondVector.
     *
     * @param firstVector The vector to be projected.
     * @param secondVector The vector to be projected on.
     * @return The angle-axis rotation.
     */
    public static SFRotation getAngleAxisRotationBetweenVectors (SFVec4d firstVector, SFVec4d secondVector) {

        SFVec4d normalizedFirstVector = firstVector.clone();
        SFVec4d normalizedSecondVector = secondVector.clone();

        if (normalizedFirstVector.length() != 1.0) {
            normalizedFirstVector.normalize();
        }

        if (normalizedSecondVector.length() != 1.0) {
            normalizedSecondVector.normalize();
        }

        Double angle = Math.acos(normalizedFirstVector.dotProduct(normalizedSecondVector));

        SFVec4d axis = normalizedFirstVector.crossProduct(normalizedSecondVector);
        SFVec4f floatAxis = SFVec4f.convert(axis);
        return new SFRotation(floatAxis.getFirstValue(), floatAxis.getSecondValue(),
                floatAxis.getThirdValue(), angle.floatValue());

   }

        /**
     * Creates the angle-axis rotation projecting the firstVector onto the secondVector.
     *
     * @param firstVector The vector to be projected.
     * @param secondVector The vector to be projected on.
     * @return The angle-axis rotation.
     */
    public static SFRotation getAngleAxisRotationBetweenVectors (SFVec4f firstVector, SFVec4f secondVector) {

        return X3DCreator.getAngleAxisRotationBetweenVectors (
                SFVec4d.convert(firstVector), SFVec4d.convert(secondVector));

   }
}
