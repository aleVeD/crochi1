package cl.escalab.crochicat.service.impl;
import cl.escalab.crochicat.model.User;
import cl.escalab.crochicat.repo.UserRepoInterface;
import cl.escalab.crochicat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepoInterface userRepoInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepoInterface.findOneByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("usuario no corresponde", username));
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(rol->{
            roles.add(new SimpleGrantedAuthority(rol.getName()));
              });
        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);

        return ud;
    }
    @Override
    public User save(User obj) {
        return userRepoInterface.save(obj);
    }

    @Override
    public User update(User obj, UUID id) {
        return userRepoInterface.save(obj);
    }

    @Override
    public List<User> getAll() {
        return userRepoInterface.findAll();
    }

    @Override
    public User get(UUID id) {
        Optional<User> op = userRepoInterface.findById(id);
        return op.isPresent() ? op.get() : new User();
    }

    @Override
    public Boolean delete(UUID id) {
        userRepoInterface.deleteById(id);
        return true;
    }
}
