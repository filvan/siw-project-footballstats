package it.uniroma3.siw.footballstats.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.footballstats.model.Credentials;
import it.uniroma3.siw.footballstats.model.CustomOAuth2User;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//	@Autowired
//	private CredentialsService credentialsService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
		String loginName = oauth2User.getLogin();
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	

}
