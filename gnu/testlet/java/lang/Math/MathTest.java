/* Copyright (C) 1999 Hewlett-Packard Company

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

// Tags: JLS1.0

package gnu.testlet.java.lang.Math;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class MathTest implements Testlet
{

  protected static TestHarness harness;
	public void test_Basics()
	{
	 	if ( Math.E != 2.7182818284590452354 )
			harness.fail("Error : test_Basics failed - 1");

		if ( Math.PI != 3.14159265358979323846 )
			harness.fail("Error : test_Basics failed - 2");
	}

	public void test_sincostan()
	{
		if (  !(new Double(Math.sin( Double.NaN ))).isNaN() )
			harness.fail("Error : test_sincostan failed - 1");
		if (  !(new Double(Math.sin( Double.POSITIVE_INFINITY ))).isNaN() )
			harness.fail("Error : test_sincostan failed - 2");
		if (  !(new Double(Math.sin( Double.NEGATIVE_INFINITY ))).isNaN() )
			harness.fail("Error : test_sincostan failed - 3");
		if (  Math.sin( -0.0 ) != -0.0 )
			harness.fail("Error : test_sincostan failed - 4");
		if (  Math.sin( 0.0 ) != 0.0 )
			harness.fail("Error : test_sincostan failed - 5");

		if (  !(new Double(Math.cos( Double.NaN ))).isNaN() )
			harness.fail("Error : test_sincostan failed - 6");
		if (  !(new Double(Math.cos( Double.POSITIVE_INFINITY ))).isNaN() )
			harness.fail("Error : test_sincostan failed - 7");
		if (  !(new Double(Math.cos( Double.NEGATIVE_INFINITY ))).isNaN() )
			harness.fail("Error : test_sincostan failed - 8");

		if (  !(new Double(Math.tan( Double.NaN ))).isNaN() )
			harness.fail("Error : test_sincostan failed - 9");
		if (  !(new Double(Math.tan( Double.POSITIVE_INFINITY ))).isNaN() )
			harness.fail("Error : test_sincostan failed - 10");
		if (  !(new Double(Math.tan( Double.NEGATIVE_INFINITY ))).isNaN())
			harness.fail("Error : test_sincostan failed - 11");
		if (  Math.tan( -0.0 ) != -0.0 )
			harness.fail("Error : test_sincostan failed - 12");
		if (  Math.tan( 0.0 ) != 0.0 )
			harness.fail("Error : test_sincostan failed - 13");

		if ( Math.sin( Math.PI / 2.0 + Math.PI /6.0 ) <= 0.0 )
			harness.fail("Error : test_sincostan failed - 14");
		if ( Math.cos( Math.PI / 2.0 + Math.PI /6.0 ) >= 0.0 )
			harness.fail("Error : test_sincostan failed - 14");
		if ( Math.tan( Math.PI / 2.0 + Math.PI /6.0 ) >= 0.0 )
			harness.fail("Error : test_sincostan failed - 14");

	}

	public void test_asinacosatan()
	{
		if (  !(new Double(Math.asin( Double.NaN ))).isNaN() )
			harness.fail("Error : test_asinacosatan failed - 1");
		if (  Math.asin( -0.0 ) != -0.0 )
			harness.fail("Error : test_asinacosatan failed - 2");
		if (  Math.asin( 0.0 ) != 0.0 )
			harness.fail("Error : test_asinacosatan failed - 3");

		if (  !(new Double(Math.asin( 10.0 ))).isNaN() )
			harness.fail("Error : test_asinacosatan failed - 4");


		if (  !(new Double(Math.acos( Double.NaN ))).isNaN() )
			harness.fail("Error : test_asinacosatan failed - 5");
		if (  !(new Double(Math.acos( 10.0 ))).isNaN() )
			harness.fail("Error : test_asinacosatan failed - 6");


		if (  !(new Double(Math.atan( Double.NaN ))).isNaN() )
			harness.fail("Error : test_asinacosatan failed - 7");
		if (  Math.atan( -0.0 ) != -0.0 )
			harness.fail("Error : test_asinacosatan failed - 8");
		if (  Math.atan( 0.0 ) != 0.0 )
			harness.fail("Error : test_asinacosatan failed - 9");

	}

	public void test_atan2()
	{
		if (!(new Double( Math.atan2 (1.0 , Double.NaN ))).isNaN())
			harness.fail("Error : test_atan2 failed - 1");
		if (!(new Double( Math.atan2 (Double.NaN,1.0 ))).isNaN())
			harness.fail("Error : test_atan2 failed - 2");

		if (( Math.atan2(0.0, 10.0 ) != -0.0 ) ||
			( Math.atan2(2.0 , Double.POSITIVE_INFINITY ) != -0.0 ))
			harness.fail("Error : test_atan2 failed - 3");

		if (( Math.atan2(-0.0, 10.0 ) != -0.0 ) ||
			( Math.atan2(-2.0 , Double.POSITIVE_INFINITY ) != -0.0 ))
			harness.fail("Error : test_atan2 failed - 4");

		if (( Math.atan2(0.0, -10.0 ) != Math.PI) ||
			( Math.atan2(2.0 , Double.NEGATIVE_INFINITY ) != Math.PI ))
			harness.fail("Error : test_atan2 failed - 4");

		if (( Math.atan2(-0.0, -10.0 ) != -Math.PI) ||
			( Math.atan2(-2.0 , Double.NEGATIVE_INFINITY ) != -Math.PI ))
			harness.fail("Error : test_atan2 failed - 5");

		if (( Math.atan2(10.0, 0.0 ) != Math.PI/2.0) ||
			( Math.atan2(Double.POSITIVE_INFINITY , 3.0) != Math.PI /2.0))
			harness.fail("Error : test_atan2 failed - 6");

		if (( Math.atan2(-10.0, 0.0 ) != -Math.PI/2.0) ||
			( Math.atan2(Double.NEGATIVE_INFINITY , 3.0) != -Math.PI /2.0))
			harness.fail("Error : test_atan2 failed - 7");

		if (( Math.atan2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY ) != Math.PI/4.0))
			harness.fail("Error : test_atan2 failed - 8");

		if (( Math.atan2(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY ) != Math.PI*3.0/4.0))
			harness.fail("Error : test_atan2 failed - 9");

		if (( Math.atan2(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY ) != -Math.PI/4.0))
			harness.fail("Error : test_atan2 failed - 10");

		if (( Math.atan2(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY ) != -Math.PI*3.0/4.0))
			harness.fail("Error : test_atan2 failed - 11");
	}

	public void test_exp()
	{
		if ( !(new Double(Math.exp( Double.NaN ))).isNaN() )
			harness.fail("Error : test_exp failed - 1");

		if ( !(new Double(Math.exp( Double.POSITIVE_INFINITY))).isInfinite() )
			harness.fail("Error : test_exp failed - 2");

		if ( Math.exp( Double.NEGATIVE_INFINITY) != 0.0 )
			harness.fail("Error : test_exp failed - 3");
	}

	public void test_log()
	{														
		if ( !(new Double(Math.log( Double.NaN ))).isNaN() )
			harness.fail("Error : test_log failed - 1");
		if ( !(new Double(Math.log( -1.0 ))).isNaN() )
			harness.fail("Error : test_log failed - 2");

		if ( !(new Double(Math.log( Double.POSITIVE_INFINITY ))).isInfinite() )
			harness.fail("Error : test_log failed - 3");
	}

	public void test_sqrt()
	{
		if ( !(new Double(Math.sqrt( Double.NaN ))).isNaN() ||
			 !(new Double(Math.sqrt( -10.0 ))).isNaN())
			harness.fail("Error : test_sqrt failed - 1");

		if ( !(new Double(Math.sqrt( Double.NaN ))).isNaN() ||
			 !(new Double(Math.sqrt( -10.0 ))).isNaN())
			harness.fail("Error : test_sqrt failed - 2");

		if ( !(new Double(Math.sqrt( Double.POSITIVE_INFINITY))).isInfinite())
			harness.fail("Error : test_sqrt failed - 3");

		if ( Math.sqrt( -0.0) != -0.0 ||  Math.sqrt( 0.0) != 0.0 )
			harness.fail("Error : test_sqrt failed - 4");

		if ( Math.sqrt( -0.0) != -0.0 ||  Math.sqrt( 0.0) != 0.0 )
			harness.fail("Error : test_sqrt failed - 5");


		double sq = Math.sqrt(4.0);
		if (!( sq >= 1.9999 &&  sq <= 2.111 ))
			harness.fail("Error : test_sqrt failed - 6");
	}

	public void test_pow()
	{
		if ( Math.pow(1.0 , 0.0 ) != 1.0 )
			harness.fail("Error : test_pow failed - 1");

		if ( Math.pow(2.0 , -0.0 ) != 1.0 )
			harness.fail("Error : test_pow failed - 2");
		
		if ( Math.pow(123.0 , 1.0 ) != 123.0 )
			harness.fail("Error : test_pow failed - 3");

		if ( !(new Double(Math.pow( 10.0, Double.NaN ))).isNaN())
			harness.fail("Error : test_pow failed - 4");

		if ( !(new Double(Math.pow( Double.NaN, 1.0 ))).isNaN())
			harness.fail("Error : test_pow failed - 5");

		if ( !(new Double(Math.pow( 2.0, Double.POSITIVE_INFINITY ))).isInfinite())
			harness.fail("Error : test_pow failed - 6");

		if ( !(new Double(Math.pow( 0.5, Double.NEGATIVE_INFINITY ))).isInfinite())
			harness.fail("Error : test_pow failed - 7");

		if ( Math.pow( 1.5, Double.NEGATIVE_INFINITY ) != 0.0 ||
			 Math.pow( 0.5, Double.POSITIVE_INFINITY ) != 0.0)
			harness.fail("Error : test_pow failed - 8");

		if ( !(new Double(Math.pow( 1.0, Double.POSITIVE_INFINITY ))).isNaN())
			harness.fail("Error : test_pow failed - 9");

		if ( Math.pow( 0.0, 1.0) != 0.0 ||
			 Math.pow( Double.POSITIVE_INFINITY , -1.0 ) != 0.0)
			harness.fail("Error : test_pow failed - 10");

		if ( !(new Double(Math.pow( 0.0, -1.0 ))).isInfinite() ||
			 !(new Double(Math.pow( Double.POSITIVE_INFINITY, 1.0 ))).isInfinite() )
			harness.fail("Error : test_pow failed - 11");

		if ( Math.pow( -0.0, 5.0) != -0.0 ||
			 Math.pow( Double.NEGATIVE_INFINITY , -7.0 ) != -0.0)
			harness.fail("Error : test_pow failed - 12");

		if ( Math.pow( -2.0, 6.0) != Math.pow(2.0,6.0))
			harness.fail("Error : test_pow failed - 13");

		if ( Math.pow( -2.0, 5.0) != -Math.pow(2.0,5.0))
			harness.fail("Error : test_pow failed - 14");

	}

	public void test_IEEEremainder()
	{
		if ( !(new Double(Math.IEEEremainder( Double.NaN, 1.0 ))).isNaN())
			harness.fail("Error :  test_IEEEremainder failed - 1");
		if ( !(new Double(Math.IEEEremainder( 1.0,Double.NaN))).isNaN()) 
			harness.fail("Error :  test_IEEEremainder failed - 2");
		if ( !(new Double(Math.IEEEremainder( Double.POSITIVE_INFINITY , 2.0))).isNaN())
			harness.fail("Error :  test_IEEEremainder failed - 3");
		if ( !(new Double(Math.IEEEremainder( 2.0,0.0))).isNaN() )
			harness.fail("Error :  test_IEEEremainder failed - 4");
		if ( Math.IEEEremainder( 3.0, Double.POSITIVE_INFINITY ) != 3.0 )
			harness.fail("Error :  test_IEEEremainder failed - 5");
	}
	
	public void test_ceil()
	{
		if ( Math.ceil(5.0) != 5.0 )
			harness.fail("Error :  test_ceil failed - 1");

		if ( Math.ceil(0.0) != 0.0 || Math.ceil(-0.0) != -0.0 )
			harness.fail("Error :  test_ceil failed - 2");

		if ( !(new Double(Math.ceil(Double.POSITIVE_INFINITY))).isInfinite() ||
			 !(new Double(Math.ceil(Double.NaN))).isNaN())
			harness.fail("Error :  test_ceil failed - 3");

		if ( Math.ceil(-0.5) != -0.0 )
			harness.fail("Error :  test_ceil failed - 4");

		if ( Math.ceil( 2.5 ) != 3.0 )
			harness.fail("Error :  test_ceil failed - 5");


	}

	public void test_floor()
	{
		if ( Math.floor(5.0) != 5.0 )
			harness.fail("Error :  test_floor failed - 1");

		if ( Math.floor(2.5) != 2.0 )
			harness.fail("Error :  test_floor failed - 2");

		if ( !(new Double(Math.floor(Double.POSITIVE_INFINITY))).isInfinite() ||
			 !(new Double(Math.floor(Double.NaN))).isNaN())
			harness.fail("Error :  test_floor failed - 3");

		if ( Math.floor(0.0) != 0.0 || Math.floor(-0.0) != -0.0 )
			harness.fail("Error :  test_floor failed - 4");

	}

	public void test_rint()
	{	
		if ( Math.rint( 2.3 ) != 2.0 )
			harness.fail("Error :  test_rint failed - 1");

		if ( Math.rint( 2.7 ) != 3.0 )
			harness.fail("Error :  test_rint failed - 2");


		if (Math.rint( 2.5) != 2.0 )
			harness.fail("Error :  test_rint failed - 3");

		if ( Math.rint( 2.0) != 2.0 )
			harness.fail("Error :  test_rint failed - 4");

		if ( Math.rint( 2.0) != 2.0 )
			harness.fail("Error :  test_rint failed - 5");

		if ( !(new Double(Math.rint(Double.POSITIVE_INFINITY))).isInfinite() ||
			 !(new Double(Math.rint(Double.NaN))).isNaN())
			harness.fail("Error :  test_rint failed - 6");

		if ( Math.rint(0.0) != 0.0 || Math.rint(-0.0) != -0.0 )
			harness.fail("Error :  test_rint failed - 7");
	}

	public void test_round()
	{
		if ( Math.round( 3.4 ) != 3 )
			harness.fail("Error :  test_round failed - 1");

		if ( Math.round( 9.55 ) != 10 )
			harness.fail("Error :  test_round failed - 2");

		if ( Math.round(Double.NaN) != 0 )
			harness.fail("Error :  test_round failed - 3");

		float f1 = Integer.MIN_VALUE;
		f1 -= 5;
		if ( Math.round(f1) != Integer.MIN_VALUE ||
			 Math.round(Float.NEGATIVE_INFINITY) != Integer.MIN_VALUE )
			harness.fail("Error :  test_round failed - 4");

		f1 = Integer.MAX_VALUE;
		f1 += 5;
		if ( Math.round(f1) != Integer.MAX_VALUE ||
			 Math.round(Float.POSITIVE_INFINITY) != Integer.MAX_VALUE )
			harness.fail("Error :  test_round failed - 5");

		double d1 = Long.MIN_VALUE;
		d1 -= 5;
		if ( Math.round(d1) != Long.MIN_VALUE ||
			 Math.round(Double.NEGATIVE_INFINITY) != Long.MIN_VALUE )
			harness.fail("Error :  test_round failed - 6");

		d1 = Long.MAX_VALUE;
		d1 += 5;
		if ( Math.round(d1) != Long.MAX_VALUE ||
			 Math.round(Double.POSITIVE_INFINITY) != Long.MAX_VALUE )
			harness.fail("Error :  test_round failed - 7");


		if ( Math.round( 3.4f ) != 3 )
			harness.fail("Error :  test_round failed - 8");

		if ( Math.round( 9.55f ) != 10 )
			harness.fail("Error :  test_round failed - 9");

		if ( Math.round(Float.NaN) != 0 )
			harness.fail("Error :  test_round failed - 10");
	}														  

	public void test_random()
	{
		if ( Math.random() < 0.0 || Math.random() > 1.0 )
			harness.fail("Error :  test_random failed - 1");
	}

	public void test_abs()
	{
		if ( Math.abs( 10 ) != 10 ) 
			harness.fail("Error :  test_abs failed - 1");

		if ( Math.abs( -23 ) != 23 )
			harness.fail("Error :  test_abs failed - 2");

		if ( Math.abs( Integer.MIN_VALUE ) != Integer.MIN_VALUE )
			harness.fail("Error :  test_abs failed - 3" );
		
		if ( Math.abs(-0) != 0 )
			harness.fail("Error :  test_abs failed - 4" );


		if ( Math.abs( 1000L ) != 1000 ) 
			harness.fail("Error :  test_abs failed - 5");

		if ( Math.abs( -2334242L ) != 2334242 )
			harness.fail("Error :  test_abs failed - 6");

		if ( Math.abs( Long.MIN_VALUE ) != Long.MIN_VALUE )
			harness.fail("Error :  test_abs failed - 7" );
		
		if ( Math.abs( 0.0f ) != 0.0f || Math.abs(-0.0f) != 0.0f )
			harness.fail("Error :  test_abs failed - 8" );
		
		if ( !(new Float(Math.abs( Float.POSITIVE_INFINITY ))).isInfinite() )
			harness.fail("Error :  test_abs failed - 9" );

		if ( !(new Float(Math.abs( Float.NaN ))).isNaN() )
			harness.fail("Error :  test_abs failed - 10" );

		if ( Math.abs( 23.34f ) != 23.34f )
			harness.fail("Error :  test_abs failed - 11" );

	
		if ( Math.abs( 0.0 ) != 0.0 || Math.abs(-0.0) != 0.0 )
			harness.fail("Error :  test_abs failed - 12" );
		
		if ( !(new Double(Math.abs( Double.POSITIVE_INFINITY ))).isInfinite() )
			harness.fail("Error :  test_abs failed - 13" );

		if ( !(new Double(Math.abs( Double.NaN ))).isNaN() )
			harness.fail("Error :  test_abs failed - 14" );

		if ( Math.abs( 23.34 ) != 23.34 )
			harness.fail("Error :  test_abs failed - 15" );

	}

	public void test_min()
	{
		if ( Math.min( 100 , 12 ) != 12 ) 
			harness.fail("Error :  test_min failed - 1" );

		if ( Math.min( Integer.MIN_VALUE , Integer.MIN_VALUE + 1 ) != Integer.MIN_VALUE )
			harness.fail("Error :  test_min failed - 2" );

		if ( Math.min( Integer.MAX_VALUE , Integer.MAX_VALUE -1 ) != Integer.MAX_VALUE -1 )
			harness.fail("Error :  test_min failed - 3" );
			
		if ( Math.min( 10 , 10 ) != 10 )
			harness.fail("Error :  test_min failed - 4" );

		if ( Math.min( 0 , -0 ) != -0 )
			harness.fail("Error :  test_min failed - 5" );


		if ( Math.min( 100L , 12L ) != 12L ) 
			harness.fail("Error :  test_min failed - 6" );

		if ( Math.min( Long.MIN_VALUE , Long.MIN_VALUE + 1 ) != Long.MIN_VALUE )
			harness.fail("Error :  test_min failed - 7" );

		if ( Math.min( Long.MAX_VALUE , Long.MAX_VALUE -1 ) != Long.MAX_VALUE -1 )
			harness.fail("Error :  test_min failed - 8" );
			
		if ( Math.min( 10L , 10L ) != 10L )
			harness.fail("Error :  test_min failed - 9" );

		if ( Math.min( 0L , -0L ) != -0L )
			harness.fail("Error :  test_min failed - 10" );

		
		if ( Math.min( 23.4f , 12.3f ) != 12.3f ) 
			harness.fail("Error :  test_min failed - 11" );

		if ( !(new Float(Math.min( Float.NaN ,  1.0f ))).isNaN()  )
			harness.fail("Error :  test_min failed - 12" );

		if ( Math.min( 10.0f , 10.0f ) != 10.0f )
			harness.fail("Error :  test_min failed - 13" );

		if ( Math.min( 0.0f , -0.0f ) != -0.0f )
			harness.fail("Error :  test_min failed - 14" );


		if ( Math.min( 23.4 , 12.3 ) != 12.3 ) 
			harness.fail("Error :  test_min failed - 15" );

		if ( !(new Double(Math.min( Double.NaN ,  1.0 ))).isNaN()  )
			harness.fail("Error :  test_min failed - 16" );

		if ( Math.min( 10.0 , 10.0 ) != 10.0 )
			harness.fail("Error :  test_min failed - 17" );

		if ( Math.min( 0.0 , -0.0 ) != -0.0 )
			harness.fail("Error :  test_min failed - 18" );

	}

	public void test_max()
	{
		if ( Math.max( 100 , 12 ) != 100 ) 
			harness.fail("Error :  test_max failed - 1" );

		if ( Math.max( Integer.MAX_VALUE , Integer.MAX_VALUE - 1 ) != Integer.MAX_VALUE )
			harness.fail("Error :  test_max failed - 2" );

		if ( Math.max( Integer.MIN_VALUE , Integer.MIN_VALUE + 1 ) != Integer.MIN_VALUE +1 )
			harness.fail("Error :  test_max failed - 3" );
			
		if ( Math.max( 10 , 10 ) != 10 )
			harness.fail("Error :  test_max failed - 4" );

		if ( Math.max( 0 , -0 ) != 0 )
			harness.fail("Error :  test_max failed - 5" );


		if ( Math.max( 100L , 12L ) != 100L ) 
			harness.fail("Error :  test_max failed - 6" );

		if ( Math.max( Long.MAX_VALUE , Long.MAX_VALUE - 1 ) != Long.MAX_VALUE )
			harness.fail("Error :  test_max failed - 7" );

		if ( Math.max( Long.MIN_VALUE , Long.MIN_VALUE +1 ) != Long.MIN_VALUE + 1 )
			harness.fail("Error :  test_max failed - 8" );
			
		if ( Math.max( 10L , 10L ) != 10L )
			harness.fail("Error :  test_max failed - 9" );

		if ( Math.max( 0L , -0L ) != 0L )
			harness.fail("Error :  test_max failed - 10" );

		
		if ( Math.max( 23.4f , 12.3f ) != 23.4f ) 
			harness.fail("Error :  test_max failed - 11" );

		if ( !(new Float(Math.max( Float.NaN ,  1.0f ))).isNaN()  )
			harness.fail("Error :  test_max failed - 12" );

		if ( Math.max( 10.0f , 10.0f ) != 10.0f )
			harness.fail("Error :  test_max failed - 13" );

		if ( Math.max( 0.0f , -0.0f ) != 0.0f )
			harness.fail("Error :  test_max failed - 14" );


		if ( Math.max( 23.4 , 12.3 ) != 23.4 ) 
			harness.fail("Error :  test_max failed - 15" );

		if ( !(new Double(Math.max( Double.NaN ,  1.0 ))).isNaN()  )
			harness.fail("Error :  test_max failed - 16" );

		if ( Math.max( 10.0 , 10.0 ) != 10.0 )
			harness.fail("Error :  test_max failed - 17" );

		if ( Math.max( 0.0 , -0.0 ) != 0.0 )
			harness.fail("Error :  test_max failed - 18" );
	}


	public void testall()
	{
		test_Basics();
		test_sincostan();
		test_asinacosatan();
		test_atan2();
		test_log();
		test_exp();
		test_sqrt();
		test_pow();
		test_IEEEremainder();
		test_ceil();
		test_floor();
		test_rint();
		test_round();
		test_random();
		test_abs();
		test_min();
		test_max();
	}

  public void test (TestHarness the_harness)
  {
    harness = the_harness;
    testall ();
  }

}
