## Process this file with automake to produce Makefile.in.

AUTOMAKE_OPTIONS = foreign

JAVAC=@JAVAC@
JCFLAGS=

## For now we just try to compile every Java file found in srcdir.
## This is specific to GNU make.
java_files := $(shell cd $(srcdir) && find gnu -name '*.java' -print)

classes.stamp: $(java_files)
	here=`/bin/pwd`; cd $(srcdir); \
	  CLASSPATH=$$here:`/bin/pwd` $(JAVAC) $(JCFLAGS) -d $$here \
	    $(subst $(srcdir)/,,$?)
	touch classes.stamp
