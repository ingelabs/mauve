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

package gnu.testlet.java.util.AbstractMap;
import java.util.*;

/**
*  Written by ACUNIA
*
*  this class extends AbstractMap and is used to test java.util.AbstractMap
*  (since it is an abstract class)
*  used by AcuniaAbstractMapTest
*/
public class AcuniaExAbstractMap extends AbstractMap
{
	private Vector keys = new Vector();
	private Vector values = new Vector();
	private boolean edit = true;
	
	private boolean deleteInAM(Object e) {
	 	if  (!keys.contains(e)) return false;
	 	values.remove(keys.indexOf(e));
	 	return keys.remove(e);
	}
	
	public Vector getKeyV() {
		return (Vector)keys.clone();
	}
	public Vector getValuesV() {
		return (Vector)values.clone();
	}
	
	public AcuniaExAbstractMap(){
		super();
	}
	
	public Set entrySet() {
		return  new ESet();
	}

	public Object put(Object key, Object value) {
		if (edit) {
			if (keys.contains(key)) {
				return values.set(keys.indexOf(key),value);
			}
			values.add(value);
			keys.add(key);
			return null;
		}
		return super.put(key,value);
	}
	
	public void set_edit(boolean b) {
		edit = b;
	}
	
  private class ESet extends AbstractSet {
	
		public Iterator iterator() {
			return new EIterator();
		}
	
	        public int size() {
	        	return keys.size();
	        }
	

  }
	
  private class Entry implements Map.Entry {

  	private Object key;
  	private Object value;
  	
  	public Entry(Object k, Object v) {
         	key = k;
         	value = v;
        }

        public Object getKey() {
        	return key;
        }

        public Object getValue() {
        	return value;
        }

        public Object setValue(Object nv) {
        	Object ov = value;
        	value = nv;
        	return ov;
        }

        public boolean equals(Object o) {

        	if (!(o instanceof Map.Entry))return false;
        	Map.Entry e = (Map.Entry)o;
        	if (  e == null ) return false;
        	return ( (key == null ? e.getKey()==null : key.equals(e.getKey())) &&
                  (value == null ? e.getValue()==null : key.equals(e.getValue())));
        }

        public int hashCode() {
        	int kc = key == null ? 0 : key.hashCode();
        	int vc = value == null ? 0 : value.hashCode();
        	return kc ^ vc;
        }

  }

        private class EIterator implements Iterator {
        	int pos=0;
                int status=0;

                public EIterator() {}

                public  boolean hasNext() {
                	return  pos < size();
                }

                public Object next() {
                 	status = 1;
                 	if (pos>= size()) throw new NoSuchElementException("no elements left");
                 	pos++;			
			return new Entry(keys.get(pos-1) ,values.get(pos-1));                 	
                }

                public void remove() {
                        if (status != 1 ) throw new IllegalStateException("do a next() operation before remove()");
                      	deleteInAM(keys.get(pos-1));
                      	pos--;
                      	status=-1;
                }
        }
  	
}	