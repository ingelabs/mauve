/* Copyright (C) 1999  Hewlett-Packard Company

   This file is part of Mauve.

   Mauve is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   Mauve is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Mauve; see the file COPYING.  If not, write to
   the Free Software Foundation, 59 Temple Place - Suite 330,
   Boston, MA 02111-1307, USA.
*/

// Tags: JLS1.0

package gnu.testlet.java.lang.Boolean;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class BooleanTest implements Testlet
{
  protected static TestHarness harness;

  public void test_Basics()
    {
      if ( !Boolean.TRUE.equals(new Boolean(true)) ||
	   !Boolean.FALSE.equals(new Boolean(false)) )		
	harness.fail("Error: test_Basics failed - 1" );
      
      Boolean b1 = new Boolean( true );
      Boolean b2 = new Boolean( false );
      
      if ( b1.booleanValue() != true || b2.booleanValue() != false )
	harness.fail("Error: test_Basics failed - 2" );
      
      Boolean bs1 = new Boolean ("True");
      Boolean bs2 = new Boolean ("False");
      Boolean bs3 = new Boolean ("true");
      Boolean bs4 = new Boolean ("hi");
      Boolean bs5 = new Boolean ("");
      
      if ( bs1.booleanValue() != true || bs2.booleanValue() != false ||
	   bs3.booleanValue() != true || bs4.booleanValue() != false ||
	   bs5.booleanValue() != false )
	harness.fail("Error: test_Basics failed - 3" );
      
      if ( (!bs1.toString().equals("true")) ||
	   (!bs2.toString().equals("false")) )
	harness.fail("Error: test_Basics failed - 4" );
    }
  
  public void test_equals()
    {
      Boolean b1 = new Boolean(true);
      Boolean b2 = new Boolean(false);
      
      if ( b1.equals(new Integer(4)))
	harness.fail("Error: test_equals failed - 1" );
      
      if ( b1.equals(null))
	harness.fail("Error: test_equals failed - 2" );
      
      if ( b1.equals( b2 ))
	harness.fail("Error: test_equals failed - 3" );
      
      if ( !b1.equals( new Boolean(true) ))
	harness.fail("Error: test_equals failed - 4" );
    }
  
  public void test_hashCode( )
    {
      Boolean b1 = new Boolean(true);
      Boolean b2 = new Boolean(false);
      
      if ( b1.hashCode() != 1231 || b2.hashCode() != 1237 )
	harness.fail("Error: test_hashCode returned wrong results" );
      
    }
  
  public void test_booleanValue( )
    {
      Boolean b1 = new Boolean(true);
      Boolean b2 = new Boolean(false);
      
      if ( b1.booleanValue() != true  || b2.booleanValue() != false )
	harness.fail("Error: test_booleanValue returned wrong results" );
      
    }
  
  public void test_valueOf( )
    {
      if ( !Boolean.valueOf("True").booleanValue() || 
	   !Boolean.valueOf("true").booleanValue() ||
	   Boolean.valueOf("anc").booleanValue())			 
	harness.fail("Error: test_valueOf returned wrong results" );
    }
  
  public void test_getBoolean( )
    {
      java.util.Properties  prop = System.getProperties();
      prop.put("booleankey1" , "true" );
      prop.put("booleankey2" , "false" );
      prop.put("booleankey3" , "hi" );
      
      System.setProperties(prop);
      
      if ( Boolean.getBoolean("booleankey1") != true ||
	   Boolean.getBoolean("booleankey2") != false ||
	   Boolean.getBoolean("booleankey3") != false )
	harness.fail("Error: test_getBoolean returned wrong results" );
    }
  
  
  
  public void testall()
    {
      test_Basics();
      test_equals();
      test_hashCode();
      test_booleanValue();
      test_valueOf();
      test_getBoolean();
    }
  
  public void test (TestHarness the_harness)
    {
      harness = the_harness;
      testall ();
    }
}
