// Copyright (C) 2004 Stephen Crawley.
// Copyright (C) 2005, 2006 Red Hat, Inc.
// Written by Stephen Crawley <crawley@dstc.edu.au>
// Extensively modified by Gary Benson <gbenson@redhat.com>

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

package gnu.testlet;

import java.security.Permission;

/**
 * A security manager for testing that security checks are performed.
 *
 * Typically a testcase would call <code>prepareChecks()</code> to
 * specify permissions that are expected to be checked during this
 * test.  Next you call whatever should perform the checks, and
 * finally you call <code>checkAllChecked()</code> to check that the
 * permissions you specified were checked.  Any unexpected checks
 * cause a {@link SecurityException} to be thrown.
 *
 * As well as the permissions that must be checked it is possible to
 * supply <code>prepareChecks()</code> with a list of permissions that
 * may be checked.  This allows some cases where proprietary JVMs
 * check something incidental that Classpath does not to be checked.
 * There are also halting versions of <code>prepareChecks()</code>
 * which will cause an exception to be thrown when all permissions
 * have been checked.  This allows throwpoints on things like
 * <code>System.exit()</code> to be tested.
 *
 * @author Stephen Crawley (crawley@dstc.edu.au)
 * @author Gary Benson (gbenson@redhat.com)
 */
public class TestSecurityManager extends SecurityManager
{
  /**
   * The security manager that was in force before we were installed.
   */
  private SecurityManager oldManager;

  /**
   * Permissions that must be checked for this test to pass.
   */
  private Permission[] mustCheck;

  /**
   * Permissions that may be checked during this test.
   */
  private Permission[] mayCheck;

  /**
   * Whether we are enabled or not.
   */
  private boolean enabled;
  
  /**
   * Must-check permissions are flagged as they are checked.
   */
  private boolean[] checked;

  /**
   * The test harness in use.
   */
  private final TestHarness harness;
  
  /**
   * Should we halt after all checks have occurred?
   */
  private boolean isHalting;

  /**
   * The exception to throw when halting.
   */
  public static class SuccessException extends SecurityException
  {
    private static final long serialVersionUID = 23;
  };
  private final SuccessException successException = new SuccessException();

  /**
   * An empty list of checks, for convenience.
   */
  private final Permission[] noChecks = new Permission[0];

  /**
   * Create a new test security manager.
   *
   * @param harness <code>TestHarness</code> the tests will be run by
   */
  public TestSecurityManager(TestHarness harness)
  {
    super();
    this.harness = harness;
  }

  /**
   * Install this test security manager.
   */
  public void install()
  {
    SecurityManager oldsm = System.getSecurityManager();
    if (oldsm == this)
      throw new IllegalStateException("already installed");
    oldManager = oldsm;

    enabled = false;
    System.setSecurityManager(this);
  }

  /**
   * Uninstall this test security manager, replacing it with whatever
   * was in force before it was installed.
   */
  public void uninstall()
  {
    SecurityManager oldsm = System.getSecurityManager();
    if (oldsm != this)
      throw new IllegalStateException("not installed");

    enabled = false;
    System.setSecurityManager(oldManager);
  }

  /**
   * Prepare this test security manager for a series of checks.
   * <code>checkAllChecked()</code> should be called after the
   * test to check that the specified permissions were checked. 
   *
   * @param mustCheck permissions that must be checked in order for
   *        the test to pass
   */
  public void prepareChecks(Permission[] mustCheck)
  {
    prepareChecks(mustCheck, noChecks);
  }

  /**
   * Prepare this test security manager for a series of checks.
   * <code>checkAllChecked()</code> should be called after the
   * test to check that the specified permissions were checked. 
   *
   * @param mustCheck permissions that must be checked in order for
   *        the test to pass
   * @param mayCheck permissions that may be checked during the test
   *        but are not required in order for the test to pass
   */
  public void prepareChecks(Permission[] mustCheck, Permission[] mayCheck)
  {
    prepareChecks(mustCheck, mayCheck, false);
  }

