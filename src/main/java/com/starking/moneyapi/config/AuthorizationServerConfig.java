package com.starking.moneyapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import com.nimbusds.oauth2.sdk.as.AuthorizationServerConfigurationRequest;
import com.nimbusds.oauth2.sdk.id.Issuer;

public class AuthorizationServerConfig extends AuthorizationServerConfigurationRequest {
	
	@Autowired
	private AuthenticationManager manager;

	public AuthorizationServerConfig(Issuer issuer) {
		super(issuer);
	}
}
