dnl -----------------------------------------------------------
dnl Original by Mark Elbrecht <snowball3@bigfoot.com> 
dnl Modified by Brian Jones <cbj@gnu.org> for Mauve
dnl acx_check_pathname_style.m4 
dnl http://research.cys.de/autoconf-archive/

AC_DEFUN([ACX_CHECK_PATHNAME_STYLE_DOS],
[AC_MSG_CHECKING(for Windows and DOS and OS/2 style pathnames)
AC_CACHE_VAL(acx_cv_pathname_style_dos, 
  [AC_REQUIRE([AC_CANONICAL_HOST])
  acx_cv_pathname_style_dos="no"
  case ${host_os} in
    *djgpp | *mingw32* | *emx*) acx_cv_pathname_style_dos="yes" ;;
  esac
  ])
AC_MSG_RESULT($acx_cv_pathname_style_dos)
if test "$acx_cv_pathname_style_dos" = "yes"; then
  AC_DEFINE(HAVE_PATHNAME_STYLE_DOS,,[defined if running on a system with dos style paths])
  CHECK_PATH_SEPARATOR=';'
  CHECK_FILE_SEPARATOR='\\'
else
  CHECK_PATH_SEPARATOR=':'
  CHECK_FILE_SEPARATOR='/'
fi

AC_SUBST(CHECK_PATH_SEPARATOR)
AC_SUBST(CHECK_FILE_SEPARATOR)
])


dnl -----------------------------------------------------------
