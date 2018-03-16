package com.sia;

import com.sia.base.ws.BaseServicesPortType;

public class TestClient {

	private static final String AFTER_FLUSH = "afterFlush";
	private static final String BETWEEN_PERSIST_AND_FLUSH = "betweenPersistAndFlush";
	private static final String BEFORE_PERSIST = "beforePersist";
	private static final String DONT_CALL = "dontCall";

	private BaseServicesPortType baseServices;
	
	public static void main(String[] args) {
		System.out.println("TestClient");
		
		WsUtil.setServerAddress("localhost:8080");
		
		TestClient testClient = new TestClient();
		testClient.createWsClient();
		testClient.callTestMethod();
	}

	public void callTestMethod() {
		baseServices.test(DONT_CALL);
	}

	public void createWsClient() {
		baseServices = WsUtil.getBaseServices();	
	}
	
}
