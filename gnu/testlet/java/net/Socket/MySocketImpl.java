// Tags: not-a-test

/*
   Copyright (C) 1999, 2002 Hewlett-Packard Company
   Copyright (C) 2002 Free Software Foundation, Inc.

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


class MySocketImpl extends SocketImpl {
    public MySocketImpl() {}
    protected void create(boolean stream) throws IOException {}
    protected void connect(String host, int port) throws IOException {}
    protected void connect(InetAddress address, int port) throws IOException {}
    protected void bind(InetAddress host, int port) throws IOException {}
    protected void listen(int backlog) throws IOException {}
    protected void accept(SocketImpl s) throws IOException {}
    protected InputStream getInputStream() throws IOException {return null;}
    protected OutputStream getOutputStream() throws IOException { return null; }
    protected int available() throws IOException { return 0; }
    protected void close() throws IOException {}
  public void setOption(int optID, Object value) throws SocketException {}
  public Object getOption(int optID) throws SocketException { return null; }

  public void shutdownInput() throws IOException {}
  public void shutdownOutput() throws IOException {}
  public void connect(SocketAddress addr, int timeout) throws IOException {}
  public void sendUrgentData(int data) throws IOException {}
}
