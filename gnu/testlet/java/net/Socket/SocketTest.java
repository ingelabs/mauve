// Tags: JDK1.0
// Uses: SocketBServer SocketServer

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

package gnu.testlet.java.net.Socket;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.net.*;
import java.io.*;


public class SocketTest implements Testlet
{
  protected static TestHarness harness;

 public void test_BasicServer()
 {
  harness.checkPoint("BasicServer");
  try {
   SocketServer srv = new SocketServer();
   srv.harness = harness;
   srv.init();
   srv.start();
   Thread.yield();
  }
  catch ( Exception e )
  {
   harness.fail("Error : test_BasicServer failed - 0 " +
    "exception was thrown :"+e.getMessage());
  }

  try {
   Socket sock = new Socket("localhost" , 20000);
   DataInputStream dis = new DataInputStream(
         sock.getInputStream());
   String str = dis.readLine();

   if ( !str.equals("hello buddy" ))
    harness.fail("Error : test_BasicServer failed - 1 " +
     "string returned is not correct." );
   sock.close();
  }
  catch ( Exception e )
  {
   harness.fail("Error : test_BasicServer failed - 2 " +
    "exception was thrown :"+e.getMessage());
  }

  // second iteration
  try {
   Socket sock = new Socket("localhost" , 20000);
   DataInputStream dis = new DataInputStream(
         sock.getInputStream());
   String str = dis.readLine();

   if ( !str.equals("hello buddy" ))
    harness.fail("Error : test_BasicServer failed - 3 " +
     "string returned is not correct." );
   sock.close();
  }
  catch ( Exception e )
  {
   harness.fail("Error : test_BasicServer failed - 4 " +
    "exception was thrown :"+e.getMessage());
  }

  //System.out.println("Test for read[]");
  // second iteration
  try {
   Socket sock = new Socket("localhost" , 20000);
   DataInputStream dis = new DataInputStream(sock.getInputStream());

   byte data[] = new byte[5];
   int len;

   len=dis.read(data);

             String str= (new String(data, 0, 0, 5));

   //System.out.println("read 5 string is:"+str);

   if ( !str.equals("hello" ))
    harness.fail("Error : test_BasicServer failed - 5 " +
     "string returned is not correct." );
   dis.close();
   sock.close();
  }
  catch ( Exception e )
  {
   harness.fail("Error : test_BasicServer failed - 6 " +
    "exception was thrown :"+e.getMessage());
  }

  //System.out.println("Test for read[] offset, len");
  // second iteration
  try {
   Socket sock = new Socket("localhost" , 20000);
   InputStream is = (sock.getInputStream());
   byte data[] = new byte[5];

   int len;
   len=is.read(data,0,5);
             String str= (new String(data, 0, 0, 5));

   if ( !str.equals("hello" ))
    harness.fail("Error : test_BasicServer failed - 8 " +
     "string returned is not correct." );
   is.close();
   sock.close();
  }
  catch ( Exception e )
  {
   harness.fail("Error : test_BasicServer failed - 9 " +
    "exception was thrown :"+e.getMessage());
  }

  //System.out.println("Test for skip");
  // second iteration
  try {
   Socket sock = new Socket("localhost" , 20000);
   InputStream is = (sock.getInputStream());
   byte data[] = new byte[5];
   is.skip(2);

   int len=is.available();  // deterministic after blocking for skip
   if (len <= 0) { //"hello buddy" len
    harness.fail("Error : test_BasicServer failed - 7 " +
     "no more data available.  " );
   }

   is.read(data,0,3);

             String str= (new String(data, 0, 0, 3));

   if ( !str.equals("llo" ))
    harness.fail("Error : test_BasicServer failed - 10 " +
     "string returned is not correct." );
   is.close();
   sock.close();
  }
  catch ( Exception e )
  {
   harness.fail("Error : test_BasicServer failed - 11 " +
    "exception was thrown :"+e.getMessage());
  }
  try
  {
  	Socket sock = new Socket("localhost", 7);
  	InputStream sin = null;
  	OutputStream sout = null;

	// create 10 refs and make sure finalize() is invoked.
  	for (int i = 0; i < 10; i++)
  	{
  		sin = sock.getInputStream();
  		sout = sock.getOutputStream();
  	}
  }
  catch(IOException e)
  {
   harness.check(true);
  }

  // invoke finalize()
  System.gc();
 }

 public void test_params()
 {
  harness.checkPoint("params");
  try {
   String host = "mail.gnu.org";
   int port = 25;
   Socket sock = new Socket( host , port  );

   if ( sock.getLocalPort() <= 0 )
    harness.fail("Error : test_params failed - 1 " +
     "get port did not return proper values" );

// if(false) { // set/getSoTimeout not there
// try {
// sock.setSoTimeout( 100 );
// if ( sock.getSoTimeout() != 100 )
//  harness.fail("Error : test_params failed - 2 " +
//   "get /set timeout did not return proper values" );
// } catch ( Exception e )
// {
//   e.printStackTrace();
//   harness.fail("Error : setSoTimeout fails since vxWorks do not support the feature" );
// }
// }

   sock.setTcpNoDelay (true);
   if ( !sock.getTcpNoDelay ())
    harness.fail("Error : test_params failed - 3 " +
     "get /set tcp delay did not return proper values" );
   sock.setSoLinger( true , 10 );
   if ( sock.getSoLinger() != 10 )
    harness.fail("Error : test_params failed - 4"  );

   sock.setSoLinger( false , 20 );
   if ( sock.getSoLinger() != -1 )
    harness.fail("Error : test_params failed - 5"  );

   if ( sock.getPort() != port )
    harness.fail("Error : test_params failed - 6"  );

   harness.check(sock.getInetAddress().toString().indexOf(host) != -1,
     "getInetAddress().toString() should contain host " + host);
   harness.check(sock.toString().indexOf(host) != -1,
     "toString() should contain host " + host);

   try {
       Socket.setSocketImplFactory( null );
   }
   catch ( java.io.IOException e )
   {
    harness.check(true);
   }
  }
  catch ( Exception e )
  {
     e.printStackTrace();
     harness.fail("Error : test_params failed - 10 " +
      "exception was thrown" );
  }
 }

