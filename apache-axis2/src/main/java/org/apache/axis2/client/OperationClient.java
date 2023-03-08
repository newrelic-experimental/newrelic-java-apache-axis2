package org.apache.axis2.client;

import org.apache.axis2.context.OperationContext;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.BaseClass)
public abstract class OperationClient {

	public abstract OperationContext getOperationContext();
	
	@Trace
	public void executeImpl(boolean b) {
		
		OperationContext oc1 = getOperationContext();
		if(oc1 != null) {
			String opName = oc1.getOperationName();
			String servicename = oc1.getServiceName();
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","OperationClient",getClass().getSimpleName(),"execute",servicename,opName);
			
		}
		Weaver.callOriginal();
	}
}
