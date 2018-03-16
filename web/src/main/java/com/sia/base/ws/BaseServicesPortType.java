
package com.sia.base.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name = "BaseServicesPortType", targetNamespace = "")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BaseServicesPortType {

	  @WebMethod(action = "urn:#BaseServices.test")
	  @WebResult(name = "testOut", targetNamespace = "", partName = "testResult")    
	  public String test(
        @WebParam(name = "testIn", targetNamespace = "", partName = "whenToRollbackOnly")
        String whenToRollbackOnly);

}
