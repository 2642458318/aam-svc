package fx.service.local.hr;

import fx.model.entity.hr.Menu;
import fx.model.entity.hr.Role;

import java.util.List;

public interface MenuService {
    /**
     * 获取所有菜单数据
     *
     * @return
     */
    List<Menu> getAllMenuWitchRole();

    /**
     * 根据id查菜单列表
     * @return
     */
    List<Menu> getMenusByHrId();

    List<Menu> getAllMenu();

    List<Integer> getMidsByRid(Integer rid);

    boolean updateMenuRole(Integer rid, Integer[] mids);

}
