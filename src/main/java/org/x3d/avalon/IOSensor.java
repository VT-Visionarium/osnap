/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.x3d.avalon;

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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.x3d.fields.MFString;
import org.x3d.fields.SFInt32;
import org.x3d.fields.SFString;
import org.x3d.fields.SFTime;
import org.x3d.model.X3DSensorNode;

/**
 * 
 * @author Peter Radics
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "IOSensor")
public class IOSensor extends X3DSensorNode {

	// <editor-fold defaultstate="collapsed" desc="Fields">

	@XmlAttribute(name = "system")
	protected SFString system;

	@XmlAttribute(name = "type")
	protected SFString type;
	@XmlAttribute(name = "name")
	protected SFString name;

	@XmlAttribute(name = "triggerName")
	protected SFString triggerName;
	@XmlAttribute(name = "maxValuesPerTrigger")
	protected SFInt32 maxValuesPerTrigger;
	@XmlAttribute(name = "triggerSlot")
	protected SFTime triggerSlot;
	@XmlAttribute(name = "logFeature")
	protected MFString logFeature;

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Properties">

	public SFString getSystem() {
		if (system == null) {
			system = new SFString("auto");
		}
		return system;
	}

	public void setSystem(SFString system) {
		this.system = system;
	}

	public SFString getType() {
		if (type == null) {
			type = new SFString();
		}
		return type;
	}

	public void setType(SFString type) {
		this.type = type;
	}

	public SFString getName() {
		if (name == null) {
			name = new SFString();
		}
		return name;
	}

	public void setName(SFString name) {
		this.name = name;
	}

	public SFString getTriggerName() {
		if (triggerName == null) {
			triggerName = new SFString();
		}
		return triggerName;
	}

	public void setTriggerName(SFString triggerName) {
		this.triggerName = triggerName;
	}

	public SFInt32 getMaxValuesPerTrigger() {
		if (maxValuesPerTrigger == null) {
			maxValuesPerTrigger = new SFInt32(1);
		}

		return maxValuesPerTrigger;
	}

	public void setMaxValuesPerTrigger(SFInt32 maxValuesPerTrigger) {
		this.maxValuesPerTrigger = maxValuesPerTrigger;
	}

	public SFTime getTriggerSlot() {
		if (triggerSlot == null) {
			triggerSlot = new SFTime();
		}
		return triggerSlot;
	}

	public void setTriggerSlot(SFTime triggerSlot) {
		this.triggerSlot = triggerSlot;
	}

	public MFString getLogFeature() {
		if (logFeature == null) {
			logFeature = new MFString();
		}
		return logFeature;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Construction/Destruction/Initialization">

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Public Methods">

	// </editor-fold>

}
