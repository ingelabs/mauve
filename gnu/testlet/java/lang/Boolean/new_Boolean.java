// tags: JDK1.0 JDK1.1 JDK1.2 PersonalJava EmbeddedJava

/*{
true
true
true
false
false
false
true
false
}*/

package gnu.testlet.java.lang.Boolean;
import gnu.testlet.Testlet;

class new_Boolean extends Testlet
{
  public void test ()
    {
      Boolean a = new Boolean("true");
      Boolean b = new Boolean("TRUE");
      Boolean c = new Boolean("tRuE");
      Boolean d = new Boolean("false");
      Boolean e = new Boolean("foo");
      Boolean f = new Boolean("");
      Boolean g = new Boolean(true);
      Boolean h = new Boolean(false);
      
      System.out.println(a);
      System.out.println(b);
      System.out.println(c);
      System.out.println(d);
      System.out.println(e);
      System.out.println(f);
      System.out.println(g);
      System.out.println(h);
    }
  
  public String description ()
    {
      return "creating Boolean objects";
    }
}
