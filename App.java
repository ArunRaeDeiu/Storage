package com.img;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.util.HashMap;
import java.util.Map;

public class App {
public static String s="";
public static String e="";
  public static void main123(String str,String sr) {

    OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
    ODatabaseSession db = orient.open("BETTER", "root", "Computer");
    
    s=str;
    e=sr;
    createSchema(db);

    createImageUpl(db);
    System.out.println("Upload in DB sucessfully");
    db.close();
    orient.close();

  }

  private static void createSchema(ODatabaseSession db) {
    OClass person = db.getClass("ImageUpl");

    if (person == null) {
      person = db.createVertexClass("ImageUpl");
    }

    if (person.getProperty("path") == null) {
      person.createProperty("path", OType.STRING);
    }
    
    if (person.getProperty("name") == null) 
    {
        person.createProperty("name", OType.STRING);
    }

  }

  private static void createImageUpl(ODatabaseSession db) {
    OVertex alice = createImageUpl(db, s,e);
    alice.save();
   
  }

  private static OVertex createImageUpl(ODatabaseSession db, String name,String surname) {
    OVertex result = db.newVertex("ImageUpl");
   
    result.setProperty("filename", surname);
    result.setProperty("filepath", name);
    result.save();
    return result;
  }

}