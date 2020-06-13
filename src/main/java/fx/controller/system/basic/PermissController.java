package fx.controller.system.basic;

import fx.model.entity.hr.Menu;
import fx.model.entity.hr.Role;
import fx.model.param.resp.RespBean;
import fx.service.local.basic.RoleService;
import fx.service.local.hr.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }

    @GetMapping("/menu")
    public List<Menu> getAllMenu() {
        return menuService.getAllMenu();
    }
}
