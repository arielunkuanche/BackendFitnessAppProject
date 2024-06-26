// package springboot.dailyfitness.service;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Collection;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

// import springboot.dailyfitness.domain.Role;
// import springboot.dailyfitness.domain.User;
// import springboot.dailyfitness.repository.UserRepository;
// import springboot.dailyfitness.web.dto.UserRegistrationDto;

// @Component
// public class UserServiceImpl implements UserService{

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private BCryptPasswordEncoder passwordEncoder;
    
//     // // public UserServiceImpl(UserRepository userRepository) {
//     //     super();
//     // //     this.userRepository = userRepository;
//     // // }



//     @Override
//     public User save(UserRegistrationDto userRegistrationDto) {
//         User user = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(), 
//                             userRegistrationDto.getEmail(), passwordEncoder.encode(userRegistrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
//         return userRepository.save(user);
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByEmail(username);
//         if(user == null)
//             throw new UsernameNotFoundException(("Invaid username or password!"));

//         return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasssword(), mapRolesToAuthorities(user.getRoles()));
//     }

//     private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//         return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//     }

// }
