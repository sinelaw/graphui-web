package com.graphui.beta.shared.types;

import java.util.ArrayList;

public class ListUtils {
	public static <T> ArrayList<T> create(T... items) {
		ArrayList<T> res = new ArrayList<T>();
		for (T item : items) {
			res.add(item);
		}
		return res;
	}
}
