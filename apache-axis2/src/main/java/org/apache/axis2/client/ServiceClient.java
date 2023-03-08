package org.apache.axis2.client;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.ServiceContext;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class ServiceClient {
	
	public abstract ServiceContext getServiceContext();

	@Trace
	public OMElement sendReceive(QName operationQName, OMElement xmlPayload) {
		ServiceContext serviceCtx = getServiceContext();
		if(serviceCtx != null) {
			String serviceName = serviceCtx.getName();
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Axis2","ServiceClient",serviceName,"sendReceive");
		}
		return Weaver.callOriginal();
	}
	
	@Trace
	public void sendReceiveNonBlocking(QName operation, OMElement elem, AxisCallback callback) {
		if(callback.token == null) {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				callback.token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		ServiceContext serviceCtx = getServiceContext();
		if(serviceCtx != null) {
			String serviceName = serviceCtx.getName();
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Axis2","ServiceClient",serviceName,"sendReceiveNonBlocking");
		}
		Weaver.callOriginal();
	}
	
	@Trace
	public void sendReceiveNonBlocking(OMElement elem, AxisCallback callback) {
		if(callback.token == null) {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				callback.token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		ServiceContext serviceCtx = getServiceContext();
		if(serviceCtx != null) {
			String serviceName = serviceCtx.getName();
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Axis2","ServiceClient",serviceName,"sendReceiveNonBlocking");
		}
		Weaver.callOriginal();
	}
	
	public void sendRobust(QName operation, OMElement elem)  {
		ServiceContext serviceCtx = getServiceContext();
		if(serviceCtx != null) {
			String serviceName = serviceCtx.getName();
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Axis2","ServiceClient",serviceName,"sendRobust");
		}
		Weaver.callOriginal();
	}
}
