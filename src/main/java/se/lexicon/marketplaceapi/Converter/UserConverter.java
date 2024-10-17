package se.lexicon.marketplaceapi.Converter;

import se.lexicon.marketplaceapi.domain.dto.UserDTOForm;
import se.lexicon.marketplaceapi.domain.entity.User;

public interface UserConverter {

    UserConverter toUserDTO(User entity);

    User toEntity(UserDTOForm dto);
}
