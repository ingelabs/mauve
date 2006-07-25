/* ValidpathLenConstraintTest14.java
   Copyright (C) 2003  Free Software Foundation, Inc.

   Distributed under the GPL. See the file `COPYING' */

// Tags: JDK1.4

package gnu.testlet.java.security.cert.pkix.pkits;

public class ValidpathLenConstraintTest14 extends BaseValidTest
{
  public ValidpathLenConstraintTest14()
  {
    super(new String[] { "data/certs/ValidpathLenConstraintTest14EE.crt",
                         "data/certs/pathLenConstraint6subsubsubCA41XCert.crt",
                         "data/certs/pathLenConstraint6subsubCA41Cert.crt",
                         "data/certs/pathLenConstraint6subCA4Cert.crt",
                         "data/certs/pathLenConstraint6CACert.crt" },
          new String[] { "data/crls/pathLenConstraint6subsubsubCA41XCRL.crl",
                         "data/crls/pathLenConstraint6subsubCA41CRL.crl",
                         "data/crls/pathLenConstraint6subCA4CRL.crl",
                         "data/crls/pathLenConstraint6CACRL.crl" });
  }
}
