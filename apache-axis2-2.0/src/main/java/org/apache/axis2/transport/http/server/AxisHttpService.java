package org.apache.axis2.transport.http.server;

import org.apache.axis2.context.MessageContext;
import org.apache.hc.core5.http.protocol.HttpContext;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.agent.instrumentation.axis2.Axis2ExtendedRequest;

@Weave
public abstract class AxisHttpService {

	@Trace(dispatcher = true)
	 protected void doService(AxisHttpRequest request, AxisHttpResponse response, HttpContext context, MessageContext msgContext) {
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "AxisHttpService", "Axis2",request.getRequestURI(),request.getMethod());
		NewRelic.getAgent().getTransaction().setWebRequest(new Axis2ExtendedRequest(request));
		Weaver.callOriginal();
	}
}
