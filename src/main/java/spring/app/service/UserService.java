package spring.app.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.app.entity.Role;
import spring.app.entity.User;
import spring.app.repository.RoleRepository;
import spring.app.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userForm = userRepository.findByUsername(username);
        if (userForm == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return userForm;
    }

    public User findUserById(Long id){
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public boolean saveUser(User userForm){
        User userFromDb = userRepository.findByUsername(userForm.getUsername());
        if (userFromDb == null){
            return false;
        }
        userForm.setRoles(Collections.singleton(new Role(1l, "ROLE_USER")));
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userRepository.save(userForm);
        return true;
    }

    public boolean deleteUser(Long id){
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> userList(Long minId){
        return entityManager.createQuery("select u from User u where u.id > :paramId", User.class)
                .setParameter("paramId", minId).getResultList();
    }
}
