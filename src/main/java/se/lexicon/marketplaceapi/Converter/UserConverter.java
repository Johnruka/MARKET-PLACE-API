package se.lexicon.marketplaceapi.Converter;

import org.springframework.stereotype.Component;
import se.lexicon.marketplaceapi.domain.dto.UserDTOForm;
import se.lexicon.marketplaceapi.domain.entity.User;

@Component
public interface UserConverter {

    UserConverter toUserDTO(User entity);

    User toEntity(UserDTOForm dto);
}