 public void test_Basics()
 {
  harness.checkPoint("Basics");
  // host name given
  try {
   Socket s = new Socket ( "babuspdjflks" , 200 );
   harness.fail("Error : test_Basics failed - 1 " +
     " exception should have been thrown here" );
  }
  catch ( UnknownHostException e ){
   harness.check(true);
  }
  catch ( IOException e ){
   harness.fail("Error : test_Basics failed - 2 " +
     " Unknown host exception should have been thrown here: " +
     e.getMessage() );
  }

  try {
   Socket s = new Socket( "localhost" , 30001 );
   harness.fail("Error : test_Basics failed - 3 " +
     " exception should have been thrown here" );

  }
  catch ( UnknownHostException e ){
   harness.fail("Error : test_Basics failed - 4 " +
     " Unknown host exception should not have been thrown here" );
  }
  catch ( IOException e ){
   harness.check(true);
  }

  try {
   Socket s = new Socket( "localhost" , 30001, true );
   harness.fail("Error : test_Basics failed - 5 " +
     " exception should have been thrown here" );

  }
  catch ( UnknownHostException e ){
   harness.fail("Error : test_Basics failed - 6 " +
     " Unknown host exception should not have been thrown here" );
  }
  catch ( IOException e ){
   harness.check(true);
  }

  // host inet given
  try {
   Socket s = new Socket ( "www.gnu.org" , 13 );
  }
  catch ( Exception e ){
     e.printStackTrace();
     harness.fail("Error : test_Basics failed - 7 " +
       "exception should not have been thrown here" );
  }
  try {
   Socket s = new Socket( InetAddress.getLocalHost() , 30002 );
   harness.fail("Error : test_Basics failed - 8 " +
     " exception should have been thrown here" );

  }
  catch ( Exception e ){
   harness.check(true);
  }

  //if (false) // 1.1 features not implemented
if (true) // 1.1 features not implemented
{

  // src socket target socket given( as hostname ).
  try {
   Socket s = new Socket ( "babuspdjflks" , 200,
       InetAddress.getLocalHost() ,20000 );
   //harness.fail("Error : test_Basics failed - 9 " +
   //  " exception should have been thrown here" );
   System.out.println("Warning: test_Basics failed - 9 EJWcr00676 still fail");
  }
  catch ( UnknownHostException e ){
   harness.check(true);
  }
  catch ( IOException e ){
   harness.fail("Error : test_Basics failed - 10 " +
     " Unknown host exception should have been thrown here: " +
     e.getMessage() );
  }

  try {
   Socket s = new Socket( "localhost" , 30003 ,
         InetAddress.getLocalHost() , 20000 );
   //harness.fail("Error : test_Basics failed - 11 " +
   //  " exception should have been thrown here" );
   System.out.println("Warning: test_Basics failed - 11 EJWcr00676 still fail");
  }
  catch ( UnknownHostException e ){
     e.printStackTrace();
     harness.fail("Error : test_Basics failed - 12 " +
       " Unknown host exception should not have been thrown here" );
  }
  catch ( IOException e ){
   harness.check(true);
  }

  // src socket target socket given( as ip address ).
  try {
   Socket s = new Socket( InetAddress.getLocalHost() , 30004 ,
         InetAddress.getLocalHost() , 20000 );
   //harness.fail("Error : test_Basics failed - 13 " +
   //  " exception should have been thrown here" );
   System.out.println("Warning: test_Basics failed - 13 EJWcr00676 still fail");
  }
  catch ( UnknownHostException e ){
   harness.fail("Error : test_Basics failed - 14 " +
     " Unknown host exception should not have been thrown here" );
  }
  catch ( IOException e ){
   harness.check(true);
  }
}

 }

 public void test_BasicBServer()
 {
  harness.checkPoint("BasicBServer");
  SocketBServer srv = new SocketBServer();
  srv.harness = harness;
  srv.init();
  srv.start();
  Thread.yield();

  try {
   Socket sock = new Socket("localhost" , 20002);
   DataInputStream dis = new DataInputStream(
         sock.getInputStream());
   String str = dis.readLine();

   if ( !str.equals("hello buddy" ))
    harness.fail("Error : test_BasicServer failed - 1 " +
     "string returned is not correct." );
   sock.close();
  }
  catch ( Exception e )
  {
     e.printStackTrace();
     harness.fail("Error : test_BasicServer failed - 2 " +
       "exception was thrown :");
  }
 }


 public void testall()
 {
   test_Basics();
   test_params();
   test_BasicServer();
   test_BasicBServer();
 }

  public void test (TestHarness the_harness)
  {
    harness = the_harness;
    testall ();
  }
}
