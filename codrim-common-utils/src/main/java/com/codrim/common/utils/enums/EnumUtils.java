package com.codrim.common.utils.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class EnumUtils {
	public static final <T extends Enum<T> & EnumWithKey> T enumForKey(Class<T> type, int key) {
		final EnumSet<T> allEnums = EnumSet.allOf(type);
		final Iterator<T> iterator = allEnums.iterator();
		T result;
		while (iterator.hasNext()) {
			result = iterator.next();
			if (result.getKey() == key) {
				return result;
			}
		}
		throw new IllegalArgumentException("Unknow enum key: '" + key + "'");
	}
	
	public static <T extends Enum<T> & EnumWithKey> List<Integer> enumForKeyList(Class<T> type) {
		List<Integer> keyList = new ArrayList<Integer>();
		final Iterator<T> iterator = EnumSet.allOf(type).iterator();
		while (iterator.hasNext()) {
			keyList.add(iterator.next().getKey());
		}
		return keyList;
	}
	
	public static final <T extends Enum<T> & EnumWithKey> Iterator<T> enumIterator(Class<T> type) {
		final EnumSet<T> allEnums = EnumSet.allOf(type);
		return allEnums.iterator();
	}

	public static <T extends Enum<T> & EnumWithKey> boolean contaisKey(Class<T> type, int key) {
		return enumForKeyList(type).contains(key);
	}
}
