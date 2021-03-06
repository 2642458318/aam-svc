package fx.config;

import fx.model.entity.hr.Menu;
import fx.model.entity.hr.Role;
import fx.service.local.hr.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 提供权限控制，自定义拦截
 * 作用：根据用户传来的请求地址，分析出请求需要的角色
 * 匹配上了就把角色返回去，没有就返回固定字符串
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    //路径匹配符
    AntPathMatcher antPathMatcher=new AntPathMatcher();

    /**
     * collection 当前请求需要的角色
     * @param
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) obj).getRequestUrl();
        //获取所有菜单数据
        List<Menu> menus = menuService.getAllMenuWitchRole();
        //循环菜单
        for (Menu menu:menus){
            //根据用户的请求地址分析出用户需要哪些角色才能访问
            if (antPathMatcher.match(menu.getUrl(),requestUrl)){
                List<Role> roles = menu.getRoles();
                String []str=new String[roles.size()];
                for (int i=0;i<roles.size();i++){
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
        //凡是没匹配上的统一，登录之后就能访问  ROLB_LOGIN只是一个标记
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
