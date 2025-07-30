package org.apache.axis2.transport.http;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.agent.instrumentation.axis2.Axis2Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Weave
public abstract class AxisServlet {
	
	@Trace(dispatcher = true)
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String[] uri = Axis2Utils.getAxisTransactionName(request);
		if(uri.length > 0) {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "Axis", uri);
		}
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String[] uri = Axis2Utils.getAxisTransactionName(request);
		if(uri.length > 0) {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "Axis", uri);
		}
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String[] uri = Axis2Utils.getAxisTransactionName(request);
		if(uri.length > 0) {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "Axis", uri);
		}
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		String[] uri = Axis2Utils.getAxisTransactionName(request);
		if(uri.length > 0) {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "Axis", uri);
		}
		Weaver.callOriginal();
	}
}