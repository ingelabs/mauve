// Tags: not-a-test

// Copyright (C) 2005 David Gilbert <david.gilbert@object-refinery.com>

// This file is part of Mauve.

// Mauve is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.

// Mauve is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with Mauve; see the file COPYING.  If not, write to
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

package gnu.testlet.java.io.ObjectInputStream;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class TestObjectInputValidation implements ObjectInputValidation, Serializable {
  private boolean validated;
  private String name;
  public TestObjectInputValidation(String name) 
  {      
    this.name = name;
    this.validated = false;
  }
  public boolean isValidated() 
  {
    return this.validated;
  }
  public void validateObject()
  {
    this.validated = true;
  }
  private void writeObject(ObjectOutputStream stream) throws IOException 
  {
    stream.defaultWriteObject();
  }
  private void readObject(ObjectInputStream stream) 
      throws IOException, ClassNotFoundException 
  {
    stream.defaultReadObject();
    stream.registerValidation(this, 10);
  }

}
