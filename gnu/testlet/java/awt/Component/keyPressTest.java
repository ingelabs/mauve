/* keyPressTest.java -- 
   Copyright (C) 2006 Red Hat
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

*/

package gnu.testlet.java.awt.Component;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Event;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class keyPressTest implements Testlet
{
  int key = 0;
  Robot r;
  myFrame f;
  TestHarness h;
  
  public void test (TestHarness h)
  {
    f = new myFrame();
    r = h.createRobot ();
    this.h = h;
    
    runTest(KeyEvent.VK_A, 'a');
    runTest(KeyEvent.VK_B, 'b');
    runTest(KeyEvent.VK_C, 'c');
    runTest(KeyEvent.VK_D, 'd');
    runTest(KeyEvent.VK_E, 'e');
    runTest(KeyEvent.VK_F, 'f');
    runTest(KeyEvent.VK_G, 'g');
    runTest(KeyEvent.VK_H, 'h');
    runTest(KeyEvent.VK_I, 'i');
    runTest(KeyEvent.VK_J, 'j');
    runTest(KeyEvent.VK_K, 'k');
    runTest(KeyEvent.VK_L, 'l');
    runTest(KeyEvent.VK_M, 'm');
    runTest(KeyEvent.VK_N, 'n');
    runTest(KeyEvent.VK_O, 'o');
    runTest(KeyEvent.VK_P, 'p');
    runTest(KeyEvent.VK_Q, 'q');
    runTest(KeyEvent.VK_R, 'r');
    runTest(KeyEvent.VK_S, 's');
    runTest(KeyEvent.VK_T, 't');
    runTest(KeyEvent.VK_U, 'u');
    runTest(KeyEvent.VK_V, 'v');
    runTest(KeyEvent.VK_W, 'w');
    runTest(KeyEvent.VK_X, 'x');
    runTest(KeyEvent.VK_Y, 'y');
    runTest(KeyEvent.VK_Z, 'z');
  }
  
  public void runTest(int code, char chr)
  {
    KeyEvent e = new KeyEvent(f, KeyEvent.KEY_PRESSED, 0, 0, code, chr, KeyEvent.KEY_LOCATION_STANDARD);
    f.dispatchEvent(e);
    
    
    f.setSize(200,200);
    f.show();
    r.mouseMove(60, 60);

    r.keyPress(code);
    r.keyRelease(code);
    h.check(key, (int) chr);
  }
  
  class myFrame
      extends Frame
  { 
    
    public boolean keyDown(Event e, int i)
    {
      key = e.key;
      return super.keyDown(e, i);
    }
  }
}
