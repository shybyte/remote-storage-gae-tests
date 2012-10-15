package shybyte.rsgae.model

import org.junit.Assert._
import org.junit.Test
import shybyte.rsgae.model.AuthTokenUtil._

@Test
class AuthTokenTest {

  @Test
  def test_isValidForPath {
    val authToken = generateAuthToken("username", Seq("tasks:rw", "contacts:r"))
    assertEquals(true, authToken.isValidForPath("/username/tasks/", "GET"))
    assertEquals(true, authToken.isValidForPath("/username/tasks/123", "PUT"))
    assertEquals(true, authToken.isValidForPath("/username/tasks/123", "DELETE"))
    assertEquals(false, authToken.isValidForPath("/otheruser/tasks/123", "PUT"))
    assertEquals(true, authToken.isValidForPath("/username/contacts/123", "GET"))
    assertEquals(false, authToken.isValidForPath("/username/contacts/123", "PUT"))
  }

  @Test
  def test_isValidForPath_for_root_scope {
    val authTokenReadAll = generateAuthToken("username", Seq(":r"))
    assertEquals(true, authTokenReadAll.isValidForPath("/username/", "GET"))
    assertEquals(true, authTokenReadAll.isValidForPath("/username/123", "GET"))
    assertEquals(false, authTokenReadAll.isValidForPath("/otheruser/", "GET"))
    
    val authTokenWriteAll = generateAuthToken("username", Seq(":rw"))

    assertEquals(true, authTokenWriteAll.isValidForPath("/username/", "PUT"))
    assertEquals(true, authTokenWriteAll.isValidForPath("/username/123", "PUT"))
    assertEquals(false, authTokenWriteAll.isValidForPath("/otheruser/", "PUT"))
  }

}