/*
 * The JTS Topology Suite is a collection of Java classes that
 * implement the fundamental operations required to validate a given
 * geo-spatial data set to a known topological specification.
 * 
 * Copyright (C) 2016 Vivid Solutions
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html), and the Vivid Solutions BSD
 * License v1.0 (found at the root of the repository).
 * 
 */
package com.vividsolutions.jtstest.testbuilder.ui.tools;

import java.awt.*;
import java.awt.event.MouseEvent;

//import com.vividsolutions.jtstest.testbuilder.IconLoader;
import com.vividsolutions.jtstest.testbuilder.AppCursors;
import com.vividsolutions.jtstest.testbuilder.model.*;

/**
 * @version 1.7
 */
public abstract class AbstractDrawTool extends LineBandTool {

	protected AbstractDrawTool() {
	  super(AppCursors.DRAW_GEOM);
	}

	protected abstract int getGeometryType();

	public void mouseClicked(MouseEvent e) {
		setBandType();
		super.mouseClicked(e);
	}

	protected void bandFinished() throws Exception {
		setType();
		geomModel().addComponent(getCoordinates());
		panel().updateGeom();
	}

	private void setType() {
		if (panel().getModel() == null)
			return;
		panel().getGeomModel().setGeometryType(getGeometryType());
	}

	private void setBandType() {
		int geomType = getGeometryType();
		setCloseRing(geomType == GeometryType.POLYGON);
	}
}
