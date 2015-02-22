package com.hong.spring.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hong.spring.domains.Member;

@Service
public class DefaultSecurityService implements SecurityService {

	@Override
	public Member getCurrentMember() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null)
			return null;

		Object princial = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (princial instanceof UserDetails) {
			return ((SpringUserDetail) princial).getMember();
		} else {
			return new NullMember();
		}
	}

	@Override
	public String getCurrentMemberId() {
		return getCurrentMember().getLoginId();
	}

	static public class NullMember extends Member {
		
		public NullMember() {
			setId(-1L);
			setUserName("손님");
		}

		@Override
		public boolean isAnonymous() {
			return true;
		}
		
	}

}
