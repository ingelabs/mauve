## Process this file with automake to produce Makefile.in.

## FIXME: dependencies
AUTOMAKE_OPTIONS = foreign subdir-objects no-dependencies

JAVACFLAGS = -g

TESTFLAGS =

check_DATA = $(STAMP)

harness_files = gnu/testlet/SimpleTestHarness.java \
	gnu/testlet/TestHarness.java gnu/testlet/Testlet.java \
	gnu/testlet/ResourceNotFoundException.java

## FIXME: leading space makes automake ignore this.  Bleah.
 include choices

classes.stamp: choices $(CHOICES)  gnu/testlet/config.class
	here=`/bin/pwd`; cd $(srcdir); \
	  CLASSPATH=$$CLASSPATH:$$here:`/bin/pwd` $(JAVAC) $(JAVACFLAGS) -d $$here \
	    $(harness_files) $(CHOICES)
	touch classes.stamp

gnu/testlet/config.class: gnu/testlet/config.java
	$(JAVAC) $(JAVACFLAGS) gnu/testlet/config.java

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

if CLASS_FILES
STAMP = classes.stamp

$(javao_files): classes.stamp

%.o: %.class classes.stamp
	CLASSPATH=.:$(srcdir) $(GCJ) -fassume-compiled $(GCJFLAGS) -c -o $@ $<

else # CLASS_FILES

%.o: %.java
## Eww.
	@-$(mkinstalldirs) $(dir $@) > /dev/null 2>&1
	CLASSPATH=.:$(srcdir) $(GCJ) -fassume-compiled $(GCJFLAGS) -c -o $@ $<

endif # CLASS_FILES

java_files = $(harness_files) $(CHOICES)
class_files = $(patsubst %.java,%.class,$(java_files)) \
	gnu/testlet/config.class
javao_files = $(patsubst %.java,%.o,$(CHOICES))

check_PROGRAMS = SimpleTestHarness

AM_GCJFLAGS = -I. -I$(srcdir)

SimpleTestHarness_SOURCES = gnu/testlet/config.java $(harness_files)
SimpleTestHarness_DEPENDENCIES = $(STAMP) $(javao_files) 
SimpleTestHarness_LDADD = $(javao_files)
SimpleTestHarness_LDFLAGS = --main=gnu.testlet.SimpleTestHarness

check-local: $(check_PROGRAMS) $(CHOICES)
	cat classes | ./SimpleTestHarness$(EXEEXT) $(TESTFLAGS)

recheck:
	test -f .save-keys || : > .save-keys
## Always remove the test program to force a relink.
	@rm -f SimpleTestHarness$(EXEEXT)
	$(MAKE) KEYS="`cat .save-keys`" check

MOSTLYCLEANFILES = $(javao_files)
CLEANFILES = $(class_files) classes.stamp

else  ## USE_GCJ

STAMP = classes.stamp

check-local: classes.stamp
	cat classes | \
	$(JAVA) gnu.testlet.SimpleTestHarness $(TESTFLAGS)

recheck:
	test -f .save-keys || : > .save-keys
	$(MAKE) KEYS="`cat .save-keys`" check

CLEANFILES = classes.stamp

endif ## USE_GCJ

## For now can't define this conditionally.
SUFFIXES = .class .java
