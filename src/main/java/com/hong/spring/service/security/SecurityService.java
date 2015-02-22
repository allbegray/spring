package com.hong.spring.service.security;

import com.hong.spring.domains.Member;

public interface SecurityService {

	String getCurrentMemberId();

	Member getCurrentMember();

}
