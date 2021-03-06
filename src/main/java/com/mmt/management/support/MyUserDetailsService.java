package com.mmt.management.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mmt.management.entity.AdminUser;
import com.mmt.management.entity.Menu;
import com.mmt.management.entity.Role;
import com.mmt.management.service.AdminUserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private AdminUserService adminService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("登录用户为："+ username);
		AdminUser sysUser = adminService.checkUserByName(username);
		if(null == sysUser) {
			throw new UsernameNotFoundException(username);
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : sysUser.getRoleList()) {
			for (Menu permission : role.getPermissionList()) {
				authorities.add(new SimpleGrantedAuthority(permission.getCode()));
			}
		}
		System.out.println("登录用户为："+ sysUser.getName());
		return new User(sysUser.getName(), sysUser.getPassword(), authorities);
	}
}
