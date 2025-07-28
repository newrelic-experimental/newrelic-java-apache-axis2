package org.apache.axis2.transport.http;

import java.io.IOException;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Weave(type=MatchType.BaseClass)
public class AbstractAgent {

	@Trace(dispatcher=true)
	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Weaver.callOriginal();
	}
}
