package gnu.testlet.java.io.File;

import java.io.FilenameFilter;
import java.io.File;

public class DocFilter implements FilenameFilter
{
  public boolean accept (File dir, String name)
  {
    if (name.endsWith (".doc"))
      return true;
    return false;
  }
}
