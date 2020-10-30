package fx.service.local.hr.impl;

import fx.dao.mapper.hr.HrMapper;
import fx.model.entity.hr.Hr;
import fx.service.local.hr.HrService;
import fx.util.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrSeriviceImpl implements HrService, UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    /**
     * 根据用户名加载hr对象
     *
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        //返回hr对象,不需要管密码对不对，security自己进行密码比较
        return hr;
    }

    @Override
    public List<Hr> getAllHrsExceptCurrentHr() {
        return hrMapper.getAllHrsExceptCurrentHr(HrUtils.getCurrentHr().getId());
    }
}
