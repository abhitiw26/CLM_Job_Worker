package com.ltim.lc.app.clm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ltim.lc.app.clm.model.ApplicationProps;
import com.ltim.lc.app.clm.model.Authorization;

@Component
public class AuthenticationUtil {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationUtil.class);

	@Autowired
	private ApplicationProps props;

	@Autowired
	private Authorization authorization;

	public String getAuthToken() {

		LOG.info("getAuthToken:: Start->{}", props);

		String token = null;
		
		authorization.setAudience(props.getAudience());
		authorization.setClientId(props.getClientId());
		authorization.setClientSecret(props.getClientSecret());
		authorization.setGrantType("client_credentials");

		RestTemplate rest = new RestTemplate();

		ResponseEntity<Authorization> res = rest.postForEntity(props.getUrl(), authorization, Authorization.class);

		//LOG.info("getAuthToken:: Auth API response{}->", res.getStatusCode());

		if (res.getStatusCode().equals(HttpStatus.OK)) {
			token = res.getBody().getToken();
		}
		LOG.info("getAuthToken:: End");
		return token;
	}
}
