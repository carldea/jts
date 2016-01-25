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

package test.jts.perf.operation.union;

import java.util.*;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.WKTFileReader;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import test.jts.*;

public class FileUnionPerfTest 
{
  static final int MAX_ITER = 1;

  static PrecisionModel pm = new PrecisionModel();
  static GeometryFactory fact = new GeometryFactory(pm, 0);
  static WKTReader wktRdr = new WKTReader(fact);
  static WKTWriter wktWriter = new WKTWriter();

  GeometryFactory factory = new GeometryFactory();
  
  public static void main(String[] args) {
    FileUnionPerfTest test = new FileUnionPerfTest();
    try {
      test.test();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  boolean testFailed = false;

  public FileUnionPerfTest() {
  }
  
  public void test()
  throws Exception
  {

//    test(TestFiles.DATA_DIR + "africa.wkt");
//    test(TestFiles.DATA_DIR + "world.wkt");
//    test(TestFiles.DATA_DIR + "bc-250k.wkt");
//    test(TestFiles.DATA_DIR + "bc_20K.wkt");
    
//    test("C:\\data\\martin\\proj\\jts\\data\\veg.wkt");

    test(TestFiles.DATA_DIR + "africa.wkt");
//    test(TestFiles.DATA_DIR + "world.wkt");
//    test("C:\\proj\\JTS\\test\\union\\npsa_albers.wkt");

  }
  
  public void test(String filename)
    throws Exception
  {
    WKTFileReader fileRdr = new WKTFileReader(filename, wktRdr);
    List polys = fileRdr.read();
    
    UnionPerfTester tester = new UnionPerfTester(polys);
    tester.runAll();
  }
      
}
