package gov.healthit.chpl.auth.user;

import gov.healthit.chpl.auth.authorization.Claim;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name="user")
public class AuthenticatedUser implements User {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="user_name")
	private String subjectName;
	
	@Column(name="password")
	private String password;
	
	@ManyToMany
	@JoinTable(
			name="user_claim",
			joinColumns={@JoinColumn(name="user_id")},
			inverseJoinColumns={@JoinColumn(name="claim_id")}
			)
	private Set<Claim> claims = new HashSet<Claim>();
	
	@Column(name="account_expired")
	private boolean accountExpired;
	
	@Column(name="account_locked")
	private boolean accountLocked;
	
	@Column(name="credentials_expired")
	private boolean credentialsExpired;
	
	@Column(name="account_enabled")
	private boolean accountEnabled;
	
	private boolean authenticated = true;
	
	public AuthenticatedUser(){};
	
	public AuthenticatedUser(String subjectName, Set<Claim> claims){
		this.subjectName = subjectName;
		this.claims = claims;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	/*
	public void setSubjectName(String subject) {
		this.subjectName = subject;
	}
	 */
	public Set<Claim> getClaims() {
		return this.claims;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

	public void addClaim(String claimValue){
		this.claims.add(new  Claim(claimValue));
	}
	
	public void addClaim(Claim claim){
		this.claims.add(claim);
	}

	public void removeClaim(String claimValue) {
		
		Claim remove = new Claim(claimValue);
		claims.remove(remove);
		
	}
	
	@Override
	public void removeClaim(Claim claim) {
		claims.remove(claim);
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.claims;
	}

	@Override
	public Object getCredentials() {
		return this.getPassword();
	}

	@Override
	public Object getDetails() {
		return this;
	}

	@Override
	public Object getPrincipal() {
		return this.getName();
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		this.authenticated = arg0;
	}

	@Override
	public String getName() {
		return subjectName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return subjectName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return accountEnabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}