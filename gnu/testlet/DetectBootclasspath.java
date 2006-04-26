package gnu.testlet;


/**
 * This tiny class is used for finding the bootclasspath of the VM used
 * to run the tests.
 * @author Anthony Balkissoon abalkiss at redhat dot com
 *
 */
public class DetectBootclasspath
{
  /**
   * Look in the system properties for the bootclasspath of the VM and return
   * it to the process that forked this process via the System.out stream.
   * 
   * Tries first to get the property "sun.boot.class.path", if there is none,
   * then it tries "java.boot.class.path", if there is still no match, looks
   * to see if there is a unique property key that contains "boot.class.path".
   * If this fails too, prints an error message.
   */
  public static void main (String[] args)
  {
    String result = "BCP_FINDER:";
    // Sun's VM stores the bootclasspath as "sun.boot.class.path".
    String temp = System.getProperty("sun.boot.class.path");
    if (temp == null)
      // JamVM stores it as "boot.class.path"
      temp = System.getProperty("java.boot.class.path");
    if (temp == null)
      {        
        String[] s = (String[])(System.getProperties().keySet().toArray());
        int count = 0;
        String key = null;
        for (int i = 0; i < s.length; i++)
          {
            if (s[i].indexOf("boot.class.path") != -1)
              {
                count ++;
                key = s[i];                
              }
          }
        if (count == 1)
          temp = System.getProperty(key);
        else
          {
            System.out.println("WARNING: Cannot auto-detect the " +
                    "bootclasspath for your VM, please file a bug report" +
                    " specifying which VM you are testing.");
            return;
          }
      }
    System.out.println(result + temp);
  }
}
