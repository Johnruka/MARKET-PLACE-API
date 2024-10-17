package se.lexicon.marketplaceapi.Service;

import se.lexicon.marketplaceapi.domain.dto.RoleDTOView;

import java.util.List;

public interface RoleService {

    List<RoleDTOView> getAll();
}
