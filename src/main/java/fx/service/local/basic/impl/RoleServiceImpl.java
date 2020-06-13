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


}
