package com.graphui.beta.shared.types;

import com.graphui.beta.shared.types.interfaces.IPair;

public class Pair<T,S>  implements IPair<T,S>
{
	T first;
	S second;
	public Pair(T first, S second) {	
		this.setFirst(first);
		this.setSecond(second);
	}

	public final  T getFirst() {
		return this.first;
	}
	public final  void setFirst(T val) {
		this.first = val;
	}
	
	public final  S getSecond() {
		return this.second;
	}
	public final  void setSecond(S val) {
		this.second = val;
	}
}