package springboot.dailyfitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springboot.dailyfitness.domain.FitnessUser;
import springboot.dailyfitness.repository.FitnessUserRepository;

@Service
public class FitnessUserDetailServiceImpl implements UserDetailsService {
    @Autowired 
    FitnessUserRepository fitRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
            FitnessUser curruser = fitRepository.findByUserName(username);
            UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), 
                AuthorityUtils.createAuthorityList(curruser.getRole()));
            return user;
    }   

}
