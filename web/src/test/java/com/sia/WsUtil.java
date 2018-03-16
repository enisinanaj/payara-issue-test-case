package com.sia;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.sia.base.ws.BaseServicesPortType;

public class WsUtil {

	private static BaseServicesPortType  baseServices;
	private static String serverAddress;	
	
	public static BaseServicesPortType getBaseServices() {
		if (baseServices != null)
			return baseServices;

		try {
			String wsdlLocation = "http://" + serverAddress + "/test/BaseServices?wsdl";
			URL wsdlURL = new URL(wsdlLocation);
			String nameSpace = "http://ws.base.sia.com/";
			QName serviceName = new QName(nameSpace, "BaseServices");
			QName portName = new QName(nameSpace, "BaseServicesPort");

			Service service = Service.create(wsdlURL, serviceName);
			baseServices = service.getPort(portName, BaseServicesPortType.class);
			return baseServices;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setServerAddress(String serverAddress) {
		WsUtil.serverAddress = serverAddress;
	}

	public static String getServerAddress() {
		return serverAddress;
	}
}
