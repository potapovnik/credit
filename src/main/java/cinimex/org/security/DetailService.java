package cinimex.org.security;

import cinimex.org.entity.RoleEntity;
import cinimex.org.entity.UserEntity;
import cinimex.org.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class DetailService implements UserDetailsService {


    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " was not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        RoleEntity role = user.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                grantedAuthorities
        );
    }

}