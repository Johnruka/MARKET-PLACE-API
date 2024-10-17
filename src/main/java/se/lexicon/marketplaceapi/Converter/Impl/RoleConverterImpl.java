package se.lexicon.marketplaceapi.Converter.Impl;

import org.springframework.stereotype.Component;
import se.lexicon.marketplaceapi.Converter.RoleConverter;
import se.lexicon.marketplaceapi.domain.dto.RoleDTOForm;
import se.lexicon.marketplaceapi.domain.dto.RoleDTOView;
import se.lexicon.marketplaceapi.domain.entity.Role;

@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleDTOView toRoleDTO(Role entity) {
        return RoleDTOView.builder().id(entity.getId()).name(entity.getName()).build();
    }

    @Override
    public Role toEntity(RoleDTOForm dto) {
        return Role.builder().id(dto.getId()).name(dto.getName()).build();
    }
}
