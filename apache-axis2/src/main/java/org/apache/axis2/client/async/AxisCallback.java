package org.apache.axis2.client.async;

import org.apache.axis2.context.MessageContext;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class AxisCallback {
	
	@NewField
	public Token token = null;
	
	@Trace(async = true)
	public void onMessage(MessageContext msgContext)  {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public void onFault(MessageContext msgContext) {
		Exception e = msgContext.getFailureReason();
		if(e != null) NewRelic.noticeError(e);
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public void onError(Exception e) {
		NewRelic.noticeError(e);
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}
	
	public void onComplete() {
		if(token != null) {
			token.expire();
			token = null;
		}
		Weaver.callOriginal();
	}
}
