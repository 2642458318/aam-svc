/*
package fx.util;

import com.alibaba.fastjson.JSON;
import jtpf.ins.svc.domain.ResponseCode;
import jtpf.ins.svc.exception.IdempotentException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

*/
/**
 * @Author: cjw
 * @Description: 接口幂等切面
 * @Date: 2020/4/1 9:26
 *//*

@Aspect
@Component
@Slf4j
public class IdempotentAspect {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private final String idgPrefix = "IDG-SVC:RESUB-TOKEN:";

    private final String orderPrefix = "INS-ORDER:RESUB-TOKEN:";

    @Around(value = "@annotation(jtpf.ins.svc.annotation.IdempotentAnnotation)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取请求头里面的幂等token
        String resubmitToken = request.getHeader("X-RESUB-TOKEN");
        if(StringUtils.isEmpty(resubmitToken)){
            throw new IdempotentException(ResponseCode.BadRequest.getCode(),"token check failed");
        }
        String orderKey = orderPrefix + resubmitToken;
        Boolean idempotentFlag = stringRedisTemplate.opsForValue().setIfAbsent(orderKey, "0");
        if(idempotentFlag){
            try {
                // 插入唯一token成功，进行处理
                String idgKey = idgPrefix + resubmitToken;
                Boolean hasKey = stringRedisTemplate.hasKey(idgKey);
                if(hasKey){
                    // idg存在token，继续业务处理
                    Object proceed = proceedingJoinPoint.proceed();
                    Map response = JSON.parseObject(proceed.toString(), Map.class);
                    String code = response.get("code").toString();
                    if(ResponseCode.Success.getCode().equals(code)){
                        // 方法执行成功，删除idg的token
                        stringRedisTemplate.delete(idgKey);
                        // 方法执行成功，删除唯一token
                        stringRedisTemplate.delete(orderKey);
                    }
                    return proceed;
                }else {
                    // idg不存在token
                    throw new IdempotentException(ResponseCode.BadRequest.getCode(),"token check failed");
                }
            }catch (Exception e){
                // 删除唯一token，抛出异常
                stringRedisTemplate.delete(orderKey);
                throw e;
            }
        }else {
            // 插入唯一token失败，进行处理
            throw new IdempotentException(ResponseCode.BadRequest.getCode(),"token check failed");
        }
    }
}
*/
