/* TestOfDSSSignature.java -- 
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

// Tags: GNU-CRYPTO JDK1.4

package gnu.testlet.gnu.java.security.sig.dss;

import gnu.java.security.hash.IMessageDigest;
import gnu.java.security.hash.Sha160;
import gnu.java.security.key.dss.DSSKeyPairGenerator;
import gnu.java.security.sig.BaseSignature;
import gnu.java.security.sig.dss.DSSSignature;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.security.KeyPair;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.util.HashMap;
import java.math.BigInteger;

/**
 * Conformance tests for the DSS signature generation/verification
 * implementation.
 */
public class TestOfDSSSignature implements Testlet
{
  public void test(TestHarness harness)
  {
    harness.checkPoint("TestOfDSSSignature");
    DSSKeyPairGenerator kpg = new DSSKeyPairGenerator();
    HashMap map = new HashMap();
    map.put(DSSKeyPairGenerator.MODULUS_LENGTH, new Integer(512));
    map.put(DSSKeyPairGenerator.USE_DEFAULTS, new Boolean(false));
    kpg.setup(map);
    KeyPair kp = kpg.generate();

    DSAPublicKey publicK = (DSAPublicKey) kp.getPublic();
    DSAPrivateKey privateK = (DSAPrivateKey) kp.getPrivate();

    DSSSignature alice = new DSSSignature();
    DSSSignature bob = (DSSSignature) alice.clone();

    byte[] message = "1 if by land, 2 if by sea...".getBytes();

    map.put(BaseSignature.SIGNER_KEY, privateK);
    alice.setupSign(map);
    alice.update(message, 0, message.length);
    Object signature = alice.sign();

    map.put(BaseSignature.VERIFIER_KEY, publicK);
    bob.setupVerify(map);
    bob.update(message, 0, message.length);

    harness.check(bob.verify(signature), "instance methods");

    IMessageDigest sha = new Sha160();
    sha.update(message, 0, message.length);
    byte[] hash = sha.digest();
    BigInteger[] rs = DSSSignature.sign(privateK, hash);

    harness.check(DSSSignature.verify(publicK, hash, rs), "class methods");
  }
}