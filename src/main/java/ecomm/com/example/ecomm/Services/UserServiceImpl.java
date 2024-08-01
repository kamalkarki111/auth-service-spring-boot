package ecomm.com.example.ecomm.Services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecomm.com.example.ecomm.Modals.User;
import ecomm.com.example.ecomm.Repos.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepository;

    @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findUserByUsername(username);

    return UserImpl.build(user);
  }
    
}
