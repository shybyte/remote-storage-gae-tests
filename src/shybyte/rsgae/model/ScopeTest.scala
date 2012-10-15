package shybyte.rsgae.model

import org.junit.Assert._
import org.junit.Test
import shybyte.rsgae.model.Scope

@Test
class ScopeTest {
  
  @Test
  def test_ScopeFromScopeString {
     assertEquals(Scope("task","rw"),Scope("task:rw"))
     assertEquals(Scope("todos","r"),Scope("todos:r"))
     assertEquals(Scope("","rw"),Scope(":rw"))
  }
  
}