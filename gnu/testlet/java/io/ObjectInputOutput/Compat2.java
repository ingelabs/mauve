package gnu.testlet.java.io.ObjectInputOutput;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.io.IOException;
import java.io.Serializable;

public class Compat2 implements Testlet
{
  static String SERIAL_REFERENCE = "Compat2.serial.bin";
  static String SERIAL_SCRATCH_FILENAME = "Compat2.tmp";
  static int SERIAL_REF_ID = 0;

  private static class GetTypeMismatch // object typemismatch in get
    implements Serializable
  {
    private Integer x = new Integer(17);
    private Integer y = new Integer(27);
    
    public String toString()
    {
      return (this.getClass().getName() + ": " +x+ "," +y);
    }
    
    private void writeObject(ObjectOutputStream stream) 
      throws IOException, ClassNotFoundException
    {
      ObjectOutputStream.PutField pf1 = stream.putFields();
      pf1.put("x", this.x);
      pf1.put("y", this.y);
      stream.writeFields();
    }
    
    private void readObject(ObjectInputStream stream) 
      throws IOException, ClassNotFoundException
    {
      ObjectInputStream.GetField gf1 = stream.readFields();
      this.x = (Integer)gf1.get("x", new String("Missed X?"));
      this.y = (Integer)gf1.get("y", new String("Missed Y?"));
    }
  }
  
  void generate(String fname) throws IOException
  { 
    FileOutputStream of = new FileOutputStream (fname);
    ObjectOutputStream oos = new ObjectOutputStream (of);
  
    oos.writeObject (new GetTypeMismatch());
  }

  GetTypeMismatch readSerial(String fname) throws IOException, ClassNotFoundException
  {
    FileInputStream ifs = new FileInputStream (fname);
    ObjectInputStream ios = new ObjectInputStream (ifs);
    
    return (GetTypeMismatch)ios.readObject();
  }

  public void test(TestHarness t)
  {
    int rand_id = 0;

    t.checkPoint ("Compatibility test for type mismatch when calling get methods");

    try
      {
	generate (SERIAL_SCRATCH_FILENAME);
	t.check (true);
	t.check (readSerial (SERIAL_SCRATCH_FILENAME).equals(new GetTypeMismatch()));
      }
    catch (Exception e)
      {
	t.check (false);
	t.debug (e);
      }
    
    try
      {
	ObjectInputStream ois = new ObjectInputStream (t.getResourceStream 
				  (getClass().getName().replace ('.', '#') + "." + SERIAL_REFERENCE));

	t.check(ois.readObject().equals (new GetTypeMismatch()));
      }
    catch (Exception e)
      {
	t.check (false);
	t.debug (e);
      }
  }

  static public void main(String args[]) throws IOException
  {
    new Compat2().generate (SERIAL_REFERENCE);
  }
}
