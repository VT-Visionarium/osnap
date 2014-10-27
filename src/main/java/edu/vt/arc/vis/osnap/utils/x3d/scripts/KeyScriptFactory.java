package edu.vt.arc.vis.osnap.utils.x3d.scripts;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import x3d.fields.SFBool;
import x3d.fields.SFString;
import x3d.model.AccessTypeNames;
import x3d.model.Field;
import x3d.model.FieldTypeName;
import x3d.model.Script;


/**
 * The {@link KeyScriptFactory} class provides methods to generate X3D
 * {@link Script} nodes which provide information on keys being pressed. These
 * scripts can either react to {@link ActionKey ActionKeys}, regular keys, or
 * both.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 */
public class KeyScriptFactory {

    /**
     * Generates an action key script with the provided definition for the
     * provided {@link ActionKey}.
     * 
     * @param def
     *            the definition
     * @param actionKey
     *            the {@link ActionKey}.
     * @return the generated X3D {@link Script} node.
     */
    public static Script generateActionKeyScript(String def, ActionKey actionKey) {

        return KeyScriptFactory.generateKeyScript(def, actionKey, null);
    }

    /**
     * Generates an action key script with the provided definition for the
     * provided key.
     * 
     * @param def
     *            the definition
     * @param key
     *            the key.
     * @return the generated X3D {@link Script} node.
     */
    public static Script generateKeyScript(String def, Character key) {

        return KeyScriptFactory.generateKeyScript(def, null, key);
    }

    /**
     * Generates a script for both an {@link ActionKey} and a regular key with
     * the provided definition..
     * 
     * @param def
     *            the definition
     * @param actionKeyFilter
     *            the {@link ActionKey}.
     * @param keyFilter
     *            the key.
     * @return the generated X3D {@link Script} node.
     */
    public static Script generateKeyScript(String def,
            ActionKey actionKeyFilter, Character keyFilter) {


        Script keyScript = new Script();
        keyScript.setDEF("KeyFilterScript");
        keyScript.setDirectOutput(SFBool.FALSE);
        keyScript.setMustEvaluate(SFBool.FALSE);

        if (keyFilter != null) {

            Field key = new Field();
            key.setName("key");
            key.setAccessType(AccessTypeNames.OUTPUT_ONLY);
            key.setType(FieldTypeName.SF_BOOL);
            Field keyPress = new Field();
            keyPress.setName("keyPress");
            keyPress.setAccessType(AccessTypeNames.INPUT_ONLY);
            keyPress.setType(FieldTypeName.SF_STRING);
            Field keyRelease = new Field();
            keyRelease.setName("keyRelease");
            keyRelease.setAccessType(AccessTypeNames.INPUT_ONLY);
            keyRelease.setType(FieldTypeName.SF_STRING);
            Field keyIndex = new Field();
            keyIndex.setName("keyIndex");
            keyIndex.setAccessType(AccessTypeNames.INITIALIZE_ONLY);
            keyIndex.setType(FieldTypeName.SF_STRING);
            keyIndex.setValue(new SFString(keyFilter.toString()));

            keyScript.getContent().add(key);
            keyScript.getContent().add(keyIndex);
            keyScript.getContent().add(keyPress);
            keyScript.getContent().add(keyRelease);
        }

        if (actionKeyFilter != null) {

            Field actionKey = new Field();
            actionKey.setName("actionKey");
            actionKey.setAccessType(AccessTypeNames.OUTPUT_ONLY);
            actionKey.setType(FieldTypeName.SF_BOOL);
            Field actionKeyPress = new Field();
            actionKeyPress.setName("actionKeyPress");
            actionKeyPress.setAccessType(AccessTypeNames.INPUT_ONLY);
            actionKeyPress.setType(FieldTypeName.SF_INT_32);
            Field actionKeyRelease = new Field();
            actionKeyRelease.setName("actionKeyRelease");
            actionKeyRelease.setAccessType(AccessTypeNames.INPUT_ONLY);
            actionKeyRelease.setType(FieldTypeName.SF_INT_32);
            Field actionKeyIndex = new Field();
            actionKeyIndex.setName("actionKeyIndex");
            actionKeyIndex.setAccessType(AccessTypeNames.INITIALIZE_ONLY);
            actionKeyIndex.setType(FieldTypeName.SF_INT_32);
            actionKeyIndex.setValue(new SFString("" + actionKeyFilter.index));

            keyScript.getContent().add(actionKey);
            keyScript.getContent().add(actionKeyIndex);
            keyScript.getContent().add(actionKeyPress);
            keyScript.getContent().add(actionKeyRelease);
        }

        StringBuilder script = new StringBuilder();

        InputStream is = KeyScriptFactory.class
                .getResourceAsStream("KeyFilterScript.js");

        String line = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {

            while ((line = in.readLine()) != null) {

                script.append(line);
                script.append("\n");
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        keyScript.setCdata(script.toString());



        return keyScript;

    }



    /**
     * The {@link ActionKey} enum provides an enumeration of supported special
     * keys.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * @since 1.0
     */
    public static enum ActionKey {
        /**
         * The F1 key.
         */
        F1(1),
        /**
         * The F2 key.
         */
        F2(2),
        /**
         * The F3 key.
         */
        F3(3),
        /**
         * The F4 key.
         */
        F4(4),
        /**
         * The F5 key.
         */
        F5(5),
        /**
         * The F6 key.
         */
        F6(6),
        /**
         * The F7 key.
         */
        F7(7),
        /**
         * The F8 key.
         */
        F8(8),
        /**
         * The F9 key.
         */
        F9(9),
        /**
         * The F10 key.
         */
        F10(10),
        /**
         * The F11 key.
         */
        F11(11),
        /**
         * The F12 key.
         */
        F12(12),
        /**
         * The Home key.
         */
        HOME(13),
        /**
         * The End key.
         */
        END(14),
        /**
         * The Page Up key.
         */
        PGUP(15),
        /**
         * The Page Down key.
         */
        PGDN(16),
        /**
         * The Up Arrow key.
         */
        UP(17),
        /**
         * The Down Arrow key.
         */
        DOWN(18),
        /**
         * The Left Arrow key.
         */
        LEFT(19),
        /**
         * The Right Arrow key.
         */
        RIGHT(20);

        private final int index;

        private ActionKey(int index) {

            this.index = index;
        }

        /**
         * Returns the {@link Integer} index of the {@link ActionKey}.
         * 
         * @return the {@link Integer} index of the {@link ActionKey}.
         */
        public int index() {

            return this.index;
        }
    }

}
