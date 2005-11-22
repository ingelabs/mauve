package gnu.testlet.javax.swing.text.DefaultStyledDocument.ElementBuffer;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Extensive tests for the ElementBuffer.insert() method. In order to check
 * this method, we make use of the DefaultStyledDocument.insert() method, which
 * passes an array of ElementSpecs to the element buffer. We then check for
 * different scenarios the resulting document structure and the document event
 * that is fired.
 * 
 * @author Roman Kennke (kennke@aicas.com)
 */
// TODO: Add more tests for EndTagType and StartTagType.
public class insert implements Testlet, DocumentListener
{

  /**
   * The document event that is fired in response to an element change. We
   * want to check that to see if the correct event is fired.
   */
  private DocumentEvent documentEvent;

  /**
   * A document for testing. It overrides some protected method for public
   * access.
   *
   * @author Roman Kennke (kennke@aicas.com)
   */
  static class TestDocument extends DefaultStyledDocument
  {
    /**
     * Overridden to provide public access.
     *
     * @param offset the offset at which to insert the data
     * @param data the data spec to insert
     */
    public void insert(int offset, ElementSpec[] data)
    {
      try
        {
          super.insert(offset, data);
        }
      catch (BadLocationException e)
        {
          throw new RuntimeException(e);
        }
    }
  }

  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    testOriginate1(harness);
    testOriginate2(harness);
    testOriginate3(harness);
    testJoinPrevious1(harness);
    testJoinPrevious2(harness);
    testJoinPrevious3(harness);
    testJoinNext1(harness);
    testJoinNext2(harness);
    testJoinNext3(harness);
  }

  /**
   * Tests content insertion with OriginateDirection. This test inserts
   * a content element between two existing elements.
   *
   * @param h the test harness to use
   */
  private void testOriginate1(TestHarness h)
  {
    h.checkPoint("testOriginate1");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 5.
    doc.insert(5, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 4 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 4);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 5);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 5);
    h.check(el2.getEndOffset(), 10);
    Element el3 = par.getElement(2);
    h.check(el3.getStartOffset(), 10);
    h.check(el3.getEndOffset(), 15);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // Two removals and one add for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec.getChildrenAdded().length, 2);
    h.check(ec.getChildrenRemoved().length, 1);
    h.check(ec.getIndex(), 0);
  }

  /**
   * Tests content insertion with OriginateDirection. This test inserts
   * a content element in between the first existing element.
   *
   * @param h the test harness to use
   */
  private void testOriginate2(TestHarness h)
  {
    h.checkPoint("testOriginate2");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 2.
    doc.insert(2, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 5 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 5);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 2);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 2);
    h.check(el2.getEndOffset(), 7);
    Element el3 = par.getElement(2);
    h.check(el3.getStartOffset(), 7);
    h.check(el3.getEndOffset(), 10);
    Element el4 = par.getElement(3);
    h.check(el4.getStartOffset(), 10);
    h.check(el4.getEndOffset(), 15);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // Two removals and one add for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec.getChildrenAdded().length, 3);
    h.check(ec.getChildrenRemoved().length, 1);
    h.check(ec.getIndex(), 0);
  }

  /**
   * Tests content insertion with OriginateDirection. This test inserts
   * a content element in between the second existing element.
   *
   * @param h the test harness to use
   */
  private void testOriginate3(TestHarness h)
  {
    h.checkPoint("testOriginate3");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 7.
    doc.insert(7, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 5 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 5);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 5);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 5);
    h.check(el2.getEndOffset(), 7);
    Element el3 = par.getElement(2);
    h.check(el3.getStartOffset(), 7);
    h.check(el3.getEndOffset(), 12);
    Element el4 = par.getElement(3);
    h.check(el4.getStartOffset(), 12);
    h.check(el4.getEndOffset(), 15);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // Two removals and one add for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec.getChildrenAdded().length, 3);
    h.check(ec.getChildrenRemoved().length, 1);
    h.check(ec.getIndex(), 1);
  }

  /**
   * Tests content insertion with JoinPreviousDirection. This test inserts
   * a content element between two existing elements.
   *
   * @param h the test harness to use
   */
  private void testJoinPrevious1(TestHarness h)
  {
    h.checkPoint("testJoinPrevious1");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 5.
    spec.setDirection(TestDocument.ElementSpec.JoinPreviousDirection);
    doc.insert(5, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 3 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 3);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 10);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 10);
    h.check(el2.getEndOffset(), 15);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // No structural change for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec, null);
  }

  /**
   * Tests content insertion with JoinPreviousDirection. This test inserts
   * a content element in between the first existing element.
   *
   * @param h the test harness to use
   */
  private void testJoinPrevious2(TestHarness h)
  {
    h.checkPoint("testJoinPrevious2");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 2.
    spec.setDirection(TestDocument.ElementSpec.JoinPreviousDirection);
    doc.insert(2, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 3 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 3);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 10);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 10);
    h.check(el2.getEndOffset(), 15);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // No structural change for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec, null);
  }

  /**
   * Tests content insertion with JoinPreviousDirection. This test inserts
   * a content element in between the second existing element.
   *
   * @param h the test harness to use
   */
  private void testJoinPrevious3(TestHarness h)
  {
    h.checkPoint("testJoinPrevious3");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 7.
    spec.setDirection(TestDocument.ElementSpec.JoinPreviousDirection);
    doc.insert(7, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 5 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 3);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 5);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 5);
    h.check(el2.getEndOffset(), 15);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // No structural change for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec, null);
  }

  /**
   * Tests content insertion with JoinNextDirection. This test inserts
   * a content element between two existing elements.
   *
   * @param h the test harness to use
   */
  private void testJoinNext1(TestHarness h)
  {
    h.checkPoint("testJoinNext1");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 5.
    spec.setDirection(TestDocument.ElementSpec.JoinNextDirection);
    doc.insert(5, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 3 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 3);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 5);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 5);
    h.check(el2.getEndOffset(), 15);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // No structural change for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec.getChildrenAdded().length, 2);
    h.check(ec.getChildrenRemoved().length, 2);
    h.check(ec.getIndex(), 0);
  }

  /**
   * Tests content insertion with JoinPreviousDirection. This test inserts
   * a content element in between the first existing element.
   *
   * @param h the test harness to use
   */
  private void testJoinNext2(TestHarness h)
  {
    h.checkPoint("testJoinNext2");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 2.
    spec.setDirection(TestDocument.ElementSpec.JoinNextDirection);
    doc.insert(2, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 3 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 3);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 2);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 2);
    h.check(el2.getEndOffset(), 15);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // No structural change for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec.getChildrenAdded().length, 2);
    h.check(ec.getChildrenRemoved().length, 2);
    h.check(ec.getIndex(), 0);
  }

  /**
   * Tests content insertion with JoinPreviousDirection. This test inserts
   * a content element in between the second existing element.
   *
   * @param h the test harness to use
   */
  private void testJoinNext3(TestHarness h)
  {
    h.checkPoint("testJoinNext3");
    TestDocument doc = new TestDocument();
    doc.addDocumentListener(this);
    char[] text = new char[] {'H', 'e', 'l', 'l', 'o'};
    SimpleAttributeSet atts = new SimpleAttributeSet();
    TestDocument.ElementSpec spec =
      new TestDocument.ElementSpec(atts, TestDocument.ElementSpec.ContentType,
                                   text, 0, 5);
    spec.setDirection(TestDocument.ElementSpec.OriginateDirection);

    TestDocument.ElementSpec[] specs = new TestDocument.ElementSpec[]{ spec };

    // Create the precondition, two elements [Hello], one at 0 and one at 5.
    doc.insert(0, specs);
    doc.insert(5, specs);

    // Now check what comes out when we insert one element at 7.
    spec.setDirection(TestDocument.ElementSpec.JoinNextDirection);
    doc.insert(7, specs);

    // We have one paragraph in the root element.
    Element root = doc.getDefaultRootElement();
    h.check(root.getElementCount(), 1);

    // We should now have 4 children in the paragraph.
    Element par = root.getElement(0);
    h.check(par.getElementCount(), 3);
    Element el1 = par.getElement(0);
    h.check(el1.getStartOffset(), 0);
    h.check(el1.getEndOffset(), 5);
    Element el2 = par.getElement(1);
    h.check(el2.getStartOffset(), 5);
    h.check(el2.getEndOffset(), 7);

    // Now check the document event that was fired.
    // No changes for the root.
    DocumentEvent.ElementChange ec = documentEvent.getChange(root);
    h.check(ec, null);
    // No structural change for the paragraph.
    ec = documentEvent.getChange(par);
    h.check(ec.getChildrenAdded().length, 2);
    h.check(ec.getChildrenRemoved().length, 2);
    h.check(ec.getIndex(), 1);
  }

  /**
   * Receives notification when some text attributes have changed.
   *
   * @param event the document event
   */
  public void changedUpdate(DocumentEvent event)
  {
    // Do nothing here.
  }

  /**
   * Receives notification when some text has been inserted into the document.
   *
   * @param event the document event
   */
  public void insertUpdate(DocumentEvent event)
  {
    documentEvent = event;
  }

  /**
   * Receives notification when some text has been removed from the document.
   *
   * @param event the document event
   */
  public void removeUpdate(DocumentEvent event)
  {
    // Do nothing here.
  }
}
