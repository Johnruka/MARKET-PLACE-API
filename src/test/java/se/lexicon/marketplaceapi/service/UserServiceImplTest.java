package se.lexicon.marketplaceapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.lexicon.marketplaceapi.Repository.RoleRepository;
import se.lexicon.marketplaceapi.Repository.UserRepository;
import se.lexicon.marketplaceapi.Service.Impl.UserServiceImpl;
import se.lexicon.marketplaceapi.domain.dto.RoleDTOForm;
import se.lexicon.marketplaceapi.domain.dto.UserDTOForm;
import se.lexicon.marketplaceapi.util.CustomPasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private CustomPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void register_ValidUserDTOForm_ShouldReturnUserDTOView() {

        Set<RoleDTOForm> roleDTOForms = new HashSet<>();
        roleDTOForms.add(RoleDTOForm.builder().id(1L).name("ROLE_USER").build());
        UserDTOForm userDTOForm = UserDTOForm.builder()
                .email("test@example.com")
                .password("password")
                .roles(roleDTOForms)
                .build();
    }

    @Test
    void register_DuplicateEmail_ShouldThrowDataDuplicateException() {
        // Arrange
        String duplicateEmail = "test@example.com";
        Set<RoleDTOForm> roleDTOForms = Collections.singleton(RoleDTOForm.builder().id(1L).name("ROLE_USER").build());
        UserDTOForm userDTOForm = UserDTOForm.builder()
                .email(duplicateEmail)
                .password("password")
                .roles(roleDTOForms)
                .build();
    }

    @Test
    void register_InvalidRole_ShouldThrowDataNotFoundException() {
        // Arrange
        String validEmail = "test@example.com";

        // Create a UserDTOForm with an invalid role (a role that does not exist in the system)
        Set<RoleDTOForm> roleDTOForms = Collections.singleton(RoleDTOForm.builder().id(2L).name("ROLE_INVALID").build());
        UserDTOForm userDTOForm = UserDTOForm.builder()
                .email(validEmail)
                .password("password")
                .roles(roleDTOForms)
                .build();

    }
}