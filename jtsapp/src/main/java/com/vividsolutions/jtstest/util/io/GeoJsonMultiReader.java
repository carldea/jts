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

package com.vividsolutions.jtstest.util.io;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.geojson.GeoJsonReader;

public class GeoJsonMultiReader {
  private static final String GEOJSON_FEATURECOLLECTION = "FeatureCollection";
  private static final String GEOJSON_COORDINATES = "coordinates";
  private GeometryFactory geomFact;
  private GeoJsonReader rdr;

  public GeoJsonMultiReader(GeometryFactory geomFact) {
    this.geomFact = geomFact;
    rdr = new GeoJsonReader(geomFact);
  }
  
  public Geometry read(String s) throws ParseException {
    if (isFeatureCollection(s)) {
      return readFeatureCollection(s);
    }
    return readGeometry(s);
  }

  private Geometry readGeometry(String s) throws ParseException {
    // TODO: trim string to include only Geometry object
    return rdr.read(s);
  }

  /**
   * Extracts all Geometry object substrings and reads them
   * @param s
   * @throws ParseException 
   */
  private Geometry readFeatureCollection(String s) throws ParseException {
    Pattern p = Pattern.compile("\\{[^\\{\\}]+?\\}");
    Matcher m = p.matcher(s);
    List geoms = new ArrayList();
    while (true) {
      boolean isFound = m.find();
      if (! isFound) break;
      String substr = m.group();
      if (isGeometry(substr)) {
        geoms.add(readGeometry(substr));
      }
      //System.out.println(sgeom);
    }
    return geomFact.createGeometryCollection(GeometryFactory.toGeometryArray(geoms));
  }

  private boolean isGeometry(String s) {
    return s.indexOf(GEOJSON_COORDINATES) >= 0;
  }

  private static boolean isFeatureCollection(String s) {
    return s.indexOf(GEOJSON_FEATURECOLLECTION) >= 0;
  }
}
