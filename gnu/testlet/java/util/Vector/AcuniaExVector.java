/* Copyright (C) 2001 ACUNIA

   This file is part of Mauve.

   Mauve is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   Mauve is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Mauve; see the file COPYING.  If not, write to
   the Free Software Foundation, 59 Temple Place - Suite 330,
   Boston, MA 02111-1307, USA.
*/

// Tags: JLS1.2

package gnu.testlet.java.util.Vector;
import java.util.*;

/**
*  Written by ACUNIA
*
*  this class extends Vector and is used to test java.uitl.Vector
*  used by AcuniaVectorTest
*/
public class AcuniaExVector extends Vector
{
	private boolean didRemoveRange=false;
	private int from = -1;
	private int to   = -1;
	public AcuniaExVector(Collection c){
		super(c);
	}
	
	public void removeRange(int fidx, int tidx) {
		didRemoveRange=true;
		to   = tidx;
		from = fidx;
		super.removeRange(fidx, tidx);
	}
	
	public boolean get_dRR() {
		return didRemoveRange;
	}
	public void set_dRR(boolean b) {
		didRemoveRange = b;
	}
	public int get_to() {
		return to;
	}
	public int get_from() {
		return from;
	}
}	