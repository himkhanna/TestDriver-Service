package com.element.wex.driver;


import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

import com.wrightexpress.authprofile.authorizationprofilemanagement.AuthorizationProfileManagement;
import com.wrightexpress.authprofile.authorizationprofilemanagement.CWSAuthorizationProfileManagement;
import com.wrightexpress.driver.drivermanagement.CWSDriverManagement;
import com.wrightexpress.driver.drivermanagement.DriverManagement;
import com.wrightexpress.driver.drivermanagement.DriverSearchRequestType;
import com.wrightexpress.driver.drivermanagement.DriverSearchResponseType;
import com.wrightexpress.driver.drivermanagement.WexGeneralFaultMessage;
import com.wrightexpress.vehiclecard.vehiclecardmanagement.CWSVehicleCardManagement;
import com.wrightexpress.vehiclecard.vehiclecardmanagement.VehicleCardManagement;

public class CXFTestClient {

    public static void main(String[] args) {
        

        DriverSearchRequestType d = new DriverSearchRequestType();
        DriverSearchRequestType.Driver driver = new DriverSearchRequestType.Driver();
        driver.setAccountNumber("6900460473000009746");
        d.setDriver(driver);
               
        Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER, "PHHWEBSERV_XC");
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "com.element.wex.driver.UTPasswordCallback");
        
        CWSDriverManagement driverCWS = new CWSDriverManagement();
        DriverManagement driverManagement = driverCWS.getDriverManagementSOAP();
        CWSAuthorizationProfileManagement profileAuthorizationCWS=new CWSAuthorizationProfileManagement();
        AuthorizationProfileManagement profileManagement=profileAuthorizationCWS.getAuthorizationProfileManagementSOAP();
        BindingProvider provider = (BindingProvider) driverManagement;
        provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "https://webservices-xc.wexinc.com/EWSDriverProject/ProxyServices/DriverProxyv1"); 
        CWSVehicleCardManagement vehicleCWS=new CWSVehicleCardManagement();
        VehicleCardManagement vehicleManagement=vehicleCWS.getVehicleCardManagementSOAP();
       
        org.apache.cxf.endpoint.Client driverclient = ClientProxy.getClient(driverManagement);
        
        driverclient.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
        
        try {
			DriverSearchResponseType ds = driverManagement.search(d);
			System.out.println(ds.getTotalDriversFound());
			System.out.println(ds.getDriver().size());
		} catch (WexGeneralFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	            
    }
}
