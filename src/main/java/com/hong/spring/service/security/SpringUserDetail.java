package com.hong.spring.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hong.spring.domains.Member;

public class SpringUserDetail implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Member member;
	private List<GrantedAuthority> authorities;

	public SpringUserDetail(Member member) {
		this.member = member;
		this.authorities = new ArrayList<GrantedAuthority>();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// if (member.getStatus() == MemberStatus.OUT)
		// return false;
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// return (member.getStatus() == MemberStatus.JOIN);
		return true;
	}

	public Member getMember() {
		return member;
	}

}
