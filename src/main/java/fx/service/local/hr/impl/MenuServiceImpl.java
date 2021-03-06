package fx.service.local.hr.impl;

import fx.dao.mapper.hr.MenuMapper;
import fx.model.entity.hr.Hr;
import fx.model.entity.hr.Menu;
import fx.model.entity.hr.Role;
import fx.service.local.hr.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取所有菜单数据
     *
     * @return
     */
    @Override
    public List<Menu> getAllMenuWitchRole() {
        return menuMapper.getAllMenuWitchRole();
    }

    /**
     * 登录的用户信息保存在SecurityContextHolder里，调用方法，获取用户id
     * 根据id查菜单列表
     * getPrincipal 当前登录的用户对象
     * 强转程hr对象，然后获取用户id
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

    }

    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }

    @Override
    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional
    @Override
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuMapper.deleteByRid(rid);
        if (mids==null || mids.length==0){
            return true;
        }
        Integer result = menuMapper.insertRecord(rid, mids);
        return result==mids.length;
    }

}
