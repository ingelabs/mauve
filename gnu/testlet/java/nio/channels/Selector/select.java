package gnu.testlet.java.nio.channels.Selector;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;

public class select implements Testlet
{

  public void test(final TestHarness harness)
  {
    class SelectorThread extends Thread {
      boolean finished;
      public void run()
      {
        try
        {
          ServerSocketChannel channel = ServerSocketChannel.open();
          InetSocketAddress hostAddress = new InetSocketAddress(0);
          channel.socket().setReuseAddress(true);
          channel.socket().bind(hostAddress);
          channel.configureBlocking(false);

          Selector selector = Selector.open();
          channel.register(selector, SelectionKey.OP_ACCEPT);

          // if wakeup is called when no select operation is in progress,
          // the next call to select() should return immediately
          // See https://gcc.gnu.org/bugzilla/show_bug.cgi?id=86154
          selector.wakeup();

          selector.select();
          finished = true;
        }
        catch (IOException e)
        {
          StringWriter sw = new StringWriter();
          PrintWriter pw = new PrintWriter(sw);
          e.printStackTrace(pw);
          harness.debug(sw.toString());
        }
      }
    };

    SelectorThread th = new SelectorThread();
    try
    {
      th.start();
      th.join(2000);
    }
    catch (Exception e)
    {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      harness.debug(sw.toString());
    }
    harness.check(th.finished);
  }

}