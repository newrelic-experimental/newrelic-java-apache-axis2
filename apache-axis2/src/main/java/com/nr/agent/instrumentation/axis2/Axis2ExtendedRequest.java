package com.nr.agent.instrumentation.axis2;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.apache.axis2.transport.http.server.AxisHttpRequest;
import org.apache.http.Header;
import org.apache.http.params.HttpParams;

import com.newrelic.api.agent.ExtendedRequest;
import com.newrelic.api.agent.HeaderType;

public class Axis2ExtendedRequest extends ExtendedRequest {
	
	private AxisHttpRequest request = null;
	
	public Axis2ExtendedRequest(AxisHttpRequest req) {
		request = req;
	}

	@Override
	public String getRequestURI() {
		return request.getRequestURI();
	}

	@Override
	public String getRemoteUser() {
		return null;
	}

	@Override
	public Enumeration getParameterNames() {HttpParams params = request.getParams();
		
		return new Enumeration<Object>() {

			@Override
			public boolean hasMoreElements() {
				return false;
			}

			@Override
			public Object nextElement() {
				throw new NoSuchElementException();
			}
			
		};
	}

	@Override
	public String[] getParameterValues(String name) {
		return new String[] {};
	}

	@Override
	public Object getAttribute(String name) {
		return null;
	}

	@Override
	public String getCookieValue(String name) {
		return null;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public String getHeader(String name) {
		Header[] headers = request.getHeaders(name);
		if(headers != null && headers.length > 0) {
			return headers[0].getValue();
		}
		return null;
	}

	@Override
	public String getMethod() {
		return request.getMethod();
	}

}
