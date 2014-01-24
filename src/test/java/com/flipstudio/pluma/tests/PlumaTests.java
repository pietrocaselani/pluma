package com.flipstudio.pluma.tests;

import com.flipstudio.pluma.Database;
import com.flipstudio.pluma.ResultSet;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Pietro Caselani
 * On 13/01/14
 * Pluma
 */
@RunWith(JUnit4.class)
public class PlumaTests {
  //region Fields
  private static final File DATABASE_FILE = new File(System.getProperty("user.dir"), "TestDB.sqlite");
  private static final ArrayList<String> QUERIES = new ArrayList<String>();
  private Database mDatabase;
  //endregion

  //region Setup
  @Before public void setUp() throws Exception {
    if (DATABASE_FILE.exists() && !DATABASE_FILE.delete()) {
      throw new RuntimeException("Can not delete database file.");
    }

    mDatabase = Database.open(DATABASE_FILE.getPath());

    assertTrue("Can not open database.", mDatabase.isOpen() && DATABASE_FILE.exists());

    mDatabase.execute(
        "CREATE TABLE people (id INTEGER PRIMARY KEY, name TEXT, lastName text, birth datetime);\n"
            + "INSERT INTO people (name,lastName,birth) VALUES ('Jeremy','Xyla','1179129666000');\n"
            + "INSERT INTO people (name,lastName,birth) VALUES ('Damon','Althea','1029576914000');\n"
            + "INSERT INTO people (name,lastName,birth) VALUES ('Reese','Kalia','763347737000');");

    mDatabase.setDatabaseListener(new Database.DatabaseListener() {
      @Override public void onExecuteQuery(String query) {
        recordQuery(query);
      }
    });
  }
  //endregion

  //region Updates
  @Test public void testUpdate() throws Exception {
    startTest("Testing updates.");

    String insertListQuery = "INSERT INTO people (name, lastName, birth) VALUES (?, ?, ?)";

    ArrayList<Object> listArgs = new ArrayList<Object>();
    listArgs.add("Arden");
    listArgs.add("Winter");
    listArgs.add(new Date(638340461000L));

    assertTrue("Could not insert record.", mDatabase.executeUpdate(insertListQuery, listArgs));

    assertTrue("Could not insert second record.",
        mDatabase.executeUpdate(insertListQuery, "Damian", "Ifeoma", new Date(1021405116000L)));

    HashMap<String, Object> mapArgs = new HashMap<String, Object>();
    mapArgs.put("name", "Chaney");
    mapArgs.put("lastName", "Kathleen");
    mapArgs.put("birth", new Date(812710812000L));

    assertTrue("Could not insert third record.", mDatabase.executeUpdate(
        "INSERT INTO people (name, lastName, birth) VALUES (:name, :lastName, :birth)", mapArgs));

    assertQueries("INSERT INTO people (name, lastName, birth) VALUES (?, ?, ?)",
        "INSERT INTO people (name, lastName, birth) VALUES (?, ?, ?)",
        "INSERT INTO people (name, lastName, birth) VALUES (:name, :lastName, :birth)");
  }
  //endregion

  //region Queries
  @Test public void testQuery() throws Exception {
    startTest("Testing queries.");

    ResultSet rs = mDatabase.executeQuery("SELECT id, name, lastName, birth FROM people");

    assertEquals("Unexpected column count.", 4, rs.getColumnCount());

    ArrayList<HashMap<String, Object>> people = new ArrayList<HashMap<String, Object>>();
    while (rs.next()) {
      HashMap<String, Object> person = new HashMap<String, Object>();
      person.put("id", rs.getInt(0));
      person.put("name", rs.getString(1));
      person.put("lastName", rs.getString(2));
      person.put("birth", rs.getDate(3));
      people.add(person);
    }

    assertEquals("Unexpected people count.", 3, people.size());
    assertEquals("Unexpected person id.", 2, people.get(1).get("id"));
    assertEquals("Unexpected person name.", "Jeremy", people.get(0).get("name"));
    assertEquals("Unexpected person last name.", "Kalia", people.get(2).get("lastName"));
    assertEquals("Unexpected person birth.", new Date(763347737000L), people.get(2).get("birth"));

    rs = mDatabase.executeQuery("SELECT id, name FROM people WHERE id = ?", 2);
    assertEquals("Unexpected column count.", 2, rs.getColumnCount());
    if (rs.next()) {
      assertEquals("Unexpected name.", "Damon", rs.getString(1));
      assertEquals("Unexpected id.", 2, rs.getBigInteger(0).intValue());
    }

    Map<String, Object> args = new TreeMap<String, Object>();
    args.put("FirstName", "Damon");

    rs = mDatabase.executeQuery("SELECT id FROM people WHERE name = :FirstName", args);
    if (rs.next()) {
      assertEquals("Unexpected id.", 2, rs.getInt(0));
    }

    assertQueries(
        "SELECT id, name, lastName, birth FROM people",
        "SELECT id, name FROM people WHERE id = ?",
        "SELECT id FROM people WHERE name = :FirstName");
  }
  //endregion

  //region Last Insert Id
  @Test public void testLastInsertId() throws Exception {
    String insert = "INSERT INTO people (name, lastName, birth) VALUES (?, ?, ?)";

    boolean result = mDatabase.executeUpdate(insert, "Carl", "Ifeoma", 757399978000L);

    assertTrue("Can not insert record.", result);

    long lastId = mDatabase.getLastInsertId();

    assertEquals("Unexpected last id.", 4, lastId);
  }
  //endregion

  //region Exec
  @Test public void testExec() throws Exception {
    mDatabase.execute("PRAGMA foreign_keys = ON");

    ResultSet rs = mDatabase.executeQuery("PRAGMA foreign_keys");
    if (rs.next()) {
      assertEquals("Unexpected foreign keys.", 1, rs.getInt(0));
    }

    assertTrue("Can not close result set.", rs.close());

    mDatabase.execute("PRAGMA foreign_keys = OFF");

    rs = mDatabase.executeQuery("PRAGMA foreign_keys");
    if (rs.next()) {
      assertEquals("Unexpected foreign keys.", 0, rs.getInt(0));
    }

    assertTrue("Can not close result set.", rs.close());
  }
  //endregion

  //region Close
  @Test public void testClose() throws Exception {
    assertTrue("Database is not open.", mDatabase.isOpen());

    assertTrue("Can not close database.", mDatabase.close());

    assertTrue("Database is not closed.", mDatabase.isClosed());
  }
  //endregion

  //region Private
  private void startTest(String message) {
    QUERIES.clear();
    System.out.println(message);
  }

  private void assertQueries(String... queries) {
    assertEquals("Unexpected queries count", queries.length, QUERIES.size());

    for (int i = 0; i < queries.length; i++) {
      assertEquals("Unexpected query", queries[i].toLowerCase(), QUERIES.get(i).toLowerCase());
    }
  }

  private void recordQuery(String query) {
    System.out.println(">>> " + query);
    QUERIES.add(query);
  }
  //endregion
}