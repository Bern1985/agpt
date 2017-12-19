package com.ancs.agpt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer {
		@Value("${server.https.port}")
		private int httpsport;
	 	
		@Override
	    public void customize(ConfigurableEmbeddedServletContainer container) {
	        container.setPort(httpsport);
	    }
}
