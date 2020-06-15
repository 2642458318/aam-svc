package fx.service.local.basic;

import fx.model.entity.hr.Menu;
import fx.model.entity.hr.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    Integer addRole(Role role);

    Integer deleteRolrById(Integer rid);
}
