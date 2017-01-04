package com.codrim.common.utils.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

/**
 * Enum工具类,需配合 {@link EnumWithKey} 进行使用
 * 工具类主要目的是为了处理值与枚举项的转换
 */
public class EnumUtils {

	/**
	 * 返回`key`对应值的枚举项,或者返回null
	 * @param type 枚举类型Class值
	 * @param key 将获取枚举项的枚举值
	 * @param <T> 枚举类型
	 * @param <Key> 值类型
	 * @return 枚举项或者空,只有当`key`为空时返回null,如果不为空,但未匹配到枚举项则会抛出异常
	 */
	public static <T extends Enum<T> & EnumWithKey<Key>, Key> T enumForKey(Class<T> type, Key key) {
		if (key == null) {
			return null;
		}

		final EnumSet<T> allEnums = EnumSet.allOf(type);
		final Iterator<T> iterator = allEnums.iterator();
		T result;
		while (iterator.hasNext()) {
			result = iterator.next();
			if (key.equals(result.getKey())) {
				return result;
			}
		}
		throw new IllegalArgumentException("Unknow enum key: '" + key + "'");
	}
	
	public static <T extends Enum<T> & EnumWithKey<Key>, Key> List<Key> enumForKeyList(Class<T> type) {
		List<Key> keyList = new ArrayList<>();
		for (T t : EnumSet.allOf(type)) {
			keyList.add(t.getKey());
		}
		return keyList;
	}
	
	public static <T extends Enum<T> & EnumWithKey<Key>, Key> Iterator<T> enumIterator(Class<T> type) {
		final EnumSet<T> allEnums = EnumSet.allOf(type);
		return allEnums.iterator();
	}

	public static <T extends Enum<T> & EnumWithKey<Key>, Key> boolean contaisKey(Class<T> type, Key key) {
		return enumForKeyList(type).contains(key);
	}
}
