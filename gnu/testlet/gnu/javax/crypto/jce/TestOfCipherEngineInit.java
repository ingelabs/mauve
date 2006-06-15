/* TestOfCipherEngineInit.java -- Some more tests for engineInit
 * related to the tests in TestOfPR27849.
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

package gnu.testlet.gnu.javax.crypto.jce;

import gnu.javax.crypto.jce.spec.BlockCipherParameterSpec;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * Regression test for logic around CipherSpi.engineInit()s. If the 
 * cipher being initialized requires parameters not derivable from 
 * the key, then defaults/random parameters must be generated, provide
 * the opmode is ENCRYPT and WRAP. If the opmode is DECRYPT or UNWRAP 
 * then an exception should be thrown. Refer to javadoc for 
 * javax.crypto.CipherSpi for more information.
 */
public class TestOfCipherEngineInit
    implements Testlet
{
  private Key key;

  private Cipher cipher;

  byte[] iv;

  /*
   * (non-Javadoc)
   * 
   * @see gnu.testlet.Testlet#test(gnu.testlet.TestHarness)
   */
  public void test(TestHarness harness)
  {
    setUp(harness);
    testECB(harness);
    testNotECB(harness);
    testInitWithParameterSpec(harness);
    
  }

  private void setUp(TestHarness harness)
  {
    try
      {
        KeyGenerator keyG = KeyGenerator.getInstance("DESede");
        keyG.init(new SecureRandom());
        key = keyG.generateKey();
      }
    catch (Exception e)
      {
        harness.debug(e);
        harness.fail(String.valueOf(e));
      }

  }

  private void testECB(TestHarness harness)
  {
    try
      {
        cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        String input = "Does this work ?";

        cipher.init(Cipher.ENCRYPT_MODE, key);
        iv = cipher.getIV();
        harness.check(iv == null,
                      "(Encrypting) cipher.getIV() for ECB MUST return null");
        byte[] plaintext = input.getBytes();
        byte[] ciphertext = cipher.doFinal(plaintext);

        iv = null;
        cipher.init(Cipher.DECRYPT_MODE, key);
        iv = cipher.getIV();
        harness.check(iv == null,
                      "(Decrypting) cipher.getIV() for ECB MUST return null");
        byte[] plaintext2 = cipher.doFinal(ciphertext);
        String recovered = new String(plaintext2);

        harness.check(input.equals(recovered),
                      "Original and recovered texts MUST be equal");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail(String.valueOf(x));
      }
  }

  public void testNotECB(TestHarness harness)
  {
    try
      {
        cipher = Cipher.getInstance("DESede/CBC/NoPadding");
        String input = "Does this work ?";

        cipher.init(Cipher.ENCRYPT_MODE, key);
        iv = cipher.getIV();
        harness.check(iv != null,
                      "(Encrypting) cipher.getIV() for CBC MUST NOT return null");
        harness.check(iv.length == 8, "(Encrypting) IV length for MUST be 8");
        byte[] plaintext = input.getBytes();
        byte[] ciphertext = cipher.doFinal(plaintext);

        iv = null;
        try
          {
            cipher.init(Cipher.DECRYPT_MODE, key);
            harness.fail("(Decrypting) init of CBC without IV NOT possible");
          }
        catch (Exception e)
          {
            String type = e.getClass().getName();
            harness.check(type.equals(InvalidKeyException.class.getName()),
                          "(Decrypt) No exception at CBC init without IV");
          }
        cipher.init(Cipher.DECRYPT_MODE, key, cipher.getParameters());
        iv = cipher.getIV();
        harness.check(iv != null,
                      "(Decrypting) cipher.getIV() for CBC MUST NOT return null");
        harness.check(iv.length == 8, "(Decrypt) IV length for CBC should be 8");
        byte[] plaintext2 = cipher.doFinal(ciphertext);
        String recovered = new String(plaintext2);

        harness.check(input.equals(recovered),
                      "Original and recovered texts MUST be equal");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail(String.valueOf(x));
      }
  }

  // Similar test to above but tests the behaviour for:
  // Cipher.init(int i, Key k, AlgorithmParameterSpec param)
  // If param is null, and the cipher needs algorithm parameters to
  // function then init should create random/default parameters provided
  // the cipher is in ENCRYPT or WRAP mode, if in DECRYPT or UNWRAP mode
  // the function must throw an InvalidKeyException.
  // Extrapolation: If algorithm does not require additional params
  // then none should be created?
  private void testInitWithParameterSpec(TestHarness harness)
  {
    try
      {
        cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        String input = "Does this work ?";

        cipher.init(Cipher.ENCRYPT_MODE, key, (AlgorithmParameterSpec) null);
        iv = cipher.getIV();
        harness.check(
                      iv == null,
                      "(Encrypting - NULL AlgorithmParameterSpec) cipher.getIV() for ECB MUST return null");
        byte[] plaintext = input.getBytes();
        byte[] ciphertext = cipher.doFinal(plaintext);

        iv = null;
        cipher.init(Cipher.DECRYPT_MODE, key, (AlgorithmParameterSpec) null);
        iv = cipher.getIV();
        harness.check(
                      iv == null,
                      "(Decrypting - NULL AlgorithmParameterSpec) cipher.getIV() for ECB MUST return null");
        byte[] plaintext2 = cipher.doFinal(ciphertext);
        String recovered = new String(plaintext2);

        harness.check(input.equals(recovered),
                      "Original and recovered texts MUST be equal");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail(String.valueOf(x));
      }

    try
      {
        cipher = Cipher.getInstance("DESede/CBC/NoPadding");
        String input = "Does this work ?";

        cipher.init(Cipher.ENCRYPT_MODE, key, (AlgorithmParameterSpec) null);
        iv = cipher.getIV();
        harness.check(
                      iv != null,
                      "(Encrypting - NULL AlgorithmParameterSpec) cipher.getIV() MUST return random IV if none provided");
        harness.check(iv.length == 8,
                      "(Encrypting - NULL AlgorithmParameterSpec) IV length for MUST be 8");
        byte[] plaintext = input.getBytes();
        byte[] ciphertext = cipher.doFinal(plaintext);

        iv = null;

        AlgorithmParameterSpec backupAlg = cipher.getParameters().getParameterSpec(
                                                                                   BlockCipherParameterSpec.class);

        try
          {
            cipher.init(Cipher.DECRYPT_MODE, key, (AlgorithmParameterSpec) null);
            harness.fail("(Decrypting - NULL AlgorithmParameterSpec) init of CBC with NULL IV NOT possible");
          }
        catch (Exception e)
          {
            String type = e.getClass().getName();
            harness.check(
                          type.equals(InvalidKeyException.class.getName()),
                          "(Decrypting - NULL AlgorithmParameterSpec) CBC init with NULL IV MUST throw exception");
          }
        try
          {
            cipher.init(Cipher.DECRYPT_MODE, key, backupAlg);
          }
        catch (Exception e)
          {
            harness.fail("(Decrypting - Valid AlgorithmParameterSpec) Exception not expected -init with proper parameters");
          }

        iv = cipher.getIV();
        harness.check(
                      iv != null,
                      "(Decrypting - Valid AlgorithmParameterSpec) cipher.getIV() for CBC init with NULL params MUST NOT return null");
        harness.check(iv.length == 8,
                      "(Decrypting - NULL AlgorithmParameterSpec) IV length for CBC should be 8");
        byte[] plaintext2 = cipher.doFinal(ciphertext);
        String recovered = new String(plaintext2);

        harness.check(input.equals(recovered),
                      "(Valid AlgorithmParameterSpec) Original and recovered texts MUST be equal");

      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail(String.valueOf(x));
      }

  }

  
  //TODO: Add tests for WRAP and UNWRAP too.
  

}
