package org.codrim.common.utils.enums;

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
	
	public static <T extends Enum<T> & EnumWithKey> List<Integer> enumForValueList(Class<T> type) {
		List<Integer> valueList = new ArrayList<Integer>();
		final Iterator<T> iterator = EnumSet.allOf(type).iterator();
		while (iterator.hasNext()) {
			valueList.add(iterator.next().getKey());
		}
		return valueList;
	}
	
	public static final <T extends Enum<T> & EnumWithKey> Iterator<T> enumIterator(Class<T> type) {
		final EnumSet<T> allEnums = EnumSet.allOf(type);
		return allEnums.iterator();
	}
}
