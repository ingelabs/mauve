/* ValidNameChainingCapitalizationTest5.java
   Copyright (C) 2003  Free Software Foundation, Inc.

   Distributed under the GPL. See the file `COPYING' */

// Tags: JDK1.4

package gnu.testlet.java.security.cert.pkix.pkits;

public class ValidNameChainingCapitalizationTest5 extends BaseValidTest
{
  public ValidNameChainingCapitalizationTest5()
  {
    super(new String[] { "data/certs/ValidNameChainingCapitalizationTest5EE.crt", "data/certs/GoodCACert.crt" },
          new String[] { "data/crls/GoodCACRL.crl" });
  }
}
