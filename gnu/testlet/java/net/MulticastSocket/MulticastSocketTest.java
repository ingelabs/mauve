// Tags: JDK1.1
// Uses: MulticastServer MulticastClient

/*
   Copyright (C) 1999 Hewlett-Packard Company

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

package gnu.testlet.java.net.MulticastSocket;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.net.*;
import java.io.*;


public class MulticastSocketTest implements Testlet
{
  protected static TestHarness harness;
    public void test_Basics()
    {
	MulticastSocket     socket;
	int                 nPort = 0;
	
	// Test for incorrect ipaddress, port and a port in use.

	try {
	    socket = new MulticastSocket(4441);
	    InetAddress address = InetAddress.getByName("15.0.0.1");
	    socket.joinGroup(address);
	    harness.fail("Wrong ipaddress arg. - 1");
	} catch (IOException e) {
	    harness.check(true);
	}


	try {
	    socket = new MulticastSocket(-1);
	    harness.fail("Wrong port arg. - 2");
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    socket.joinGroup(address);

	} catch (Exception e) {
	    harness.check(true);
	}
	

	try {
	    socket = new MulticastSocket(0);
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    socket.joinGroup(address);
	    socket.leaveGroup(address);
	    socket.close();
	    harness.check(true);
	} catch (Exception e) {   
	    harness.fail("Correct args. - 3"); 
	    e.printStackTrace();
	}
	

	try {
	    socket = new MulticastSocket();
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    nPort = socket.getLocalPort();
	    socket.joinGroup(address);
	    socket.leaveGroup(address);
	    socket.close();
	    harness.check(true);
	} catch (Exception e) { 
	    harness.fail("Correct args. different constructor. - 4");
	    e.printStackTrace();
	}
	
	
	try {
	    socket = new MulticastSocket(nPort);
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    socket.joinGroup(address);
	    socket.joinGroup(address);
	    harness.fail("joinGroup() twice.");
	} catch (Exception e) {
	    harness.check(true);
	}

	try {
	    socket = new MulticastSocket(++nPort);
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    socket.joinGroup(null);
	    harness.fail("joinGroup() with incorrect params. - 5");
	} catch (NullPointerException e) {
	    harness.fail("joinGroup() with incorrect params. should have " +
		"thrown an IOException - 5a");
	}
	catch(IOException e){
	    harness.check(true);
	}
	

	try {
	    socket = new MulticastSocket(++nPort);
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    socket.joinGroup(address);
	    socket.leaveGroup(address);
	    socket.leaveGroup(address);
	    harness.fail("leaveGroup() twice. - 6");
	    socket.close();
	} catch (Exception e) {
	    harness.check(true);
	}


	try {
	    socket = new MulticastSocket(++nPort);
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    socket.joinGroup(address);
	    socket.leaveGroup(null);
	    harness.fail("leaveGroup() with incorrect params - 7");
	    socket.close();
	} catch (Exception e) {
	    harness.check(true);
	}
	

	try {
	    socket = new MulticastSocket(++nPort);
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    socket.joinGroup(address);
	    socket.leaveGroup(address);
	    socket.close();
	    harness.check(true);
	} catch (Exception e) { 
	    harness.fail("Correct args.");
	}
	
	
	// FIXME: This test has no success or failure checks.
	try {
	    //	    System.out.println("getTTL() and setTTL().");
	    socket = new MulticastSocket(++nPort);
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    socket.joinGroup(address);
	    byte bTTL = socket.getTTL();
	    
	    //System.out.println("Default TTL = " + bTTL);
	    bTTL = (byte)127;
	    socket.setTTL(bTTL);
	    bTTL = socket.getTTL();
	    //System.out.println("New TTL = " + bTTL);
	    
	    bTTL = (byte)-56;
	    socket.setTTL(bTTL);
	    bTTL = socket.getTTL();
	    //System.out.println("Newer TTL = " + bTTL);
	    // Note is unsigned byte is used -56 will roll to a +ve value.
	    // Developer should verify if this is a failure case or not.
	    //if(bTTL == -56)
	    //System.out.println("FAIL : TTL cannot be negative");
	    
	    socket.setTTL((byte)1);
	    socket.leaveGroup(address);
	    socket.close();
	} catch (Exception e) {}
	
	    
    }

    public void test_Comm(){
	try {
                        MulticastClient client = new MulticastClient();
                        client.start();
			MulticastServer server = new MulticastServer(4446);
			server.start();
			harness.check(true);
	}catch(Exception e){
			harness.fail("test_Comm failed");
			e.printStackTrace();
	}
    }
	
    public void testall()
    {
	test_Basics();
	test_Comm();
    }

  public void test (TestHarness the_harness)
  {
    harness = the_harness;
    testall ();
  }
}
