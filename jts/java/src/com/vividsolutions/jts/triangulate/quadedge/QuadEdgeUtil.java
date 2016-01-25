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

package com.vividsolutions.jts.triangulate.quadedge;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

/**
 * Utilities for working with {@link QuadEdge}s.
 * 
 * @author mbdavis
 * 
 */
public class QuadEdgeUtil 
{
	/**
	 * Gets all edges which are incident on the origin of the given edge.
	 * 
	 * @param start
	 *          the edge to start at
	 * @return a List of edges which have their origin at the origin of the given
	 *         edge
	 */
	public static List findEdgesIncidentOnOrigin(QuadEdge start) {
		List incEdge = new ArrayList();

		QuadEdge qe = start;
		do {
			incEdge.add(qe);
			qe = qe.oNext();
		} while (qe != start);

		return incEdge;
	}

}
