package fx.controller.system.basic;

import fx.model.entity.hr.Menu;
import fx.model.entity.hr.Role;
import fx.model.param.resp.RespBean;
import fx.service.local.basic.RoleService;
import fx.service.local.hr.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询菜单资源
     * @return
     */
    @GetMapping("/menu")
    public List<Menu> getAllMenu() {
        return menuService.getAllMenu();
    }

    /**
     * 根据角色id查询资源id
     * @param rid
     * @return
     */
    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    /**
     * 控制角色的康访问资源
     * @param rid
     * @param mids
     * @return
     */
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        if (menuService.updateMenuRole(rid,mids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/role/")
    public RespBean addRole(@RequestBody Role role){
        if (roleService.addRole(role)==1){
            return RespBean.ok("角色添加成功");
        }
        return RespBean.error("角色添加失败");
    }


    /**
     * 删除角色
     * @param rid
     * @return
     */
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRolrById(@PathVariable Integer rid){
        if (roleService.deleteRolrById(rid)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
