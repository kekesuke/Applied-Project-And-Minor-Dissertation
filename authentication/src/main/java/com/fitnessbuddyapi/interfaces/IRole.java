package com.fitnessbuddyapi.interfaces;

public interface IRoleService<T> extends IService<T> {

	T findByName(String name);


}
