package com.example.demo.appuser;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class AppUser implements UserDetails {

	private static final long serialVersionUID = 2095936543802646254L;

	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
	)

	private Long id = null;
	private String name = null;
	private String userName = null;
	private String email = null;
	private String password = null;

	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole = null;
	private Boolean bLocked = null;
	private Boolean bEnable = null;

	public AppUser(String name,
				   String userName,
				   String email,
				   String password,
				   AppUserRole appUserRole,
				   Boolean bLocked,
				   Boolean bEnable
				   ) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
		this.bLocked = bLocked;
		this.bEnable = bEnable;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	public boolean isAccountNonLocked() {
		return !bLocked;
	}

	public boolean isEnabled() {
		return bEnable;
	}
}