package org.apache.axis2.receivers;

import java.util.logging.Level;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.AxisMessage;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.agent.instrumentation.axis2.Axis2Headers;

@Weave(type=MatchType.BaseClass)
public abstract class AbstractMessageReceiver {

	@Trace(dispatcher=true)
	protected void invokeBusinessLogic(MessageContext msgCtx) throws AxisFault {
		AxisMessage axisMsg = msgCtx.getAxisMessage();
		NewRelic.getAgent().getLogger().log(Level.FINE, "AbstractMessageReceiver - invokeBusinessLogic: {0}", 
				new Object[] {getClass().getCanonicalName()});
		String axisName = axisMsg.getName();
		String axisOpName = axisMsg.getAxisOperation().getName().getLocalPart();
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName(new String[]{"Custom", "MessageReceiver", axisName, axisOpName});
		traced.addRollupMetricName(new String[]{"Custom", "MessageReceiver", "invokeBusinessLogic", axisName});
		Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public void receive(MessageContext msgCtx) throws AxisFault {
		AxisMessage axisMsg = msgCtx.getAxisMessage();
		NewRelic.getAgent().getLogger().log(Level.FINE, "AbstractMessageReceiver - receive: {0}", 
				new Object[] {getClass().getCanonicalName()});
		Axis2Headers wrapper = new Axis2Headers(msgCtx);
		NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Other, wrapper);
		String axisName = axisMsg.getName();
		String axisOpName = axisMsg.getAxisOperation().getName().getLocalPart();
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName(new String[]{"Custom", "MessageReceiver", axisName, axisOpName});
		traced.addRollupMetricName(new String[]{"Custom", "MessageReceiver", axisName});
		Weaver.callOriginal();
	}
}
