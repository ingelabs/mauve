/* ElementStructure8.java -- 
   Copyright (C) 2006 Red Hat
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

*/

// Tags: JDK1.4

package gnu.testlet.javax.swing.text.DefaultStyledDocument.ElementBuffer;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;

public class ElementStructure8 extends DefaultStyledDocument implements Testlet
{
  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    h2 = harness;
    try
      {
        /* TEST 0 *////////////////////////////////////////////////////////////
        harness.checkPoint("Test 0");
        ElementStructure8 doc = new ElementStructure8();
        Element root = doc.getDefaultRootElement();
        doc.insertString(0, "first line of text. \n", null);
        harness.check(root.getElementCount() == 2);
        harness.check(root.getElement(0).getStartOffset() == 0);
        harness.check(root.getElement(0).getEndOffset() == 21);
        harness.check(root.getElement(1).getStartOffset() == 21);
        harness.check(root.getElement(1).getEndOffset() == 22);
        doc.insertString
          (21, "second line of text. \n third line of text. \n", null);
        harness.check(root.getElementCount() == 4);
        harness.check(root.getElement(0).getElementCount() == 1);
        harness.check(root.getElement(1).getElementCount() == 1);
        harness.check(root.getElement(2).getElementCount() == 1);

        Element first = root.getElement(0).getElement(0);
        harness.check(first.getStartOffset() == 0);
        harness.check(first.getEndOffset() == 21);

        Element second = root.getElement(1).getElement(0);
        harness.check(second.getStartOffset() == 21);
        harness.check(second.getEndOffset() == 43);

        Element third = root.getElement(2).getElement(0);
        harness.check(third.getStartOffset() == 43);
        harness.check(third.getEndOffset() == 65);

        Element fourth = root.getElement(3).getElement(0);
        harness.check(fourth.getStartOffset() == 65);
        harness.check(fourth.getEndOffset() == 66);
        
        //printElements(doc.getDefaultRootElement(), 0);
        
        /* TEST 1 *////////////////////////////////////////////////////////////
        harness.checkPoint("Test 1");
        doc = new ElementStructure8();
        root = doc.getDefaultRootElement();
        doc.insertString(0, "first line of text. \n", null);
        harness.check(root.getElementCount() == 2);
        harness.check(root.getElement(0).getStartOffset() == 0);
        harness.check(root.getElement(0).getEndOffset() == 21);
        harness.check(root.getElement(1).getStartOffset() == 21);
        harness.check(root.getElement(1).getEndOffset() == 22);
        doc.insertString(21, "second line of text. \n ", null);
        harness.check(root.getElementCount() == 3);
        harness.check(root.getElement(0).getElementCount() == 1);
        harness.check(root.getElement(1).getElementCount() == 1);
        harness.check(root.getElement(2).getElementCount() == 2);

        first = root.getElement(0).getElement(0);
        harness.check(first.getStartOffset() == 0);
        harness.check(first.getEndOffset() == 21);

        second = root.getElement(1).getElement(0);
        harness.check(second.getStartOffset() == 21);
        harness.check(second.getEndOffset() == 43);

        third = root.getElement(2).getElement(0);
        harness.check(third.getStartOffset() == 43);
        harness.check(third.getEndOffset() == 44);
        
        fourth = root.getElement(2).getElement(1);
        harness.check(fourth.getStartOffset() == 44);
        harness.check(fourth.getEndOffset() == 45);
        
        //printElements(doc.getDefaultRootElement(), 0);

        /* TEST 2 *////////////////////////////////////////////////////////////
        harness.checkPoint("Test 2");
        doc = new ElementStructure8();
        root = doc.getDefaultRootElement();
        doc.insertString(0, "first line of text.", null);
        harness.check(root.getElementCount() == 1);
        harness.check(root.getElement(0).getStartOffset() == 0);
        harness.check(root.getElement(0).getEndOffset() == 20);
        doc.insertString(5, "second line \n of text.", null);
        harness.check(root.getElementCount() == 2);
        harness.check(root.getElement(0).getElementCount() == 1);
        harness.check(root.getElement(1).getElementCount() == 1);

        first = root.getElement(0).getElement(0);
        harness.check(first.getStartOffset() == 0);
        harness.check(first.getEndOffset() == 18);

        second = root.getElement(1).getElement(0);
        harness.check(second.getStartOffset() == 18);
        harness.check(second.getEndOffset() == 42);
        
        //printElements(doc.getDefaultRootElement(), 0);
        
        /* TEST 3 *////////////////////////////////////////////////////////////
        harness.checkPoint("Test 3");
        doc = new ElementStructure8();
        root = doc.getDefaultRootElement();
        doc.insertString(0, "first line of text. \n", null);
        harness.check(root.getElementCount() == 2);
        harness.check(root.getElement(0).getStartOffset() == 0);
        harness.check(root.getElement(0).getEndOffset() == 21);
        harness.check(root.getElement(1).getStartOffset() == 21);
        harness.check(root.getElement(1).getEndOffset() == 22);
        doc.insertString
          (21, "\n second line of text. \n third line of text. \n", null);
        harness.check(root.getElementCount() == 5);
        harness.check(root.getElement(0).getElementCount() == 1);
        harness.check(root.getElement(1).getElementCount() == 1);
        harness.check(root.getElement(2).getElementCount() == 1);
        harness.check(root.getElement(3).getElementCount() == 1);

        first = root.getElement(0).getElement(0);
        harness.check(first.getStartOffset() == 0);
        harness.check(first.getEndOffset() == 21);

        second = root.getElement(1).getElement(0);
        harness.check(second.getStartOffset() == 21);
        harness.check(second.getEndOffset() == 22);

        third = root.getElement(2).getElement(0);
        harness.check(third.getStartOffset() == 22);
        harness.check(third.getEndOffset() == 45);

        fourth = root.getElement(3).getElement(0);
        harness.check(fourth.getStartOffset() == 45);
        harness.check(fourth.getEndOffset() == 67);
        
        Element fifth = root.getElement(4).getElement(0);
        harness.check(fifth.getStartOffset() == 67);
        harness.check(fifth.getEndOffset() == 68);
        
        //printElements(doc.getDefaultRootElement(), 0);
        
        /* TEST 4 *////////////////////////////////////////////////////////////
        harness.checkPoint("Test 4");
        doc = new ElementStructure8();
        root = doc.getDefaultRootElement();
        doc.insertString
          (0, "\n second line of text. \n third line of text. \n", null);
        harness.check(root.getElementCount() == 4);
        harness.check(root.getElement(0).getElementCount() == 1);
        harness.check(root.getElement(1).getElementCount() == 1);
        harness.check(root.getElement(2).getElementCount() == 1);
        harness.check(root.getElement(3).getElementCount() == 1);

        first = root.getElement(0).getElement(0);
        harness.check(first.getStartOffset() == 0);
        harness.check(first.getEndOffset() == 1);

        second = root.getElement(1).getElement(0);
        harness.check(second.getStartOffset() == 1);
        harness.check(second.getEndOffset() == 24);

        third = root.getElement(2).getElement(0);
        harness.check(third.getStartOffset() == 24);
        harness.check(third.getEndOffset() == 46);

        fourth = root.getElement(3).getElement(0);
        harness.check(fourth.getStartOffset() == 46);
        harness.check(fourth.getEndOffset() == 47);
        
        //printElements(doc.getDefaultRootElement(), 0);
        
        /* TEST 5 *////////////////////////////////////////////////////////////
        harness.checkPoint("Test 5");
        doc = new ElementStructure8();
        root = doc.getDefaultRootElement();
        doc.insertString(0, "first line of text.", null);
        harness.check(root.getElementCount() == 1);
        harness.check(root.getElement(0).getStartOffset() == 0);
        harness.check(root.getElement(0).getEndOffset() == 20);
        doc.insertString(20, "\n third line of text.", null);
        harness.check(root.getElementCount() == 3);
        harness.check(root.getElement(0).getElementCount() == 1);
        harness.check(root.getElement(1).getElementCount() == 1);
        harness.check(root.getElement(2).getElementCount() == 1);

        first = root.getElement(0).getElement(0);
        harness.check(first.getStartOffset() == 0);
        harness.check(first.getEndOffset() == 20);

        second = root.getElement(1).getElement(0);
        harness.check(second.getStartOffset() == 20);
        harness.check(second.getEndOffset() == 21);

        third = root.getElement(2).getElement(0);
        harness.check(third.getStartOffset() == 21);
        harness.check(third.getEndOffset() == 41);
        
        //printElements(doc.getDefaultRootElement(), 0);
      }
    catch (Exception t)
      {
        // t.printStackTrace();
        harness.debug(t);
      }
    catch (AssertionError e)
      {
        e.printStackTrace();
        harness.debug(e);
      }
  }
  
  static TestHarness h2;

  static int numInserts = 0;

  static int numLeaves = 0;

  static int numBranches = 0;

  // We override the constructor so we can explicitly set the type of the
  // buffer to be our ElementBuffer2, allowing us to test some internals.
  public ElementStructure8()
  {
    super();
    buffer = new ElementBuffer2(createDefaultRoot());
  }

  // Overriding this method allows us to check that the right number
  // of newLines was encountered and that the event has the proper
  // offset and length.
  protected void insertUpdate(DefaultDocumentEvent ev, AttributeSet attr)
  {
    int l = ev.getLength();
    int o = ev.getOffset();
    if (numInserts == 0)
      {
        h2.checkPoint("first doc event");
        h2.check(o == 0);
        h2.check(l == 21);
      }
    else if (numInserts == 1)
    {
      h2.checkPoint("second doc event");
      h2.check(o == 21);      
      h2.check(l == 44);
    } 
    else if (numInserts == 2)
      {
        h2.checkPoint("third doc event");
        h2.check(o == 0);      
        h2.check(l == 21);
      } 
    else if (numInserts == 3)
      {
        h2.checkPoint("fourth doc event");
        h2.check(o == 21);      
        h2.check(l == 23);
      } 
    else if (numInserts == 4)
      {
        h2.checkPoint("fifth doc event");
        h2.check(o == 0);      
        h2.check(l == 19);
      } 
    else if (numInserts == 5)
      {
        h2.checkPoint("sixth doc event");
        h2.check(o == 5);      
        h2.check(l == 22);
      } 
    else if (numInserts == 6)
      {
        h2.checkPoint("seventh doc event");
        h2.check(o == 0);      
        h2.check(l == 21);
      } 
    else if (numInserts == 7)
      {
        h2.checkPoint("eighth doc event");
        h2.check(o == 21);      
        h2.check(l == 46);
      } 
    else if (numInserts == 8)
      {
        h2.checkPoint("ninth doc event");
        h2.check(o == 0);      
        h2.check(l == 46);
      } 
    else if (numInserts == 9)
      {
        h2.checkPoint("tenth doc event");
        h2.check(o == 0);      
        h2.check(l == 19);
      }
    else if (numInserts == 10)
      {
        h2.checkPoint("eleventh doc event");
        h2.check(o == 20);      
        h2.check(l == 21);
      } 

    else
      h2.fail ("too many calls to DefaultStyledDocument.insertUpdate");

    super.insertUpdate(ev, attr);
  }

  // Overriding this method allows us to check that the proper LeafElements
  // are being created.
  protected Element createLeafElement(Element parent, AttributeSet a, int p0,
                                      int p1)
  {
    numLeaves++;
    if (numLeaves == 1)
      {
        h2.checkPoint("create first leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 21);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 22);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("parent Element should have children, but has none.");
            h2.fail("parent Element should have children, but has none.");
          }        
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 2)
      {
        h2.checkPoint("create second leaf element");
        h2.check(p0 == 21);
        h2.check(p1 == 22);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 3)
      {
        h2.checkPoint("create third leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 21);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 65);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 4)
      {
        h2.checkPoint("create fourth leaf element");
        h2.check(p0 == 21);
        h2.check(p1 == 43);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 5)
      {
        h2.checkPoint("create fifth leaf element");
        h2.check(p0 == 43);
        h2.check(p1 == 65);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 6)
      {
        h2.checkPoint("create sixth leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 21);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 22);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 7)
      {
        h2.checkPoint("create seventh leaf element");
        h2.check(p0 == 21);
        h2.check(p1 == 22);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 8)
      {
        h2.checkPoint("create eighth leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 21);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 44);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 9)
      {
        h2.checkPoint("create ninth leaf element");
        h2.check(p0 == 21);
        h2.check(p1 == 43);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 10)
      {
        h2.checkPoint("create tenth leaf element");
        h2.check(p0 == 43);
        h2.check(p1 == 44);
        try
          {
            h2.check(parent.getStartOffset() == 44);
            h2.check(parent.getEndOffset() == 45);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 11)
      {
        h2.checkPoint("create eleventh leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 18);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 42);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 12)
      {
        h2.checkPoint("create twelfth leaf element");
        h2.check(p0 == 27);
        h2.check(p1 == 42);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 13)
      {
        h2.checkPoint("create thirteenth leaf element");
        h2.check(p0 == 18);
        h2.check(p1 == 42);
        try
          {
            h2.check(parent.getStartOffset() == 27);
            h2.check(parent.getEndOffset() == 42);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 14)
      {
        h2.checkPoint("create fourteenth leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 21);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 22);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 15)
      {
        h2.checkPoint("create fifteenth leaf element");
        h2.check(p0 == 21);
        h2.check(p1 == 22);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 16)
      {
        h2.checkPoint("create sixteenth leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 21);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 67);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 17)
      {
        h2.checkPoint("create seventeeth leaf element");
        h2.check(p0 == 21);
        h2.check(p1 == 22);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 18)
      {
        h2.checkPoint("create eighteenth leaf element");
        h2.check(p0 == 22);
        h2.check(p1 == 45);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 19)
      {
        h2.checkPoint("create nineteenth leaf element");
        h2.check(p0 == 45);
        h2.check(p1 == 67);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 20)
      {
        h2.checkPoint("create twentieth leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 1);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 47);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 21)
      {
        h2.checkPoint("create twenty-first leaf element");
        h2.check(p0 == 1);
        h2.check(p1 == 24);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 22)
      {
        h2.checkPoint("create twenty-second leaf element");
        h2.check(p0 == 24);
        h2.check(p1 == 46);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 23)
      {
        h2.checkPoint("create twenty-third leaf element");
        h2.check(p0 == 46);
        h2.check(p1 == 47);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 24)
      {
        h2.checkPoint("create twenty-fourth leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 20);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 41);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("branch element should have children, but has none");
            h2.fail("branch element should have children, but has none");
          }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 25)
      {
        h2.checkPoint("create twenty-fifth leaf element");
        h2.check(p0 == 20);
        h2.check(p1 == 21);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 26)
      {
        h2.checkPoint("create twenty-sixth leaf element");
        h2.check(p0 == 21);
        h2.check(p1 == 41);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }

    else      
      h2.fail ("too many leaf elements created");
    return super.createLeafElement(parent, a, p0, p1);
  }

  // Overriding this method allows us to check that the proper BranchElements
  // are being created.
  protected Element createBranchElement(Element parent, AttributeSet a)
  {    
    numBranches ++;
    if (numBranches == 1)
      {
        h2.checkPoint("create first branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 22);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numBranches == 2)
      {
        h2.checkPoint("create second branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 66);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 3)
      {
        h2.checkPoint("create third branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 66);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 4)
      {
        h2.checkPoint("create fourth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 22);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 5)
      {
        h2.checkPoint("create fifth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 45);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 6)
      {
        h2.checkPoint("create sixth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 42);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 7)
      {
        h2.checkPoint("create seventh branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 22);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 8)
      {
        h2.checkPoint("create eighth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 68);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 9)
      {
        h2.checkPoint("create ninth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 68);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 10)
      {
        h2.checkPoint("create tenth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 68);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 11)
      {
        h2.checkPoint("create eleventh branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 47);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 12)
      {
        h2.checkPoint("create twelfth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 47);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 13)
      {
        h2.checkPoint("create thirteenth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 47);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 14)
      {
        h2.checkPoint("create fourteenth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 41);
        h2.check(a.getAttributeCount() == 0); 
      }
    else if (numBranches == 15)
      {
        h2.checkPoint("create fifteenth branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 41);
        h2.check(a.getAttributeCount() == 0); 
      }
    else
      h2.fail ("too many branch elements created");
    return super.createBranchElement(parent, a);
  }

  // Prints some spaces.
  public static void pad(int pad)
  {
    for (int i = 0; i < pad; i++)
      System.out.print(" ");
  }

  // Displays the Element hierarchy starting with <code>start</code>.
  // This is just debugging code.
  public static void printElements(Element start, int pad)
  {
    pad(pad);
    if (pad == 0)
      System.out.println("ROOT ELEMENT (" + start.getStartOffset() + ", "
                         + start.getEndOffset() + ")");
    else if (start instanceof AbstractDocument.BranchElement)
      System.out.println("BranchElement (" + start.getStartOffset() + ", "
                         + start.getEndOffset() + ")");
    else
      {
        try
          {
            System.out.println("LeafElement ("
                               + start.getStartOffset()
                               + ", "
                               + start.getEndOffset()
                               + "): "
                               + start.getAttributes().getAttributeCount()
                               + ": "
                               + start.getDocument().
                               getText(
                                       start.getStartOffset(),
                                       start.getEndOffset()
                                       - start.getStartOffset()));
          }
        catch (BadLocationException ble)
          {
          }
      }
    for (int i = 0; i < start.getElementCount(); i++)
      printElements(start.getElement(i), pad + 3);
  }

  // A class to be the buffer of the styled document.
  // This allows us to check that some values are correct internally within
  // the ElementBuffer.
  public class ElementBuffer2 extends ElementBuffer
  {
    public ElementBuffer2(Element root)
    {
      super(root);
    }

    // This method allows us to check that the ElementSpecs generated by 
    // DefaultStyledDocument.insertUpdate are correct.
    protected void insertUpdate(ElementSpec[] data)
    {
      numInserts++;
      if (numInserts == 1)
        {
          h2.checkPoint("ElementBuffer insertUpdate: first insertion");
          h2.check(data.length == 3);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 21);

          h2.check(data[1].getType() == ElementSpec.EndTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);

          h2.check(data[2].getType() == ElementSpec.StartTagType);
          h2.check
            (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 0);
        }
      else if (numInserts == 2)
        {
          h2.checkPoint("ElementBuffer insertUpdate: second insertion");
          h2.check (data.length == 8);
          h2.check(data[0].getType() == ElementSpec.EndTagType);
          h2.check(data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 0);
          
          h2.check(data[1].getType() == ElementSpec.StartTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);          
          
          h2.check(data[2].getType() == ElementSpec.ContentType);
          h2.check(data[2].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 22);
          
          h2.check(data[3].getType() == ElementSpec.EndTagType);
          h2.check(data[3].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 0);
          
          h2.check(data[4].getType() == ElementSpec.StartTagType);
          h2.check(data[4].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[4].getOffset() == 0);
          h2.check(data[4].getLength() == 0);
          
          h2.check(data[5].getType() == ElementSpec.ContentType);
          h2.check(data[5].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[5].getOffset() == 0);
          h2.check(data[5].getLength() == 22);          

          h2.check(data[6].getType() == ElementSpec.EndTagType);
          h2.check(data[6].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[6].getOffset() == 0);
          h2.check(data[6].getLength() == 0);          

          h2.check(data[7].getType() == ElementSpec.StartTagType);
          h2.check(data[7].getDirection() == ElementSpec.JoinNextDirection);
          h2.check(data[7].getOffset() == 0);
          h2.check(data[7].getLength() == 0);          
        }
      else if (numInserts == 3)
        {
          h2.checkPoint("ElementBuffer insertUpdate: third insertion");
          h2.check(data.length == 3);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 21);
          
          h2.check(data[1].getType() == ElementSpec.EndTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);
          
          h2.check(data[2].getType() == ElementSpec.StartTagType);
          h2.check
            (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 0); 
        }
      else if (numInserts == 4)
        {
          h2.checkPoint("ElementBuffer insertUpdate: fourth insertion");
          h2.check (data.length == 6);
          h2.check(data[0].getType() == ElementSpec.EndTagType);
          h2.check(data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 0);
          
          h2.check(data[1].getType() == ElementSpec.StartTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);          
          
          h2.check(data[2].getType() == ElementSpec.ContentType);
          h2.check(data[2].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 22);
          
          h2.check(data[3].getType() == ElementSpec.EndTagType);
          h2.check(data[3].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 0);
          
          h2.check(data[4].getType() == ElementSpec.StartTagType);
          h2.check(data[4].getDirection() == ElementSpec.JoinNextDirection);
          h2.check(data[4].getOffset() == 0);
          h2.check(data[4].getLength() == 0);
          
          h2.check(data[5].getType() == ElementSpec.ContentType);
          h2.check(data[5].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[5].getOffset() == 0);
          h2.check(data[5].getLength() == 1);          
        }
      else if (numInserts == 5)
        {
          h2.checkPoint("ElementBuffer insertUpdate: fifth insertion");
          h2.check(data.length == 1);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 19);
        }
      else if (numInserts == 6)
        {
          h2.checkPoint("ElementBuffer insertUpdate: sixth insertion");
          h2.check(data.length == 4);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 13);
          
          h2.check(data[1].getType() == ElementSpec.EndTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);
          
          h2.check(data[2].getType() == ElementSpec.StartTagType);
          h2.check
            (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 0);
          
          h2.check(data[3].getType() == ElementSpec.ContentType);
          h2.check(data[3].getDirection() == ElementSpec.JoinNextDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 9);          
        }
      else if (numInserts == 7)
        {
          h2.checkPoint("ElementBuffer insertUpdate: seventh insertion");
          h2.check(data.length == 3);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 21);
          
          h2.check(data[1].getType() == ElementSpec.EndTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);
          
          h2.check(data[2].getType() == ElementSpec.StartTagType);
          h2.check
            (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 0); 
        }
      else if (numInserts == 8)
        {
          h2.checkPoint("ElementBuffer insertUpdate: second insertion");
          h2.check (data.length == 11);
          h2.check(data[0].getType() == ElementSpec.EndTagType);
          h2.check(data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 0);
          
          h2.check(data[1].getType() == ElementSpec.StartTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);          
          
          h2.check(data[2].getType() == ElementSpec.ContentType);
          h2.check(data[2].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 1);
          
          h2.check(data[3].getType() == ElementSpec.EndTagType);
          h2.check(data[3].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 0);
          
          h2.check(data[4].getType() == ElementSpec.StartTagType);
          h2.check(data[4].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[4].getOffset() == 0);
          h2.check(data[4].getLength() == 0);
          
          h2.check(data[5].getType() == ElementSpec.ContentType);
          h2.check(data[5].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[5].getOffset() == 0);
          h2.check(data[5].getLength() == 23);          

          h2.check(data[6].getType() == ElementSpec.EndTagType);
          h2.check(data[6].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[6].getOffset() == 0);
          h2.check(data[6].getLength() == 0);          

          h2.check(data[7].getType() == ElementSpec.StartTagType);
          h2.check(data[7].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[7].getOffset() == 0);
          h2.check(data[7].getLength() == 0);
          
          h2.check(data[8].getType() == ElementSpec.ContentType);
          h2.check(data[8].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[8].getOffset() == 0);
          h2.check(data[8].getLength() == 22);
          
          h2.check(data[9].getType() == ElementSpec.EndTagType);
          h2.check(data[9].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[9].getOffset() == 0);
          h2.check(data[9].getLength() == 0);          

          h2.check(data[10].getType() == ElementSpec.StartTagType);
          h2.check(data[10].getDirection() == ElementSpec.JoinNextDirection);
          h2.check(data[10].getOffset() == 0);
          h2.check(data[10].getLength() == 0);          
        }
      else if (numInserts == 9)
        {
          h2.checkPoint("ElementBuffer insertUpdate: second insertion");
          h2.check (data.length == 9);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 1);
          
          h2.check(data[1].getType() == ElementSpec.EndTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);
          
          h2.check(data[2].getType() == ElementSpec.StartTagType);
          h2.check(data[2].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 0);          
          
          h2.check(data[3].getType() == ElementSpec.ContentType);
          h2.check(data[3].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 23);
          
          h2.check(data[4].getType() == ElementSpec.EndTagType);
          h2.check(data[4].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[4].getOffset() == 0);
          h2.check(data[4].getLength() == 0);
          
          h2.check(data[5].getType() == ElementSpec.StartTagType);
          h2.check(data[5].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[5].getOffset() == 0);
          h2.check(data[5].getLength() == 0);
          
          h2.check(data[6].getType() == ElementSpec.ContentType);
          h2.check(data[6].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[6].getOffset() == 0);
          h2.check(data[6].getLength() == 22);          

          h2.check(data[7].getType() == ElementSpec.EndTagType);
          h2.check(data[7].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[7].getOffset() == 0);
          h2.check(data[7].getLength() == 0);          

          h2.check(data[8].getType() == ElementSpec.StartTagType);
          h2.check
            (data[8].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check(data[8].getOffset() == 0);
          h2.check(data[8].getLength() == 0);          
        }
      else if (numInserts == 10)
        {
          h2.checkPoint("ElementBuffer insertUpdate: tenth insertion");
          h2.check(data.length == 1);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 19);
        }
      else if (numInserts == 11)
        {
          h2.checkPoint("ElementBuffer insertUpdate: eleventh insertion");
          h2.check (data.length == 6);
          h2.check(data[0].getType() == ElementSpec.EndTagType);
          h2.check(data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 0);
          
          h2.check(data[1].getType() == ElementSpec.StartTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);          
          
          h2.check(data[2].getType() == ElementSpec.ContentType);
          h2.check(data[2].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 1);
          
          h2.check(data[3].getType() == ElementSpec.EndTagType);
          h2.check(data[3].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 0);
          
          h2.check(data[4].getType() == ElementSpec.StartTagType);
          h2.check(data[4].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[4].getOffset() == 0);
          h2.check(data[4].getLength() == 0);
          
          h2.check(data[5].getType() == ElementSpec.ContentType);
          h2.check(data[5].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[5].getOffset() == 0);
          h2.check(data[5].getLength() == 20);          
        }

      else
        h2.fail("too many ElementSpecs created");
      super.insertUpdate(data);
    }
  }
}
