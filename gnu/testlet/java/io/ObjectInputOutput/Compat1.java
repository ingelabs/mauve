package gnu.testlet.java.io.ObjectInputOutput;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.io.IOException;
import java.io.File;

public class Compat1 implements Testlet
{
  static String SERIAL_REFERENCE = "serial.bin";
  static String SERIAL_SCRATCH_FILENAME = "Compat1.tmp";
  static int SERIAL_REF_ID = 0;
  
  BigInteger getBigInt(int id)
  {
    return 
      new BigInteger("1010101010101101010101010102102102013103913019301210" + id);
  }

  void generate(String fname, int id) throws IOException
  { 
    FileOutputStream of = new FileOutputStream (fname);
    ObjectOutputStream oos = new ObjectOutputStream (of);
  
    oos.writeObject (getBigInt (id));
  }

  BigInteger readSerial(String fname) throws Exception
  {
    FileInputStream ifs = new FileInputStream (fname);
    ObjectInputStream ios = new ObjectInputStream (ifs);
    
    return (BigInteger)ios.readObject();
  }

  public void test(TestHarness t)
  {
    int rand_id = 0;

    t.checkPoint ("Compatibility test for BigInteger");

    try
      {
	generate (SERIAL_SCRATCH_FILENAME, rand_id);
	t.check (true);
	t.check(readSerial (SERIAL_SCRATCH_FILENAME).equals (getBigInt (rand_id)));
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

	t.check(ois.readObject().equals (getBigInt (SERIAL_REF_ID)));
      }
    catch (Exception e)
      {
	t.check (false);
	t.debug (e);
      }

    new File(SERIAL_SCRATCH_FILENAME).delete();
  }

  static public void main(String args[]) throws IOException
  {
    new Compat1().generate (SERIAL_REFERENCE, SERIAL_REF_ID);
  }
}
