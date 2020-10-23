//package com.config;
//
//
//import com.domain.User;
//import com.domain.UserRole;
//import com.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Service("customUserDetailsService")
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UserService userService;
//
//	@Transactional
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //loadUserByEmail?
//		User user = userService.getUserByEmail(email);
//
//		if (user != null) {
//			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//			Set<UserRole> userRoles = user.getUserRolesList();
//			for (UserRole ur : userRoles) {
//				authorities.add(new SimpleGrantedAuthority("ROLE_" + ur.getType()));
//			}
//
//			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//					authorities);
//		}
//		throw new UsernameNotFoundException("Email not found");
//	}
//
//}
