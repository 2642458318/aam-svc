package fx.service.local.basic.impl;

import fx.dao.mapper.basic.RoleMapper;
import fx.model.entity.hr.Menu;
import fx.model.entity.hr.Role;
import fx.service.local.basic.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getAllRole() {
        return roleMapper.getAllRoles();
    }

    @Override
    public Integer addRole(Role role) {
        if (role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.addRole(role);
    }

    @Override
    public Integer deleteRolrById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }


}
