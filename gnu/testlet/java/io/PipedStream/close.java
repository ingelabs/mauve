// Tags: JDK1.0

// This test is from Jeff Sturm.
// It tests whether close() on a PipedInputStream will correctly
// notify the writer.

package gnu.testlet.java.io.PipedStream;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.*;

public class close implements Runnable, Testlet {
	Thread main;
	PipedInputStream in;
	PipedOutputStream out;

	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("Closing pipe input stream:");
			in.close();
			Thread.sleep(1000);
			System.out.println("Interrupting pipe reader:");
			main.interrupt();
		} catch (Throwable t) {
		}
	}

	public void test (TestHarness harness) {
		int val = 23;
		try {
			close test = new close();

			test.main = Thread.currentThread();
			test.out = new PipedOutputStream();
			test.in = new PipedInputStream(test.out);

			(new Thread(test)).start();

			val = test.in.read();
		} catch (Throwable t) {
		}
		harness.check(val, -1);
	}
}
