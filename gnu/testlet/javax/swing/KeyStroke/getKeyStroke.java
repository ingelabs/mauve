// Tags: JDK1.2

// Copyright (C) 2005 Thomas Zander <zander@kde.org>

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

package gnu.testlet.javax.swing.KeyStroke;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.awt.*;
import javax.swing.*;

/**
 * These tests pass with the Sun JDK 1.4.2_05 on GNU/Linux IA-32.
 */
public class getKeyStroke implements Testlet {
    public void test(TestHarness harness) {
        harness.checkPoint("Calling with unknown string should return 'null'");
        try {
            KeyStroke ks = KeyStroke.getKeyStroke("bad");
            harness.check(ks == null);
        } catch(Throwable t) {
            harness.check(false);
        }
        try {
            KeyStroke ks = KeyStroke.getKeyStroke(null);
            harness.check(ks == null);
        } catch(Throwable t) {
            harness.check(false);
        }
    }
}

