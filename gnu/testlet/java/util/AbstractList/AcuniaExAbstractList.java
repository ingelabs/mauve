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

package gnu.testlet.java.util.AbstractList;
import java.util.*;

/*
*  Written by ACUNIA
*
*  this class extends AbstractList and is used to test java.uitl.AbstractList
*  (since it is an abstract class)
*  used by AcuniaAbstractListTest
*/
public class AcuniaExAbstractList extends AbstractList
{
	private boolean edit=true;
	private boolean didRemoveRange=false;
	private boolean updateMC=false;
	private boolean sleepy=false;
	private int from = -1;
	private int to   = -1;
	
	public Vector v = new Vector();
	
	public AcuniaExAbstractList(){
		super();
	}
	
	public int size() {
		if (sleepy){
			try { Thread.sleep(150L); }
			catch(Exception e) {}
		}
		return v.size();
	}
		
	public Object get(int idx) {
		return v.get(idx);
	}
	
	public int getMC() {
		return modCount;
	}	
	
	public void set_edit(boolean b) {
	 	edit = b;
	}
	public void set_sleepy(boolean b) {
	 	sleepy = b;
	}
	public void set_updateMC(boolean b) {
	 	updateMC = b;
	}
	
	public void add(int idx, Object o) {
		if (edit) {
		 	v.add(idx , o);
		}
		else super.add(idx,o);
		if (updateMC) modCount++;
	}

	public Object remove(int idx) {
		if (edit) {
			if (updateMC) modCount++;
			return v.remove(idx);
		}
		System.out.println("calling remove from AbstractList");	
		return super.remove(idx);
	}
	
	public Object set(int idx , Object o) {
		if (edit) {
			if (updateMC) modCount++;
			return v.set(idx , o);
		}
		return super.set(idx , o);
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