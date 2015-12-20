package com.element.wex.driver;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class UTPasswordCallback implements CallbackHandler {
	


	    /**
	     * Here, we attempt to get the password from the private
	     * alias/passwords map.
	     */
	    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
	        for (int i = 0; i < callbacks.length; i++) {
	            WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
	            pc.setPassword("ch4rt_h4nd");

	        }
	    }
	
	}
