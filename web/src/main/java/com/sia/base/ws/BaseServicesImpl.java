package com.sia.base.ws;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import com.sia.base.ws.BaseServicesPortType;
import com.sia.test.BaseServicesAdapterInterface;


@WebService(serviceName = "BaseServices",  portName = "BaseServicesPort", targetNamespace = "")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class BaseServicesImpl implements BaseServicesPortType {
	
	@EJB
	private BaseServicesAdapterInterface baseServicesAdapter;

	@Resource WebServiceContext wsContext;
	
	private BaseServicesAdapterInterface getBaseServicesIntf() {
		return baseServicesAdapter;
	}

	
	  @WebMethod(action = "urn:#BaseServices.test")
	  @WebResult(name = "testOut", targetNamespace = "", partName = "testResult")    
	  public String test(
      @WebParam(name = "testIn", targetNamespace = "", partName = "whenToRollbackOnly")
      String whenToRollbackOnly) {
			return getBaseServicesIntf().test();
	  }

   
}