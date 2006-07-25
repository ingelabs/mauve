/* ValidBasicSelfIssuedNewWithOldTest4.java
   Copyright (C) 2003  Free Software Foundation, Inc.

   Distributed under the GPL. See the file `COPYING' */

// Tags: JDK1.4

package gnu.testlet.java.security.cert.pkix.pkits;

public class ValidBasicSelfIssuedNewWithOldTest4 extends BaseValidTest
{
  public ValidBasicSelfIssuedNewWithOldTest4()
  {
    super(new String[] { "data/certs/ValidBasicSelfIssuedNewWithOldTest4EE.crt",
                         "data/certs/BasicSelfIssuedOldKeyCACert.crt" },
          new String[] { "data/crls/BasicSelfIssuedOldKeyCACRL.crl" },
          new String[] { "data/certs/BasicSelfIssuedOldKeyNewWithOldCACert.crt" });
  }
}
