package ecomm.com.example.ecomm.Services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ecomm.com.example.ecomm.Modals.User;

public class UserImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
  private String username;

  private String email;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserImpl(String email, String username, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public static UserImpl build(User user) {

    return new UserImpl(
        user.getEmail(), 
        user.getUsername(),
        user.getPassword());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
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
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
      UserImpl user = (UserImpl) o;
    return Objects.equals(username, user.username);
  }
}
