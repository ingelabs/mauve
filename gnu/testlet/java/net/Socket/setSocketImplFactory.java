// Tags: JDK1.0
// Uses: TestSocketImplFactory

/*
  Copyright (C) 2003 C. Brian Jones
  
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

package gnu.testlet.java.net.Socket;

import java.net.*;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class setSocketImplFactory implements Testlet
{
  public void test (TestHarness harness)
  {
    try 
      {
	Socket.setSocketImplFactory (new TestSocketImplFactory ());
	harness.check (true, "setSocketImplFactory() - 1");
      }
    catch (Throwable t)
      {
	harness.check (false, "setSocketImplFactory() - 1");
      }

    try
      {
	Socket.setSocketImplFactory (new TestSocketImplFactory ());
	harness.check (false, "setSocketImplFactory() - 2");
      }
    catch (SocketException se)
      {
	harness.check (true, "setSocketImplFactory() - 2");
      }
    catch (Throwable t)
      {
	harness.check (false, "setSocketImplFactory() - 3");
      }

  }
}
