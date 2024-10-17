package se.lexicon.marketplaceapi.Converter;

import org.springframework.stereotype.Component;
import se.lexicon.marketplaceapi.domain.dto.RoleDTOForm;
import se.lexicon.marketplaceapi.domain.dto.RoleDTOView;
import se.lexicon.marketplaceapi.domain.entity.Role;

@Component
public interface RoleConverter {

    RoleDTOView toRoleDTO(Role entity);

    Role toEntity(RoleDTOForm dto);
}
