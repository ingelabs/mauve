//Tags: not-a-test

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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * A HTTP server for testing purpose only. The server can
 * be started on a given port and the response headers and
 * response body to be returned set. This way one can test
 * arbritrary testcases for the http client side library.
 *  
 * @see gnu.testlet.java.net.HttpURLConnection.responseCodeTest
 * @see gnu.testlet.java.net.HttpURLConnection.responseHeadersTest
 * 
 * @author Wolfgang Baer (WBaer@gmx.de)
 */
public final class TestHttpServer implements Runnable
{ 
  /**
   * The interface to be implemented if checking
   * of the serverside received headers and body
   * is desired.
   */
  interface CheckReceivedRequest 
  {
    /**
     * Will be called with a List of headers in 
     * the sequence received by the test server.
     * 
     * @param headers List of headers
     */
    public void checkHeaders(List headers);
    
    /**
     * Will be called with the body if one was
     * received.
     * 
     * @param body the body
     */
    public void checkBody(byte[] body);    
  }
  
  /**
   * The actual request handler.
   * It always returns what is set in the TestHttpServer
   * for the response headers and response body.
   */
  class TestHttpRequestHandler implements Runnable
  {  
    Socket socket;
    OutputStream output;
    InputStream input;
  
    public TestHttpRequestHandler(Socket socket) throws Exception
    {
      this.socket = socket;
      output = socket.getOutputStream();
      input = socket.getInputStream();
    }
  
    public void run()
    {
      try
        {
          // Read the whole request into a byte array
          ByteArrayOutputStream buffer = new ByteArrayOutputStream();
          
          byte[] b = new byte[1024];
          int bytes = 0 ;
          while ((bytes = input.read(b)) != -1)
            { 
              buffer.write(b, 0, bytes);
              if (bytes < 1024)
                break;
            }
          
          byte[] request = buffer.toByteArray();

          // Parse the request headers from the byte array          
          List headerList = new ArrayList();
          
          ByteArrayOutputStream line;
          int i = 0;
          line = new ByteArrayOutputStream();
          for (; i < request.length; i++)
            {
              if (request[i] != (byte) 0x0a) // LF
                line.write(request[i]);
              else
                {
                  byte[] array = line.toByteArray();
                  if (array.length == 1) // the last is only a LF
                    break;

                  String headerLine = new String(array);
                  headerList.add(headerLine);
                  line = new ByteArrayOutputStream();
                }
            }

          // Put the remaining bytes into the request body 
          byte[] body = new byte[(request.length - (i + 1))];
          System.arraycopy(request, i + 1, body, 0, body.length);
          
          // Check everything          
          if (check != null)
            {
              check.checkHeaders(headerList);
              if (body.length > 0)
                check.checkBody(body);
            }         
             
          // Response writing          
          // write the response headers
          output.write(responseHeader);
          
          // write the body 
          if (responseBody != null)
            output.write(responseBody);
          
          // Clean up
          output.close();
          input.close();
          socket.close();            
        }
      catch (Exception e)
        {
          // ignore
        }
    }
  }

  int port;
  byte[] responseHeader;
  byte[] responseBody;
  CheckReceivedRequest check;
  boolean kill = false;
  ServerSocket serverSocket;  

  /**
   * Create a TestHttpServer on given port
   * @param port the port to use.
   */
  public TestHttpServer(int port)
  {
    this.port = port;
  }
  
  /**
   * An object implementing the CheckReceivedRequest
   * interface. The methods will be called to enable
   * checks of the serverside received request.
   * 
   * @param responseBody the byte[] of the body
   */
  public void setCheckReceivedRequest(CheckReceivedRequest object)
  {
    this.check = object;
  }
  
  /**
   * The bytes which should be sent as the response body.
   * @param responseBody the byte[] of the body
   */
  public void setResponseBody(byte[] responseBody)
  {
    this.responseBody = responseBody;
  }
  
  /**
   * The bytes which should be sent as the response headers. 
   * @param responseHeaders the byte[] of the headers
   */
  public void setResponseHeaders(byte[] responseHeaders)
  {
    this.responseHeader = responseHeaders;
  }
  
  /**
   * This cleans up recources so more than one
   * TestHttpServer can be used in one mauve run.
   */
  public void killTestServer()
  {
    kill = true;
    try
      {
        serverSocket.close();
      }
    catch (IOException e)
      {
        // ignore
      }
  }
  
  /**
   * Listens on the port and creates a Handler for
   * incoming connections.
   */
  public void run() 
  {   
    try
      {
        serverSocket = new ServerSocket(port);
        while (! kill)
          {
            Socket socket = serverSocket.accept();
            try
              {
                TestHttpRequestHandler request = 
                  new TestHttpRequestHandler(socket);
                Thread thread = new Thread(request);
                
                thread.start();
              }
            catch (Exception e)
              {
                // ignore
              }
          }
      }
    catch (IOException e)
      {
        // ignore
      }
  }
}
