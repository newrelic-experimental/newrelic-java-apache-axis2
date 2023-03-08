package org.apache.axis2.engine;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.agent.instrumentation.axis2.Axis2Utils;

@Weave(type=MatchType.Interface,
originalName = "org.apache.axis2.engine.Handler")
public abstract class Handler_instrumentation {

	public abstract String getName();
	
	@Trace(dispatcher=true)
	public Handler.InvocationResponse invoke(MessageContext msgContext) throws AxisFault {
		String soapActionName = Axis2Utils.getSoapActionName(msgContext.getSoapAction());
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", getName(), soapActionName});
		return Weaver.callOriginal();
	}
}
