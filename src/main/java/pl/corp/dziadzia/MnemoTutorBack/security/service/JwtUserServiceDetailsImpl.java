package pl.corp.dziadzia.MnemoTutorBack.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.corp.dziadzia.MnemoTutorBack.model.User;
import pl.corp.dziadzia.MnemoTutorBack.repo.UserRepository;
import pl.corp.dziadzia.MnemoTutorBack.security.JwtUserMapper;

@Service
public class JwtUserServiceDetailsImpl implements UserDetailsService{

    private final UserRepository userRepository;

    @Autowired
    public JwtUserServiceDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("Couldn't find user with username: " + username);

        return JwtUserMapper.createJwtUser(user);
    }
}
