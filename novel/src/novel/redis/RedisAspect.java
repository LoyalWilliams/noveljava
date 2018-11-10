package novel.redis;


import javax.annotation.Resource;

import novel.annotation.RedisAnontation;
import novel.util.RedisCache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;

/**
 * @Description: 切面：查询前先查询redis，如果查询不到穿透到数据库，从数据库查询到数据后，
 * 启动一个线程保存到redis，然后下次查询可直接命中缓存
 */
@Component
@Aspect
public class RedisAspect {

    @Autowired
    @Qualifier("redisCache")
    private RedisCache redisCache;
    
    @Resource
    private  JedisPool jedisPool;

    @Pointcut("@annotation(novel.annotation.RedisAnontation)")
    public void CacheCut(){
    }
    

    @Around("CacheCut()")
    public Object around(ProceedingJoinPoint joinPoint){
       //前置：到redis中查询缓存
      //先获取目标方法参数
        String applId = null;
        Object[] args = joinPoint.getArgs();
        if(args!=null && args.length > 0){
        	for (Object object : args) {
        		applId += String.valueOf(args[0]);
			}
        }
//        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];
        
//        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();
        
        //redis中key格式：    applId:方法名称
        String redisKey = applId + ":" + className + "." + methodName;
        
        System.out.println("redisKey:"+redisKey);
        
        RedisAnontation anontation = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(RedisAnontation.class);
        
        
        Object obj = redisCache.getDataFromRedis(redisKey, anontation);
        
        if(null!=obj){
        	return obj;
        }
        
        
//        System.out.println("没有从redis中查到数据...");
        
        //没有查到，那么查询数据库
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            
            e.printStackTrace();
        }
        
      //后置：将数据库中查询的数据放到redis中
        redisCache.setDataToRedis(redisKey, object, anontation);
        //将查询到的数据返回
        return object;
        
    }
}