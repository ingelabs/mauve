// Tags: JDK1.2

// Copyright (C) 2005 Free Software Foundation

// This file is part of Mauve.

// Mauve is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.

// Mauve is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with Mauve; see the file COPYING.  If not, write to
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

package gnu.testlet.java.io.ObjectInputStream;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.io.*;
import java.lang.reflect.*;

/**
 * This test checks that ObjectInputStream.readObject() resolves objects
 * in the stream using the correct ClassLoader, based on the context it is
 * called from.
 */
public class ClassLoaderTest implements Testlet 
{
  static class MyClassLoader extends ClassLoader
  {    
    public Class defineClass(File cls, String name)
      throws ClassNotFoundException, IOException
    {
      if (!cls.exists())
        throw new ClassNotFoundException();
      
      FileInputStream is = new FileInputStream(cls);
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      byte[] buf = new byte[512];
      int len, written;
      while (true)
        {
	  len = is.read(buf, 0, buf.length);
	  if (len == -1)
	    break;
	  written = 0;
	  while (written < len)
	    {
	      os.write(buf, written, len - written);
	      written += len;
	    }
	}
	
      byte[] classData = os.toByteArray();

      return super.defineClass(name, classData, 0, classData.length);      
    }
  }

  public static class MyClass implements Serializable
  {
    int i = 555;
    
    public static Object deserialize(byte[] serData) 
      throws IOException, ClassNotFoundException
    {
      ByteArrayInputStream bis = new ByteArrayInputStream(serData);
      ObjectInputStream ois = new ObjectInputStream(bis);
      Object obj = ois.readObject();
      ois.close();
      return obj;
    }
  }

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness) throws Exception
  {
    MyClassLoader loader = new MyClassLoader();
    ClassLoader sysLoader = this.getClass().getClassLoader();
    
    // FIXME: This assumes that mauve is run from its base directory, and
    // the test classes are not in a .jar or something. Might be better to use
    // a URL or something to find this?
    File classFile = new File(
      "gnu/testlet/java/io/ObjectInputStream/ClassLoaderTest$MyClass.class");
    harness.check(classFile.exists(), "class file exists");
    String className =
      "gnu.testlet.java.io.ObjectInputStream.ClassLoaderTest$MyClass";

    Class cl = loader.defineClass(classFile, className);

    harness.check(cl.getClassLoader() == loader, 
      "Class has correct classloader");

    // Now the fun part. Pipe an instance of MyClass through an Object 
    // stream. Depending on which class-context we deserialize it in, the 
    // resulting instance should have a different ClassLoader (and class).

    harness.checkPoint ("Deserialized objects have correct ClassLoader");

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(cl.newInstance());
    oos.close();    
    byte[] serData = bos.toByteArray();

    Object obj = MyClass.deserialize(serData);

    harness.check (obj.getClass().getClassLoader() == sysLoader);
    System.out.println (obj.getClass().getClassLoader() == loader);

    Method m = cl.getMethod("deserialize", new Class[] {byte[].class});
    Object obj2 = m.invoke(null, new Object[] {serData});
    harness.check(obj2.getClass().getClassLoader() == loader);
    System.out.println (obj2.getClass().getClassLoader() == loader);

    harness.check (obj.getClass() != obj2.getClass(), "Class equality");
  }  
}
