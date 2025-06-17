package com.training.springkeycloak.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class JwtAuthConvertor implements Converter<Jwt, AbstractAuthenticationToken>{

	@Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
	private String clientId;
	
	
	private final JwtGrantedAuthoritiesConverter jwtAUthoritiesConvertor = new JwtGrantedAuthoritiesConverter();
	
	@Override
	public AbstractAuthenticationToken convert(Jwt jwttoken) {
		Collection<GrantedAuthority> authorities =Stream.concat(jwtAUthoritiesConvertor.convert(jwttoken).stream(), extractRoles(jwttoken).stream())
		.collect(Collectors.toSet());
		return new JwtAuthenticationToken(jwttoken,authorities);
	}
	
	public Collection<? extends GrantedAuthority> extractRoles(Jwt jwt){
		Set<String> roles = new HashSet<String>();
		
		Map<String, Object> realmAccess= jwt.getClaim("springkeycloak");
		
		if(realmAccess!=null && realmAccess.containsKey("roles")) {
			roles.addAll((Collection<? extends String>) realmAccess.get("roles"));
		}
		
		Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
		if(resourceAccess!=null && resourceAccess.containsKey(clientId)) {
			Map<String,Object> accesClient = (Map<String, Object>) resourceAccess.get(clientId);
			if(accesClient!=null && accesClient.containsKey("roles")) {
				roles.addAll((Collection<? extends String>) accesClient.get("roles"));
			}
		}
		
		
		return roles.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
		
		
	}
	

}
