/*
 * #%L
 * ImgLib: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2013 Stephan Preibisch, Tobias Pietzsch, Barry DeZonia,
 * Stephan Saalfeld, Albert Cardona, Curtis Rueden, Christian Dietz, Jean-Yves
 * Tinevez, Johannes Schindelin, Lee Kamentsky, Larry Lindsey, Grant Harris,
 * Mark Hiner, Aivar Grislis, Martin Horn, Nick Perry, Michael Zinsmaier,
 * Steffen Jaensch, Jan Funke, Mark Longair, and Dimiter Prodanov.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of any organization.
 * #L%
 */

package mpicbg.imglib.type.numeric.integer;

import mpicbg.imglib.container.DirectAccessContainer;
import mpicbg.imglib.container.basictypecontainer.IntAccess;
import mpicbg.imglib.container.basictypecontainer.array.IntArray;
import mpicbg.imglib.cursor.Cursor;
import mpicbg.imglib.util.Util;

/**
 * TODO
 *
 * @author Stephan Preibisch
 * @author Stephan Saalfeld
 */
public abstract class GenericIntType<T extends GenericIntType<T>> extends IntegerTypeImpl<T>
{
	// the DirectAccessContainer
	final DirectAccessContainer<T, ? extends IntAccess> storage;
	
	// the (sub)DirectAccessContainer that holds the information 
	IntAccess b;
	
	// this is the constructor if you want it to read from an array
	public GenericIntType( DirectAccessContainer<T, ? extends IntAccess> intStorage )
	{
		storage = intStorage;
	}

	// this is the constructor if you want it to be a variable
	public GenericIntType( final int value )
	{
		storage = null;
		b = new IntArray( 1 );
		setValue( value );
	}

	// this is the constructor if you want it to be a variable
	public GenericIntType() { this( 0 ); }

	@Override
	public void updateContainer( final Cursor<?> c ) 
	{ 
		b = storage.update( c ); 
	}

	protected int getValue(){ return b.getValue( i ); }
	protected void setValue( final int f ){ b.setValue( i, f ); }
	
	@Override
	public void mul( final float c )
	{
		final int a = getValue();
		setValue( Util.round( a * c ) );
	}
	
	@Override
	public void mul( final double c )
	{
		final int a = getValue();
		setValue( ( int )Util.round( a * c ) );
	}
	
	@Override
	public void add( final T c )
	{
		final int a = getValue();
		setValue( a + c.getValue() );
	}

	@Override
	public void div( final T c )
	{
		final int a = getValue();
		setValue( a / c.getValue() );
	}

	@Override
	public void mul( final T c )
	{
		final int a = getValue( );
		setValue( a * c.getValue() );
	}

	@Override
	public void sub( final T c )
	{
		final int a = getValue( );
		setValue( a - c.getValue() );
	}

	@Override
	public int compareTo( final T c ) 
	{ 
		final int a = getValue();
		final int b = c.getValue();
		if ( a > b )
			return 1;
		else if ( a < b )
			return -1;
		else 
			return 0;
	}

	@Override
	public void set( final T c )
	{
		setValue( c.getValue() );
	}

	@Override
	public void setOne() { setValue( 1 ); }

	@Override
	public void setZero() { setValue( 0 ); }

	@Override
	public void inc()
	{
		int a = getValue();
		setValue( ++a );
	}

	@Override
	public void dec()
	{
		int a = getValue();
		setValue( --a );
	}
	
	@Override
	public String toString(){ return "" + getValue(); }
}
