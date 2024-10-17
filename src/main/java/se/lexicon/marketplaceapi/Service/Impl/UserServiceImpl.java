package se.lexicon.marketplaceapi.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.marketplaceapi.Exception.DataDuplicateException;
import se.lexicon.marketplaceapi.Exception.DataNotFoundException;
import se.lexicon.marketplaceapi.Repository.RoleRepository;
import se.lexicon.marketplaceapi.Repository.UserRepository;
import se.lexicon.marketplaceapi.Service.UserService;
import se.lexicon.marketplaceapi.domain.dto.RoleDTOView;
import se.lexicon.marketplaceapi.domain.dto.UserDTOForm;
import se.lexicon.marketplaceapi.domain.dto.UserDTOView;
import se.lexicon.marketplaceapi.domain.entity.Role;
import se.lexicon.marketplaceapi.domain.entity.User;
import se.lexicon.marketplaceapi.util.CustomPasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTOView register(UserDTOForm userDTOForm) {

        // Checking parameters
        if (userDTOForm == null) throw new IllegalArgumentException("User form cannot be null");

        // Checking if email exists in the DB
        boolean doesExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if (doesExistEmail) throw new DataDuplicateException("Email already exists");

        //Validate Roles in repository and retrieve them
        Set<Role> roleList = userDTOForm.getRoles()
                .stream()
                .map(
                        roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                                .orElseThrow(() -> new DataNotFoundException("Role is not valid")))
                .collect(Collectors.toSet());

        // Convert UserDTOForm to User entity
        // Hash the password
        User user = User.builder()
                .email(userDTOForm.getEmail())
                .password(passwordEncoder.encode(userDTOForm.getPassword()))
                .roles(roleList)
                .build();
        // Save User to the DB
        User savedUser = userRepository.save(user);

        // Convert the repository result to UserDTOView
        // return the result

        Set<RoleDTOView> roleDTOViews = savedUser.getRoles()
                .stream()
                .map(
                        role -> RoleDTOView.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                .collect(Collectors.toSet());

        return UserDTOView.builder()
                .email(savedUser.getEmail())
                .roles(roleDTOViews)
                .build();

    }

    @Override
    public UserDTOView getByEmail(String email) {
        User user = userRepository.findById(email).orElseThrow(() -> new DataNotFoundException("Email does not exist"));
        Set<RoleDTOView> roleDTOViews = user.getRoles()
                .stream()
                .map(
                        role -> RoleDTOView.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                .collect(Collectors.toSet());

        return UserDTOView.builder()
                .email(user.getEmail())
                .roles(roleDTOViews)
                .build();
    }

    @Override
    public void disableByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateExpiredByEmail(email, true);
    }

    private void isEmailTaken(String email) {
    }

    @Override
    public void enableByEmail(String email) {
        if (!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email does not exist");
    }
}

