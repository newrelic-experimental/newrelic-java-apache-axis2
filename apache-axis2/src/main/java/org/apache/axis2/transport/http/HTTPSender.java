package org.apache.axis2.transport.http;

import java.net.URL;

import org.apache.axis2.context.MessageContext;

//import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class HTTPSender {

	@Trace(dispatcher=true,leaf=true)
	public void send(MessageContext msgContext, URL url, String soapActionString) {
		Weaver.callOriginal();
	}
}
