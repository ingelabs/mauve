## Process this file with automake to produce Makefile.in.

AUTOMAKE_OPTIONS = foreign

JCFLAGS = -g
JC1FLAGS = -g

check_DATA = classes.stamp

harness_files = gnu/testlet/SimpleTestHarness.java \
	gnu/testlet/TestHarness.java gnu/testlet/Testlet.java

include choices

classes.stamp: choices $(CHOICES)
	here=`/bin/pwd`; cd $(srcdir); \
	  CLASSPATH=$$here:`/bin/pwd` $(JAVAC) $(JCFLAGS) -d $$here \
	    $(harness_files) $(CHOICES)
	touch classes.stamp

KEYS =
choices: FORCE
	ok=no; \
	if test -f .save-keys && test -f choices && test "`cat .save-keys`" = "$(KEYS)"; then \
	  ok=yes; \
	fi; \
	here=`/bin/pwd`; \
	if test "$$ok" = no; then \
	  echo "$(KEYS)" > .save-keys; \
	  cd $(srcdir) && $(SHELL) choose $$here $(KEYS); \
	fi

FORCE:

if USE_GCJ

java_files = $(harness_files) $(CHOICES)
class_files = $(patsubst %.java,%.class,$(java_files))
javao_files = $(patsubst %.class,%.o,$(class_files))

check_PROGRAMS = SimpleTestHarness

SimpleTestHarness_SOURCES =
## FIXME: should have way to compile from .java directly to
## .o.  See libjava/Makefile.am.
SimpleTestHarness_DEPENDENCIES = classes.stamp $(javao_files)
SimpleTestHarness_LDADD = $(javao_files)
SimpleTestHarness_LDFLAGS = --main=gnu.testlet.SimpleTestHarness
SimpleTestHarness_LINK = $(GCJ) $(AM_CFLAGS) $(CFLAGS) $(LDFLAGS) -o $@

%.o: %.class
	CLASSPATH=.:$(srcdir) $(GCJ) -fassume-compiled $(JC1FLAGS) -c -o $@ $<

check-local: $(check_PROGRAMS) $(CHOICES)
	cat classes | ./SimpleTestHarness

recheck:
	test -f .save-keys || : > .save-keys
## Always remove the test program to force a relink.
	@rm -f SimpleTestHarness
	$(MAKE) KEYS="`cat .save-keys`" check

MOSTLYCLEANFILES = $(javao_files)
CLEANFILES = $(class_files) classes.stamp

else  ## USE_GCJ

check-local: classes.stamp
	cat classes | $(JAVA) gnu.testlet.SimpleTestHarness

recheck:
	test -f .save-keys || : > .save-keys
	$(MAKE) KEYS="`cat .save-keys`" check

CLEANFILES = classes.stamp

endif ## USE_GCJ

## For now can't define this conditionally.
SUFFIXES = .class .java
