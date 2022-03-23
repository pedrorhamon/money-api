package com.starking.moneyapi.config;

import com.nimbusds.oauth2.sdk.as.AuthorizationServerConfigurationRequest;
import com.nimbusds.oauth2.sdk.id.Issuer;

public class AuthorizationServerConfig extends AuthorizationServerConfigurationRequest {
	
	public AuthorizationServerConfig(Issuer issuer) {
		super(issuer);
	}
}
