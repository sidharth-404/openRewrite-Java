package org.agoncal.application.petstore.security;

import org.agoncal.application.petstore.util.ConfigProperty;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.net.URISyntaxException;

/**
 * @author blep
 *         Date: 16/02/12
 *         Time: 07:28
 */
public class LoginContextProducer {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private SimpleCallbackHandler callbackHandler;

    // ======================================
    // =          Business methods          =
    // ======================================

    @Produces
    public LoginContext produceLoginContext(@ConfigProperty("loginConfigFile") String loginConfigFileName,
                                            @ConfigProperty("loginModuleName") String loginModuleName) throws LoginException, URISyntaxException {
    	
    	
    	System.out.println("loginconfig file : "+ (loginConfigFileName != null?loginConfigFileName: "empty config name....."));
    	System.out.println("loginModuleName : "+ (loginModuleName!= null?loginModuleName: "empty module name....."));    	
    	
    	System.out.println("URI1 : "+LoginContextProducer.class.getResource(loginConfigFileName).toURI().getPath());
    	
    	System.out.println("URI2 : "+LoginContextProducer.class.getResource(loginConfigFileName).toURI());
    	
    	System.out.println("URI3 : "+new File(LoginContextProducer.class.getResource(loginConfigFileName).toURI().getPath()));
    	
        //System.setProperty("java.security.auth.login.config", new File(LoginContextProducer.class.getResource(loginConfigFileName).toURI()).getPath());

    	System.setProperty("java.security.auth.login.config", new File(LoginContextProducer.class.getResource(loginConfigFileName).toURI().getPath()).getPath());

    	
        try {
            return new LoginContext(loginModuleName, callbackHandler);
        } catch (Exception e) {
            System.out.println("ouch!!!");
            return null;
        }
    }

}
