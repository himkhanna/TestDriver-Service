package com.element.wex.driver;

import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

public class OutLoggingInterceptor extends LoggingOutInterceptor {

	 public OutLoggingInterceptor() {
		 super();
		 super.setPrettyLogging(true);
	       
	    }

	    @Override
	    protected String formatLoggingMessage(LoggingMessage loggingMessage) {
	        String soapXmlPayload = loggingMessage.getPayload() != null ? loggingMessage.getPayload().toString() : null;

	        // do what you want with the payload... in my case, I stuck it in a JMS Queue

	        return super.formatLoggingMessage(loggingMessage);
	    }
	    
	    @Override
	    protected String transform(String originalLogString) {
	    	
	    	SecureLoggingUtil secureLoggingUtil=new SecureLoggingUtil();
	    	secureLoggingUtil.setMaskingChar("*");
	    	secureLoggingUtil.setSecureFields("Password");
	    	return secureLoggingUtil.maskSensitiveFields(originalLogString);
	    }
}
