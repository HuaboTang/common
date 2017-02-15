package com.codrim.common.utils.enums;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;
import java.util.stream.Collectors;

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
	public static <Key, T extends Enum<T> & EnumWithKey<Key>> T enumForKey(Class<T> type, Key key) {
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

	public static <Key, T extends Enum<T> & EnumWithKey<Key>> Optional<T> optionalEnumForKey(Class<T> type, Key key) {
		if (key == null) {
			return Optional.empty();
		}

		final EnumSet<T> allEnums = EnumSet.allOf(type);
		final Iterator<T> iterator = allEnums.iterator();
		T result;
		while (iterator.hasNext()) {
			result = iterator.next();
			if (key.equals(result.getKey())) {
				return Optional.of(result);
			}
		}
		return Optional.empty();
	}

	public static <T extends Enum<T> & EnumWithKey<Key>, Key> List<Key> keysOfEnum(Class<T> type) {
		return EnumSet.allOf(type).stream().map(EnumWithKey::getKey).collect(Collectors.toList());
	}
	
	public static <T extends Enum<T> & EnumWithKey<Key>, Key> Iterator<T> enumIterator(Class<T> type) {
		final EnumSet<T> allEnums = EnumSet.allOf(type);
		return allEnums.iterator();
	}

	public static <T extends Enum<T> & EnumWithKey<Key>, Key> boolean contaisKey(Class<T> type, Key key) {
		return keysOfEnum(type).contains(key);
	}

    /**
     * 将由逗号分隔的值拼接而成的字符串,转化为Enum `List`
     * @param <T> 枚举类型
     * @param string 逗号分隔的值拼接而成的字符串
     * @param type 枚举类型值
     * @return 枚举成员集合
     */
	public static <T extends Enum<T> & EnumWithKey<Integer>> List<T> enumsForKeys(String string, Class<T> type) {
		final String[] split = StringUtils.split(string, ",");
		if (ArrayUtils.isNotEmpty(split)) {
			return Arrays.stream(split).map(sType -> EnumUtils.optionalEnumForKey(type, NumberUtils.toInt(sType)).orElse(null))
					.collect(Collectors.toList());
		} else {
			return null;
		}
	}

}
