package fx.service.local.hr;

import fx.model.entity.hr.Menu;

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
}
