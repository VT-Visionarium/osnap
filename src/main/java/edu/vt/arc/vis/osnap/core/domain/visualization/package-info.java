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
 * This package contains the classes pertinent to the visual representation of
 * graphs.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlSchema(namespace = "edu.vt.arc.vis.osnap.core.domain.visualization", xmlns = { @XmlNs(namespaceURI = "edu.vt.arc.vis.osnap.core.domain.visualization", prefix = "vis") })
@XmlJavaTypeAdapters({ @XmlJavaTypeAdapter(value = XmlColorTypeAdapter.class, type = Color.class) })
package edu.vt.arc.vis.osnap.core.domain.visualization;

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


import javafx.scene.paint.Color;

import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jutility.io.xml.XmlColorTypeAdapter;
