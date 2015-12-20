package com.element.wex.driver;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingMessage;

public class InLoggingInterceptor extends LoggingInInterceptor {

	public InLoggingInterceptor() {
        super();
        super.setPrettyLogging(true);
    }

    @Override
    protected String formatLoggingMessage(LoggingMessage loggingMessage) {
        String soapXmlPayload = loggingMessage.getPayload() != null ? loggingMessage.getPayload().toString() : null;

        // do what you want with the payload... in my case, I stuck it in a JMS Queue

        return super.formatLoggingMessage(loggingMessage);
    }
	
}
