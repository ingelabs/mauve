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

package gnu.testlet.java.util.AbstractSequentialList;
import java.util.*;

/*
*  Written by ACUNIA
*
*  this class extends AbstractSequentialList and is used to test java.uitl.AbstractSequentialList
*  (since it is an abstract class)
*  used by AcuniaAbstractListTest
*/
public class AcuniaExASList extends AbstractSequentialList
{
	
	public LinkedList v = new LinkedList();
	
	public AcuniaExASList(){
		super();
	}
	public AcuniaExASList(List l){
		super();
		v.addAll(l);
	}
	
	public int size() {
		return v.size();
	}
	public ListIterator listIterator(int idx) {
	 	return v.listIterator(idx);
	}
}	