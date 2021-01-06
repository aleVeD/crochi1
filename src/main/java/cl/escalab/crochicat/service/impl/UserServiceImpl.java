package cl.escalab.crochicat.service.impl;
import cl.escalab.crochicat.model.User;
import cl.escalab.crochicat.repo.UserRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepoInterface userRepoInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepoInterface.findOneByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("usuario no corresponde", username));
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(rol->{
            roles.add(new SimpleGrantedAuthority(rol.getName()));
              });
        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), roles);

        return ud;
    }
}
