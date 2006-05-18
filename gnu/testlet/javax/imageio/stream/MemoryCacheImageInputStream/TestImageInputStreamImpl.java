// Tags: JDK1.4

// Copyright (C) 2006 Red Hat

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

package gnu.testlet.javax.imageio.stream.MemoryCacheImageInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteOrder;

import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageInputStreamImpl;
import javax.imageio.stream.MemoryCacheImageInputStream;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Test ImageInputStreamImpl's read methods reading from an in-memory
 * source.
 */
public class TestImageInputStreamImpl
  implements Testlet
{

  public void test(TestHarness h)
  {
    try
      {
        // Test unsigned values.
        byte[] b = new byte[]
          { 
            (byte) 0x72,
            (byte) 0x70,
            (byte) 0x05,
            (byte) 0x77,
            (byte) 0xac,
            (byte) 0xf2,
            (byte) 0x3b,
            (byte) 0x67
          };

        ByteArrayInputStream bs = new ByteArrayInputStream(b);
        ImageInputStream i = new MemoryCacheImageInputStream(bs);

        // Test ByteOrder.BIG_ENDIAN, the default.
        h.check(i.read() == 114);
        i.seek(0);
        h.check(i.readBoolean() == true);
        i.seek(0);
        h.check(i.readByte() == 114);
        i.seek(0);
        h.check(i.readChar() == '\u7270');
        i.seek(0);
        h.check(Double.compare(i.readDouble(), 1.709290273164385E243) == 0);
        i.seek(0);
        h.check(Float.compare(i.readFloat(), 4.7541126E30f) == 0);
        i.seek(0);
        h.check(i.readInt() == 1919944055);
        i.seek(0);
        h.check(i.readLong() == 8246096929276181351L);
        i.seek(0);
        h.check(i.readShort() == 29296);
        i.seek(0);
        h.check(i.readUnsignedByte() == 114);
        i.seek(0);
        h.check(i.readUnsignedInt() == 1919944055);
        i.seek(0);
        h.check(i.readUnsignedShort() == 29296);

        // Test ByteOrder.LITTLE_ENDIAN.
        i.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        i.seek(0);
        h.check(i.read() == 114);
        i.seek(0);
        h.check(i.readBoolean() == true);
        i.seek(0);
        h.check(i.readByte() == 114);
        i.seek(0);
        h.check(i.readChar() == '\u7072');
        i.seek(0);
        h.check(Double.compare(i.readDouble(), 1.9456609400629563E189) == 0);
        i.seek(0);
        h.check(Float.compare(i.readFloat(), 2.7064693E33f) == 0);
        i.seek(0);
        h.check(i.readInt() == 1996845170);
        i.seek(0);
        h.check(i.readLong() == 7438806032077647986L);
        i.seek(0);
        h.check(i.readShort() == 28786);
        i.seek(0);
        h.check(i.readUnsignedByte() == 114);
        i.seek(0);
        h.check(i.readUnsignedInt() == 1996845170);
        i.seek(0);
        h.check(i.readUnsignedShort() == 28786);

        // Test unsigned values.
        b = new byte[]
          { 
            (byte) 0x92,
            (byte) 0x80,
            (byte) 0x05,
            (byte) 0x77,
            (byte) 0xac,
            (byte) 0xf2,
            (byte) 0x8b,
            (byte) 0xa7
          };

        bs = new ByteArrayInputStream(b);
        i = new MemoryCacheImageInputStream(bs);

        // Test ByteOrder.BIG_ENDIAN, the default.
        h.check(i.read() == 146);
        i.seek(0);
        h.check(i.readBoolean() == true);
        i.seek(0);
        h.check(i.readByte() == -110);
        i.seek(0);
        h.check(i.readChar() == '\u9280');
        i.seek(0);
        h.check(Double.compare(i.readDouble(), -1.4183142849706364E-219) == 0);
        i.seek(0);
        h.check(Float.compare(i.readFloat(), -8.079283E-28f) == 0);
        i.seek(0);
        h.check(i.readInt() == -1837103753);
        i.seek(0);
        h.check(i.readLong() == -7890300535592285273L);
        i.seek(0);
        h.check(i.readShort() == -28032);
        i.seek(0);
        h.check(i.readUnsignedByte() == 146);
        i.seek(0);
        h.check(i.readUnsignedInt() == 2457863543L);
        i.seek(0);
        h.check(i.readUnsignedShort() == 37504);

        // Test ByteOrder.LITTLE_ENDIAN.
        i.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        i.seek(0);
        h.check(i.read() == 146);
        i.seek(0);
        h.check(i.readBoolean() == true);
        i.seek(0);
        h.check(i.readByte() == -110);
        i.seek(0);
        h.check(i.readChar() == '\u8092');
        i.seek(0);
        h.check(Double.compare(i.readDouble(), -3.463391436203922E-118) == 0);
        i.seek(0);
        h.check(Float.compare(i.readFloat(), 2.707747E33f) == 0);
        i.seek(0);
        h.check(i.readInt() == 1996849298);
        i.seek(0);
        h.check(i.readLong() == -6373734025067659118L);
        i.seek(0);
        h.check(i.readShort() == -32622);
        i.seek(0);
        h.check(i.readUnsignedByte() == 146);
        i.seek(0);
        h.check(i.readUnsignedInt() == 1996849298);
        i.seek(0);
        h.check(i.readUnsignedShort() == 32914);
      }
    catch(IOException e)
      {
        throw new RuntimeException(e);
      }
  }
}
