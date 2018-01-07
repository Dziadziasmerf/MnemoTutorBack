package pl.corp.dziadzia.MnemoTutorBack.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.corp.dziadzia.MnemoTutorBack.model.Authority;
import pl.corp.dziadzia.MnemoTutorBack.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserMapper {

    public static JwtUser createJwtUser(User user) {
        return new JwtUser( user.getId(),
                            user.getPassword(),
                            user.getUsername(),
                            user.isEnabled(),
                            mapAutorities(user.getAuthorities()));
    }

    private static List<GrantedAuthority> mapAutorities(List<Authority> authorities) {
        return authorities
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getType().name()))
                .collect(Collectors.toList());

    }
}
