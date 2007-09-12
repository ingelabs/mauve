## Process this file with automake to produce Makefile.in.

## FIXME: dependencies
AUTOMAKE_OPTIONS = foreign subdir-objects no-dependencies

JAVACFLAGS = -g -1.5

TESTFLAGS =

check_DATA = $(STAMP)

EXTRA_DIST =  Harness.java RunnerProcess.java gnu junit

VERSION = ${shell date +%F}

harness_files = \
	Harness.java \
	RunnerProcess.java \
	gnu/testlet/config.java \
	gnu/testlet/TestHarness.java \
	gnu/testlet/Testlet.java \
	gnu/testlet/TestSecurityManager.java \
	gnu/testlet/ResourceNotFoundException.java \
	gnu/testlet/TestReport.java \
	gnu/testlet/TestResult.java \
	gnu/testlet/VisualTestlet.java

harness:
	$(JAVAC) $(harness_files)

all-local: harness

check-local:
	$(JAVA) Harness
 
## For now can't define this conditionally.
SUFFIXES = .class .java
clean-local:
	find . -name '*.class' -print | xargs rm -f
