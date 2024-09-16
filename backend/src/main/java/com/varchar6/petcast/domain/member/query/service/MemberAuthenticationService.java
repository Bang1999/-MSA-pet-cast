package com.varchar6.petcast.domain.member.query.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberAuthenticationService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException;
}
