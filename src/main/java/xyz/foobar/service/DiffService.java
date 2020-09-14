package xyz.foobar.service;

import java.io.Serializable;

import xyz.foobar.Diff;
import xyz.foobar.DiffEngine;
import xyz.foobar.DiffException;

public class DiffService implements DiffEngine, Serializable {

	public <T extends Serializable> T apply(T original, Diff<?> diff) throws DiffException {
		
		return null;
	}

	public <T extends Serializable> Diff<T> calculate(T original, T modified) throws DiffException {
		// TODO Auto-generated method stub
		return null;
	}

}
