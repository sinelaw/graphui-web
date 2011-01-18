package com.graphui.beta.shared.types.interfaces;

public interface IPair<T, S> {
	T getFirst();
	void setFirst(T val);
	S getSecond();
	void setSecond(S val);
}
