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
 * @Description: 切面：查询前先查询redis，如果查询不到穿透到数据库，从数据库查询到数据后，保存到redis，然后下次查询可直接命中缓存
 */
@Component
@Aspect
public class TestRedisAspect {

    @Autowired
    @Qualifier("redisCache")
    private RedisCache redisCache;
    
    @Resource
    private  JedisPool jedisPool;
//    execution(* novel.service.impl.*.*(..))
    //设置切点：也可以在xml中配置
//    @Pointcut("execution(* novel.service.NovelService.*(..))")   
    @Pointcut("@annotation(novel.annotation.RedisAnontation)")
    public void myPointCut(){
        
    }
    

    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint joinPoint){
        //前置：到redis中查询缓存
        System.out.println("调用从redis中查询的方法...");
        
      //先获取目标方法参数
        String applId = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
           applId = String.valueOf(args[0]);
        }
        System.out.println("args:"+args[0]);
        
        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];
        System.out.println("target:"+target);
        System.out.println("className:"+className);
        
        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();
        System.out.println("methodName:"+methodName);
        
        //redis中key格式：    applId:方法名称
        String redisKey = applId + ":" + className + "." + methodName;
        
        System.out.println("redisKey"+redisKey);

//        args:novel.base.Pager@327e70
//        target:novel.service.impl.NovelServiceImpl@c85ee9
//        className:novel.service.impl.NovelServiceImpl
//        methodName:getGirlNovels
//        redisKeynovel.base.Pager@327e70:novel.service.impl.NovelServiceImpl.getGirlNovels
        
        
//        jedisPool.getResource().set("hello", "xxxxxxxxxxxx");
        
        //获取从redis中查询到的对象
//        Object objectFromRedis = redisCache.getDataFromRedis(redisKey,joinPoint.getTarget().getClass());
//        System.out.println(joinPoint.getTarget().getClass().getName());
//        System.out.println(joinPoint.);
        //如果查询到了
//        if(null != objectFromRedis){
//            System.out.println("从redis中查询到了数据...不需要查询数据库");
//            return objectFromRedis;
//        }
        
        
        
        RedisAnontation redisCache = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(RedisAnontation.class);
        System.out.println("methodName:"+methodName+"注解"+redisCache.clazz().getName()+":"+redisCache.serialType());
//        redisCache.clazz();redisCache.serialType();
        System.out.println("没有从redis中查到数据...");
        
        //没有查到，那么查询数据库
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            
            e.printStackTrace();
        }
        
        System.out.println("从数据库中查询的数据...");
        
        //后置：将数据库中查询的数据放到redis中
        System.out.println("调用把数据库查询的数据存储到redis中的方法...");
//        if(Novel.class.isInstance(object))
//        	System.out.println("jixxxxxxxxxxx");
//        else
//        	System.out.println("22222222222222");
        
//        redisCache.setDataToRedis(redisKey, (Serializable)object);
        
        //将查询到的数据返回
        return object;
        
    }
}