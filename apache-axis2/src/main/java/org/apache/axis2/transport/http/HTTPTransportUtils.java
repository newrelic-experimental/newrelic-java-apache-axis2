package org.apache.axis2.transport.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.engine.Handler;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.agent.instrumentation.axis2.Axis2Utils;

@Weave
public abstract class HTTPTransportUtils {

	@SuppressWarnings("rawtypes")
	@Trace(dispatcher=true)
	public static boolean processHTTPGetRequest(MessageContext msgContext, OutputStream out, String soapAction, 
			String requestURI, ConfigurationContext configurationContext, Map requestParameters) throws AxisFault {
		boolean return_bool = Weaver.callOriginal();
		String[] transactionNames = Axis2Utils.getAxisTransactionName(msgContext, requestURI, soapAction);
		if(transactionNames.length > 0) {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_HIGH, true, "Axis HTTP Get", transactionNames);
		}
		return return_bool;
	}

	@Trace(dispatcher=true)
	public static Handler.InvocationResponse processHTTPPostRequest(MessageContext msgContext, InputStream in, 
			OutputStream out, String contentType, String soapActionHeader, String requestURI) throws AxisFault {
		Handler.InvocationResponse return_response = Weaver.callOriginal();
		String[] transactionNames = Axis2Utils.getAxisTransactionName(msgContext, requestURI, soapActionHeader);
		if(transactionNames.length > 0) {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_HIGH, true, "Axis HTTP Post", transactionNames);
		}
		return return_response;
	}
}