  /**
   * Prepare this test security manager for a series of checks.
   * A <code>SuccessException</code> will be thrown when the
   * final permission is checked, halting the test.
   *
   * @param mustCheck permissions that must be checked in order for
   *        the test to pass
   */
  public void prepareHaltingChecks(Permission[] mustCheck)
  {
    prepareHaltingChecks(mustCheck, noChecks);
  }

  /**
   * Prepare this test security manager for a series of checks.
   * A <code>SuccessException</code> will be thrown when the
   * final permission is checked, halting the test.
   *
   * @param mustCheck permissions that must be checked in order for
   *        the test to pass
   * @param mayCheck permissions that may be checked during the test
   *        but are not required in order for the test to pass
   */
  public void prepareHaltingChecks(Permission[] mustCheck,
				   Permission[] mayCheck)
  {
    prepareChecks(mustCheck, mayCheck, true);
  }

  /**
   * Prepare this test security manager for a series of checks.
   *
   * @param mustCheck permissions that must be checked in order for
   *        the test to pass
   * @param mayCheck permissions that may be checked during the test
   *        but are not required in order for the test to pass
   * @param isHalting whether to throw a <code>SuccessException</code>
   *        when the final permission is checked
   */
  protected void prepareChecks(Permission[] mustCheck,
			       Permission[] mayCheck,
			       boolean isHalting)
  {
    this.mayCheck = mayCheck;
    this.mustCheck = mustCheck;
    this.isHalting = isHalting;

    checked = new boolean[mustCheck.length];
    enabled = true;
  }

  /**
   * Check that this permission is one that we should be checking.
   * 
   * @param perm the permission to be checked
   * @throws SuccessException if all <code>mustCheck</code>
   *         permissions have been checked and <code>isHalting</code>
   *         is true.
   * @throws SecurityException if none of the <code>mustCheck</code>
   *         or <code>mayCheck</code> permissions implies
   *         <code>perm</code>.
   */
  public void checkPermission(Permission perm) throws SecurityException
  {
    if (!enabled)
      return;

    if (harness != null)
      harness.debug("checkPermission(" + perm + ")");

    boolean matched = false;
    for (int i = 0; i < mustCheck.length; i++) {
      if (mustCheck[i].implies(perm))
	matched = checked[i] = true;
    }

    if (!matched) {
      for (int i = 0; i < mayCheck.length; i++) {
	if (mayCheck[i].implies(perm))
	  matched = true;
      }
    }

    if (!matched) {
      enabled = false;
      
      harness.debug("unexpected check: " + perm);

      if (mustCheck.length != 0) {
	StringBuffer expected = new StringBuffer();
	for (int i = 0; i < mustCheck.length; i++)
	  expected.append(' ').append(mustCheck[i]);
	harness.debug("expected: mustCheck:" + expected.toString());
      }

      if (mayCheck.length != 0) {
	StringBuffer expected = new StringBuffer();
	for (int i = 0; i < mayCheck.length; i++)
	  expected.append(' ').append(mayCheck[i]);
	harness.debug("expected: mayCheck:" + expected.toString());
      }

      throw new SecurityException("unexpected check: " + perm);
    }
    
    if (isHalting) {
      boolean allChecked = true;
      for (int i = 0; i < checked.length; i++) {
	if (!checked[i])
	  allChecked = false;
      }
      if (allChecked) {
	enabled = false;
	throw successException;
      }
    }
  }

  /**
   * Check that all <code>mustCheck</code> permissions were checked,
   * calling <code>TestHarness.check()</code> with the result.
   */
  public void checkAllChecked()
  {
    enabled = false;

    boolean allChecked = true;
    for (int i = 0; i < checked.length; i++) {
      if (!checked[i]) {
	harness.debug("Unchecked permission: " + mustCheck[i]);
	allChecked = false;
      }
    }
    
    harness.check(allChecked);
  }
}
