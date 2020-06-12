package fx.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 分析出当前用户是否具备访问该url的角色
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    /**
     *
     * @param authentication 当前用户的登录信息(用户的角色)
     * @param object 请求对象
     * @param configAttributes 访问该对象需要的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //先遍历需要的角色
        for (ConfigAttribute configAttribute: configAttributes){
            String needRole = configAttribute.getAttribute();
            //如果需要的角色是ROLE_LOGIN,那就登陆后就能访问
            if ("ROLE_LOGIN".equals(needRole)){
                //如果当前用户是匿名用户的实例，说明没登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登录，请登录");
                }else {
                    return;
                }
            }
            //获取当前用户登录的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            //这里判断之需要满足一个角色就能访问
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请练习管理员");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
