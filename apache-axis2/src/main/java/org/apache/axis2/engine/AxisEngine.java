package org.apache.axis2.engine;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.agent.instrumentation.axis2.Axis2Headers;

@Weave(type=MatchType.ExactClass)
public abstract class AxisEngine {

	@Trace(dispatcher=true)
	public static void send(MessageContext msgContext) throws AxisFault {
		Axis2Headers wrapper = new Axis2Headers(msgContext);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(wrapper);
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_LOW, true, "AxisEngine", new String[] {"Send",msgContext.getSoapAction()});
		NewRelic.addCustomParameter("Message ID", msgContext.getMessageID());
		Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public static Handler.InvocationResponse receive(MessageContext msgContext) throws AxisFault {
		Axis2Headers wrapper = new Axis2Headers(msgContext);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(wrapper);
		NewRelic.addCustomParameter("Message ID", msgContext.getMessageID());
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_LOW, true, "AxisEngine", new String[] {"Receive",msgContext.getSoapAction()});
		return Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public static void sendFault(MessageContext msgContext) throws AxisFault {
		Axis2Headers wrapper = new Axis2Headers(msgContext);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(wrapper);
		Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public static Handler.InvocationResponse resume(MessageContext msgContext) throws AxisFault {
		Axis2Headers wrapper = new Axis2Headers(msgContext);
		NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Other, wrapper);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public static Handler.InvocationResponse resumeReceive(MessageContext msgContext) throws AxisFault {
		Axis2Headers wrapper = new Axis2Headers(msgContext);
		NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Other, wrapper);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public static Handler.InvocationResponse resumeSend(MessageContext msgContext) throws AxisFault {
		Axis2Headers wrapper = new Axis2Headers(msgContext);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(wrapper);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public static void resumeSendFault(MessageContext msgContext) throws AxisFault {
		Axis2Headers wrapper = new Axis2Headers(msgContext);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(wrapper);
		Weaver.callOriginal();
	}

}
