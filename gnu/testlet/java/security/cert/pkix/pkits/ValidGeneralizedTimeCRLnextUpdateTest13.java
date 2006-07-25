/* ValidGeneralizedTimeCRLnextUpdateTest13.java
   Copyright (C) 2003  Free Software Foundation, Inc.

   Distributed under the GPL. See the file `COPYING' */

// Tags: JDK1.4

package gnu.testlet.java.security.cert.pkix.pkits;

public class ValidGeneralizedTimeCRLnextUpdateTest13 extends BaseValidTest
{
  public ValidGeneralizedTimeCRLnextUpdateTest13()
  {
    super(new String[] { "data/certs/ValidGeneralizedTimeCRLnextUpdateTest13EE.crt",
                         "data/certs/GeneralizedTimeCRLnextUpdateCACert.crt" },
          new String[] { "data/crls/GeneralizedTimeCRLnextUpdateCACRL.crl" });
  }
}
