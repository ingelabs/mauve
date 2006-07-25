/* ValidBasicSelfIssuedCRLSigningKeyTest6.java
   Copyright (C) 2003  Free Software Foundation, Inc.

   Distributed under the GPL. See the file `COPYING' */

// Tags: JDK1.4

package gnu.testlet.java.security.cert.pkix.pkits;

public class ValidBasicSelfIssuedCRLSigningKeyTest6 extends BaseValidTest
{
  public ValidBasicSelfIssuedCRLSigningKeyTest6()
  {
    super(new String[] { "data/certs/ValidBasicSelfIssuedCRLSigningKeyTest6EE.crt",
                         "data/certs/BasicSelfIssuedCRLSigningKeyCACert.crt" },
          new String[] { "data/crls/BasicSelfIssuedCRLSigningKeyCACRL.crl" },
          new String[] { "data/certs/BasicSelfIssuedCRLSigningKeyCRLCert.crt" });
  }
}
