package novel.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import novel.annotation.RedisAnontation;
import novel.annotation.RedisAnontation.SerialType;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * @Description:Redis缓存
 */
public class RedisCache {

	@Resource
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	// 从redis缓存中查询，反序列化
	public Object getDataFromRedis(String redisKey, RedisAnontation anntation) {
		// 查询
		Jedis jedis = jedisPool.getResource();

		byte[] result = jedis.get(redisKey.getBytes());
		jedisPool.returnResource(jedis);
		Class clazz = anntation.clazz();
		if (null != result) {
			return deserilize(result, anntation);
		} else {
			return null;
		}

		// 查询到了，反序列化
		// return SerializeUtil.deserializationObject(result, clazz);

	}

	// 将数据库中查询到的数据放入redis
	public  void setDataToRedis(String redisKey, Object obj,RedisAnontation anontation) {
		// 序列化
		byte[] bytes = serilize(obj, anontation);
		// 存入redis
		Jedis jedis = jedisPool.getResource();
		String success = jedis.set(redisKey.getBytes(), bytes);
		jedis.expire(redisKey.getBytes(), 4500);
		jedisPool.returnResource(jedis);
		if ("OK".equals(success)) {
			System.out.println("数据成功保存到redis...");
		}
	}

	/**
	 * 使用kryo的序列化方式
	 * @param object
	 * @param anontation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object deserilize(byte[] object, RedisAnontation anontation) {
		if (anontation.serialType() == SerialType.LIST) {
			// 查询到了，反序列化
			return SerializeUtil
					.deserializationList(object, anontation.clazz());
		} else if (anontation.serialType() == SerialType.OBJ) {
			// 查询到了，反序列化
			return SerializeUtil.deserializationObject(object,
					anontation.clazz());
		} else if (anontation.serialType() == SerialType.SET) {
			// 查询到了，反序列化
			return SerializeUtil.deserializationSet(object, anontation.clazz());
		} else if (anontation.serialType() == SerialType.MAP) {
			// 查询到了，反序列化
			return SerializeUtil.deserializationMap(object, anontation.clazz());
		} else {
			throw new RuntimeException("无法进行反序列化");
		}
	}

	/**
	 * 对应的反序列化方式
	 * @param object
	 * @param anontation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private byte[] serilize(Object object, RedisAnontation anontation) {
		if (anontation.serialType() == SerialType.LIST) {
			byte[] list = SerializeUtil.serializationList((List) object,
					anontation.clazz());
			return list;
		} else if (anontation.serialType() == SerialType.OBJ) {
			return SerializeUtil.serializationObject((Serializable) object);
		} else if (anontation.serialType() == SerialType.SET) {
			return SerializeUtil.serializationSet((Set) object,
					anontation.clazz());
		} else if (anontation.serialType() == SerialType.MAP) {
			// 查询到了，反序列化
			return SerializeUtil.serializationMap((Map) object,
					anontation.clazz());
		} else {
			throw new RuntimeException("无法进行序列化");
		}

	}
}
