package novel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisAnontation {
	/**
	 * 枚举类型分别代表list，object,set,map
	 */
	public enum SerialType{LIST,OBJ,SET,MAP};
	/**
	 * 需要进行缓存的类
	 */
	public Class clazz();
	/**
	 * 需要声明序列化的类型
	 */
	SerialType serialType() default SerialType.OBJ;
}
