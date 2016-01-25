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

package com.vividsolutions.jtslab;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jtslab.snapround.GeometrySnapRounder;

public class SnapRoundFunctions {
  /**
   * Reduces precision pointwise, then snap-rounds.
   * Note that output set may not contain non-unique linework
   * (and thus cannot be used as input to Polygonizer directly).
   * UnaryUnion is one way to make the linework unique.
   * 
   * 
   * @param geom a geometry containing linework to node
   * @param scaleFactor the precision model scale factor to use
   * @return the noded, snap-rounded linework
   */
  public static Geometry snapRoundLines(
      Geometry geom, double scaleFactor) {
    PrecisionModel pm = new PrecisionModel(scaleFactor);

    GeometrySnapRounder gsr = new GeometrySnapRounder(pm);
    gsr.setLineworkOnly(true);
    Geometry snapped = gsr.execute(geom);
    return snapped;
  }
  
  public static Geometry snapRound(
      Geometry geomA, Geometry geomB, 
      double scaleFactor) {
    PrecisionModel pm = new PrecisionModel(scaleFactor);

    Geometry geom = geomA;
    
    if (geomB != null) {
      geom = geomA.getFactory().createGeometryCollection(new Geometry[] { geomA, geomB });
    }
    
    GeometrySnapRounder gsr = new GeometrySnapRounder(pm);
    Geometry snapped = gsr.execute(geom);
    return snapped;
  }
  

}
