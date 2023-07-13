package com.codegym.service.auth;

import com.codegym.mapper.MapperUtils;
import com.codegym.model.ERole;
import com.codegym.model.User;
import com.codegym.repository.UserRepository;
import com.codegym.service.auth.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final MapperUtils mapperUtils;

    public void register(RegisterRequest request){
        var user = mapperUtils.toUser(request);

        user.setRole(ERole.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean checkUsernameOrPhoneNumberOrEmail(RegisterRequest request, BindingResult result){
        boolean check = false;
//        if(userRepository.existsByUsernameIgnoreCase(request.getUsername())){
//            result.reject("username", null,
//                    "There is already an account registered with the same username");
//            check = true;
//        }
        if(userRepository.existsByEmailIgnoreCase(request.getEmail())){
            result.reject("email", null,
                    "There is already an account registered with the same email");
            check = true;
        }
//        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())){
//            result.reject("phoneNumber", null,
//                    "There is already an account registered with the same phone number");
//            check = true;
//        }
        return check;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCaseOrPhoneNumber(username,username,username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Exist") );
        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), role);
    }
}
