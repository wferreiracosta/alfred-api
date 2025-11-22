package br.com.wferreiracosta.alfred.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.wferreiracosta.alfred.securities.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		} catch (Exception e) {
			return null;
		}
	}
	
}
