package com.nr.agent.instrumentation.axis2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.axis2.context.MessageContext;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;

public class Axis2Headers implements Headers {
	
	private MessageContext msgCtx;
	
	public Axis2Headers(MessageContext messageCtx) {
		msgCtx = messageCtx;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public String getHeader(String key) {
		return (String) msgCtx.getProperty(key);
	}

	@Override
	public Collection<String> getHeaders(String name) {
		List<String> list = new ArrayList<>();
		String value = getHeader(name);
		if(value != null && !value.isEmpty()) {
			list.add(value);
		}
		return list;
	}

	@Override
	public void setHeader(String name, String value) {
		msgCtx.setProperty(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		msgCtx.setProperty(name, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getHeaderNames() {
		Iterator<String> iterator = msgCtx.getPropertyNames();
		List<String> names = new ArrayList<>();
		while(iterator.hasNext()) {
			names.add(iterator.next());
		}
		return names;
	}

	@Override
	public boolean containsHeader(String name) {
		Collection<String> names = getHeaderNames();
		return names.contains(name);
	}

}
