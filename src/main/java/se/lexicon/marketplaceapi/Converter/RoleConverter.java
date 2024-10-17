package se.lexicon.marketplaceapi.Converter;

import se.lexicon.marketplaceapi.domain.dto.RoleDTOForm;
import se.lexicon.marketplaceapi.domain.dto.RoleDTOView;
import se.lexicon.marketplaceapi.domain.entity.Role;

public interface RoleConverter {

    RoleDTOView toRoleDTO(Role entity);

    Role toEntity(RoleDTOForm dto);
}
