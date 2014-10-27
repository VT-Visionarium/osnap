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
package edu.vt.arc.vis.osnap.utils.x3d.scripts;


import edu.vt.arc.vis.osnap.utils.x3d.X3DCreator;
import x3d.model.*;
import x3d.avalon.*;
import x3d.fields.*;


/**
 * 
 * @author Peter Radics
 * @version 1.0
 * @deprecated since 1.0
 */

public class CaveScriptGenerator {

    // <editor-fold defaultstate="collapsed" desc="Fields">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Properties">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed"
    // desc="Construction/Destruction/Initialization">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * @return the cave script.
     */
    public static Group createCAVEScript() {

        IOSensor iowand = new IOSensor();

        iowand.setType(new SFString("dtk-instantIOWand"));

        /*
         * <field name="dtkShmWandName" type="SFString"
         * accessType="initializeOnly" value="wand" /> <field
         * name="dtkShmJoystickName" type="SFString" accessType="initializeOnly"
         * value="joystick" /> <field name="dtkShmButtonsName" type="SFString"
         * accessType="initializeOnly" value="buttons" /> <field
         * name="meterRatio" type="SFFloat" accessType="initializeOnly"
         * value="1.524" />
         * 
         * <field name="wandmatrix" type="SFMatrix4f" accessType="outputOnly" />
         * <field name="wandrotation" type="SFRotation" accessType="outputOnly"
         * /> <field name="wandposition" type="SFVec3f" accessType="outputOnly"
         * /> <field name="joystick_x_axis" type="SFFloat"
         * accessType="outputOnly" /> <field name="joystick_y_axis"
         * type="SFFloat" accessType="outputOnly" />
         * 
         * <field name="button_1" type="SFBool" accessType="outputOnly" />
         * <field name="button_2" type="SFBool" accessType="outputOnly" />
         * <field name="button_3" type="SFBool" accessType="outputOnly" />
         * <field name="button_4" type="SFBool" accessType="outputOnly" />
         */
        NavigationInfo navigationInfo = new NavigationInfo();

        navigationInfo.getType().clear();
        navigationInfo.getType().add("WALK");
        navigationInfo.getType().add("ANY");

        /*
         * <NavigationInfo type='"WALK" "ANY"' interactionType="ray"
         * containerField='children' avatarSize='.25 1.75 .75'
         * visibilityLimit='0' speed='9' headlight='true'> <SteerNavigator
         * DEF='nav' inputRange="-1,1" rotationSpeed="0.2,0.2,0.2" />
         * </NavigationInfo>
         */
        SteerNavigator steerNavigator = new SteerNavigator();
        steerNavigator.setZeroDeflectionTrans(new SFVec3f(0f, 0f, 0f));
        steerNavigator.setZeroDeflectionRot(new SFVec3f(0f, 0f, 0f));
        steerNavigator.setRotationSpeed(new SFVec3f(0.2f, 0.2f, 0.2f));
        steerNavigator.getInputRange().getValue().clear();
        steerNavigator.getInputRange().add(new SFVec2f(-1f, 1f));

//
//        Script navmap = new Script();
//        X3DCreator.setX3DNodeName(navmap, null, "navmap");
//
//
//        Field button1press = new Field();
//        button1press.setName("button_press_1");
//        button1press.setType(FieldTypeName.SF_BOOL);
//        button1press.setAccessType(AccessTypeNames.INPUT_ONLY);
//        navmap.getContent().add(button1press);
//
//        Field button2press = new Field();
//        button2press.setName("button_press_2");
//        button2press.setType(FieldTypeName.SF_BOOL);
//        button2press.setAccessType(AccessTypeNames.INPUT_ONLY);
//        navmap.getContent().add(button2press);
//
//        Field button3press = new Field();
//        button3press.setName("button_press_3");
//        button3press.setType(FieldTypeName.SF_BOOL);
//        button3press.setAccessType(AccessTypeNames.INPUT_ONLY);
//        navmap.getContent().add(button3press);
//
//        Field button4press = new Field();
//        button4press.setName("button_press_4");
//        button4press.setType(FieldTypeName.SF_BOOL);
//        button4press.setAccessType(AccessTypeNames.INPUT_ONLY);
//        navmap.getContent().add(button4press);
//
//        Field joystickMoveX = new Field();
//        joystickMoveX.setName("joystick_move_x");
//        joystickMoveX.setType(FieldTypeName.SF_FLOAT);
//        joystickMoveX.setAccessType(AccessTypeNames.INPUT_ONLY);
//        navmap.getContent().add(joystickMoveX);
//
//        Field joystickMoveY = new Field();
//        joystickMoveY.setName("joystick_move_y");
//        joystickMoveY.setType(FieldTypeName.SF_FLOAT);
//        joystickMoveY.setAccessType(AccessTypeNames.INPUT_ONLY);
//        navmap.getContent().add(joystickMoveY);
//
//        Field wandRotationChanged = new Field();
//        wandRotationChanged.setName("wand_rotation_changed");
//        wandRotationChanged.setType(FieldTypeName.SF_ROTATION);
//        wandRotationChanged.setAccessType(AccessTypeNames.INPUT_ONLY);
//        navmap.getContent().add(wandRotationChanged);
//
//        Field joystickXAdjusted = new Field();
//        joystickXAdjusted.setName("wand_rotation_changed");
//        joystickXAdjusted.setType(FieldTypeName.SF_FLOAT);
//        joystickXAdjusted.setAccessType(AccessTypeNames.OUTPUT_ONLY);
//        navmap.getContent().add(joystickXAdjusted);
//
//        Field joystickYAdjusted = new Field();
//        joystickYAdjusted.setName("wand_rotation_changed");
//        joystickYAdjusted.setType(FieldTypeName.SF_FLOAT);
//        joystickYAdjusted.setAccessType(AccessTypeNames.OUTPUT_ONLY);
//        navmap.getContent().add(joystickYAdjusted);
//
//        Field translationX = new Field();
//        translationX.setName("translation_x");
//        translationX.setType(FieldTypeName.SF_FLOAT);
//        translationX.setAccessType(AccessTypeNames.OUTPUT_ONLY);
//        navmap.getContent().add(translationX);
//
//        Field translationY = new Field();
//        translationY.setName("translation_x");
//        translationY.setType(FieldTypeName.SF_FLOAT);
//        translationY.setAccessType(AccessTypeNames.OUTPUT_ONLY);
//        navmap.getContent().add(translationY);
//
//        Field translationZ = new Field();
//        translationZ.setName("translation_x");
//        translationZ.setType(FieldTypeName.SF_FLOAT);
//        translationZ.setAccessType(AccessTypeNames.OUTPUT_ONLY);
//        navmap.getContent().add(translationZ);
//
//        Field translationVector = new Field();
//        translationVector.setName("translation_vector");
//        translationVector.setType(FieldTypeName.SF_VEC_3_F);
//        translationVector.setAccessType(AccessTypeNames.OUTPUT_ONLY);
//        navmap.getContent().add(translationVector);
//
//        Field wandRotationNoRoll = new Field();
//        wandRotationNoRoll.setName("wand_rotation_noroll");
//        wandRotationNoRoll.setType(FieldTypeName.SF_ROTATION);
//        wandRotationNoRoll.setAccessType(AccessTypeNames.OUTPUT_ONLY);
//        navmap.getContent().add(wandRotationNoRoll);
//
//        Field navField = new Field();
//        navField.setName("nav");
//        navField.setType(FieldTypeName.SF_NODE);
//        navField.setAccessType(AccessTypeNames.INITIALIZE_ONLY);
//
//        SteerNavigator useNav = new SteerNavigator();
//        useNav.setUSE(steerNavigator);
//
//        navField.getChildObjects().add(useNav);
//        navmap.getContent().add(navField);
//
//
//
//        Field iowandField = new Field();
//        iowandField.setName("iowand");
//        iowandField.setType(FieldTypeName.SF_NODE);
//        iowandField.setAccessType(AccessTypeNames.INITIALIZE_ONLY);
//
//        IOSensor useIOWand = new IOSensor();
//        useIOWand.setUSE(iowand);
//
//        iowandField.getChildObjects().add(useIOWand);
//
//        navmap.getContent().add(iowandField);
//
//        Field colorChoiceField = new Field();
//        colorChoiceField.setName("colorChoice");
//        colorChoiceField.setType(FieldTypeName.SF_INT_32);
//        colorChoiceField.setAccessType(AccessTypeNames.OUTPUT_ONLY);
//
//        navmap.getContent().add(colorChoiceField);
//
//        navmap.getUrl()
//                .add("file:///Users/peter/Dropbox/Development/Projects/GraphVisualizer/src/edu.vt.arc.vis.osnap/utils/x3d/scripts/KeyEventHandlerScript.js");

        /*
         * <![CDATA[ecmascript:
         * 
         * var js_y = 0.0;
         * 
         * function button_press_1( value, time ) { if( value ) { //nav.message
         * = 'decreaseNavSpeed'; if (colorChoice == -1) { colorChoice = 0; }
         * else { colorChoice = -1; }
         * 
         * } } function button_press_2( value, time ) { if( value )
         * navField.message = 'resetViewPosition'; } function button_press_3(
         * value, time ) { if( value ) navField.message = 'increaseNavSpeed'; }
         * function button_press_4( value, time ) { if( value ) navField.message
         * = 'nextNavMode'; } function joystick_move_x( value, time ) {
         * joystick_x_adjusted = -value; } function joystick_move_y( value, time
         * ) { js_y = -value; if( js_y > 0.05 || js_y < -0.05 ) {
         * wand_rotation_noroll = iowand.wandrotation; wand_rotation_noroll.z =
         * 0.0; translation_vector = wand_rotation_noroll.multVec( SFVec3f( 0.0,
         * 0.0, js_y ) ); translation_x = translation_vector.x; translation_y =
         * translation_vector.y; translation_z = translation_vector.z; } else {
         * translation_x = 0.0; translation_y = 0.0; translation_z = 0.0; } }
         * 
         * function wand_rotation_changed( value, time ) { if( js_y > 0.05 ||
         * js_y < -0.05 ) { wand_rotation_noroll = iowand.wandrotation;
         * wand_rotation_noroll.z = 0.0; translation_vector =
         * wand_rotation_noroll.multVec( SFVec3f( 0.0, 0.0, js_y ) );
         * translation_x = translation_vector.x; translation_y =
         * translation_vector.y; translation_z = translation_vector.z; } else {
         * translation_x = 0.0; translation_y = 0.0; translation_z = 0.0; } }
         * 
         * ]]>
         */



        return null;
    }

    // </editor-fold>

}
