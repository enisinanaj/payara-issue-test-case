package com.sia.test;

import javax.ejb.Stateless; 


@Stateless
public class BaseServicesAdapterImpl implements BaseServicesAdapterInterface {

	@Override
	public String test() {
		return "ok";
	}

	
}

