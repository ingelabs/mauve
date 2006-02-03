/* FakeDSAParameters.java
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

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.security.spec.InvalidParameterSpecException;

/**
 * A fake JCE DSA parameters type.
 */
public class FakeDSAParameters extends AlgorithmParametersSpi
{
  /* (non-Javadoc)
   * @see java.security.AlgorithmParametersSpi#engineInit(java.security.spec.AlgorithmParameterSpec)
   */
  protected void engineInit(AlgorithmParameterSpec paramSpec)
      throws InvalidParameterSpecException
  {
  }

  /* (non-Javadoc)
   * @see java.security.AlgorithmParametersSpi#engineInit(byte[])
   */
  protected void engineInit(byte[] params) throws IOException
  {
  }

  /* (non-Javadoc)
   * @see java.security.AlgorithmParametersSpi#engineInit(byte[], java.lang.String)
   */
  protected void engineInit(byte[] params, String format) throws IOException
  {
  }

  /* (non-Javadoc)
   * @see java.security.AlgorithmParametersSpi#engineGetParameterSpec(java.lang.Class)
   */
  protected AlgorithmParameterSpec engineGetParameterSpec(Class paramSpec)
      throws InvalidParameterSpecException
  {
    return new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,BigInteger.ONE);
  }

  /* (non-Javadoc)
   * @see java.security.AlgorithmParametersSpi#engineGetEncoded()
   */
  protected byte[] engineGetEncoded() throws IOException
  {
    return null;
  }

  /* (non-Javadoc)
   * @see java.security.AlgorithmParametersSpi#engineGetEncoded(java.lang.String)
   */
  protected byte[] engineGetEncoded(String format) throws IOException
  {
    return null;
  }

  /* (non-Javadoc)
   * @see java.security.AlgorithmParametersSpi#engineToString()
   */
  protected String engineToString()
  {
    return null;
  }
}
