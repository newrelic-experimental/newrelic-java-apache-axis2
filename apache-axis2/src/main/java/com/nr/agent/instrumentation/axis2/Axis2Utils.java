package com.nr.agent.instrumentation.axis2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;

public class Axis2Utils {
	
	public static String[] getAxisTransactionName(MessageContext msgContext, String requestURI, String soapAction) {
		ArrayList<String> transactionNameList = new ArrayList<String>();
		if (msgContext != null) {
			AxisService axisService = msgContext.getAxisService();
			AxisOperation axisOperation = msgContext.getAxisOperation();
			if(soapAction == null) {
				soapAction = msgContext.getSoapAction();
			}
			if(axisService != null && axisService.getName() != null) {
				String serviceName = axisService.getName();
				if(!serviceName.isEmpty()) {
					transactionNameList.add(serviceName);
					AgentBridge.publicApi.addCustomParameter("Service Name", serviceName);
				}
			} 
			if(axisOperation != null && axisOperation.getName() != null) {
				String opName = axisOperation.getName().getLocalPart();
				if(!opName.isEmpty()) {
					transactionNameList.add(opName);
					NewRelic.addCustomParameter("Operation Name", opName);
				}
			}
		}
		
		if (soapAction != null && !soapAction.isEmpty()) {
			String soapActionName = getSoapActionName(soapAction);
			NewRelic.addCustomParameter("SOAP Action", soapActionName);
			if(transactionNameList.isEmpty()) { 
				transactionNameList.add(soapActionName);
			}
		}
		
		if (requestURI != null && !requestURI.isEmpty()) {
			NewRelic.addCustomParameter("Request URI", requestURI);
			if(transactionNameList.isEmpty()) { 
				transactionNameList.add(requestURI);
			}
		}
		
		if(!transactionNameList.isEmpty()) {
			return transactionNameList.toArray(new String[transactionNameList.size()]);
		} else {
			return new String[0];
		}
	}
	
	public static String[] getAxisTransactionName(HttpServletRequest request) {
		return getAxisTransactionName(null, request.getRequestURI(), null);
	}
	
	public static String[] getAxisTransactionName(MessageContext msgContext) {
		return getAxisTransactionName(msgContext, null, null);
	}
	
	public static String getSoapActionName(String soapAction) {
	    if (soapAction.startsWith("urn:")) {
	    	return soapAction.substring(4);
	    } else {
	    	return soapAction;
	    }
	}
}