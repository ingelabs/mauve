/* FakeProvider.java
   Copyright (C) 2006 Free Software Foundation, Inc.
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

// Tags: not-a-test 

package gnu.testlet.gnu.java.security.jce;

import java.security.Provider;

/**
 * A fake JCE provider.
 */
public class FakeProvider extends Provider
{
  public FakeProvider()
  {
    super("FakeProvider", 0.1, "");

    put("AlgorithmParameters.DSA", FakeDSAParameters.class.getName());
    put("KeyFactory.Encoded",
        gnu.java.security.jce.sig.EncodedKeyFactory.class.getName());
    put("KeyFactory.DSS",
        gnu.java.security.jce.sig.DSSKeyFactory.class.getName());
    put("KeyFactory.RSA",
        gnu.java.security.jce.sig.RSAKeyFactory.class.getName());
  }
}
