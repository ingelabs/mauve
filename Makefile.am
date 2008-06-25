## Process this file with automake to produce Makefile.in.

## FIXME: dependencies
AUTOMAKE_OPTIONS = foreign subdir-objects no-dependencies

JAVACFLAGS = -g -1.5

TESTFLAGS =

check_DATA = $(STAMP)

EXTRA_DIST =  Harness.java RunnerProcess.java gnu junit

VERSION = ${shell date +%F}

harness_files = \
	$(srcdir)/Harness.java \
	$(srcdir)/RunnerProcess.java \
	$(srcdir)/gnu/testlet/TestHarness.java \
	$(srcdir)/gnu/testlet/Testlet.java \
	$(srcdir)/gnu/testlet/TestSecurityManager.java \
	$(srcdir)/gnu/testlet/ResourceNotFoundException.java \
	$(srcdir)/gnu/testlet/TestReport.java \
	$(srcdir)/gnu/testlet/TestResult.java \
	$(srcdir)/gnu/testlet/VisualTestlet.java \
	\
	gnu/testlet/config.java \
	\
	$(srcdir)/junit/framework/*.java \
	$(srcdir)/junit/runner/*.java \
	$(srcdir)/junit/textui/*.java

harness:
	$(JAVAC) -d . $(harness_files)

all-local: harness

check-local:
	$(JAVA) Harness
 
## For now can't define this conditionally.
SUFFIXES = .class .java
clean-local:
	find . -name '*.class' -print | xargs rm -f
