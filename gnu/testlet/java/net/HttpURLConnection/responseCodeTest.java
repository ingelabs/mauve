//Tags: JDK1.1
//Uses: TestHttpServer

//Copyright (C) 2006 Free Software Foundation, Inc.
//Written by Wolfgang Baer (WBaer@gmx.de)

//This file is part of Mauve.

//Mauve is free software; you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation; either version 2, or (at your option)
//any later version.

//Mauve is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with Mauve; see the file COPYING.  If not, write to
//the Free Software Foundation, 51 Franklin Street, Fifth Floor,
//Boston, MA, 02110-1301 USA.

package gnu.testlet.java.net.HttpURLConnection;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Tests correct behaviour of getInputStream(), getErrorStream()
 * for all error response codes.
 */
public class responseCodeTest implements Testlet
{
  /**
   * Starts an HTTP server on port 8080 and calls
   * the test_ResponseCode for the error codes.
   */
  public void test(TestHarness h) 
  {  
    TestHttpServer server = null;
    try
      {
        server = new TestHttpServer(8080);
        Thread thread = new Thread(server);
        thread.start();
        try
          {
//          SUN JDK needs some time to open sockets
            Thread.sleep(100);
          }
        catch (InterruptedException e1)
          {
          }
        
        for (int i=400; i < 418; i++)
          test_ResponseCode(i, h, server);
        
        for (int i=500; i < 506; i++)
          test_ResponseCode(i, h, server);
      }
    finally
      {
        server.killTestServer();
      }   
  }
  
  public void test_ResponseCode(int responseCode, 
      TestHarness h, TestHttpServer server)
  {    
    try
      {        
        URL url = new URL("http://localhost:8080/");        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();        
        conn.setRequestMethod("GET");
        
        h.checkPoint("Test " + responseCode + " response");

        // construct what should be returned by the test server as headers        
        ByteArrayOutputStream headers = new ByteArrayOutputStream();

        // status line (the responsecode encoded for the test)
        headers.write(new String("HTTP/1.0 " + responseCode + " OK\r\n").getBytes());
        headers.write("Server: TestServer\r\n\r\n".getBytes());

        server.setResponseHeaders(headers.toByteArray());
        
        // test the responsecode
        int code = conn.getResponseCode();
        h.check(code == responseCode);

        // getInputStream should always throw an IOException
        try 
          {
            conn.getInputStream();
            h.check(false);
          }
        catch (IOException e)
          {
            // for a 404/410 it must be a FNFE
            if (responseCode == 404 
                || responseCode == 410)
              {
                // Since JDK 1.5 as FNFE is thrown - so this will fail for 1.4
                if (e instanceof FileNotFoundException)
                  h.check(true);
                else
                  h.check(false);
              }
            else
              h.check(true);
          }
        
        // the errorstream must be set always
        InputStream error = conn.getErrorStream();
        h.check(error != null);
        
        conn.disconnect();       
      }   
    catch (IOException e)
      {       
        h.debug("Unexpected IOException");
        h.debug(e);
      }
  }
}
