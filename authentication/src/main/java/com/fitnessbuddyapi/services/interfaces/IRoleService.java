package com.fitnessbuddyapi.services.interfaces;

import java.util.Optional;

import com.fitnessbuddyapi.models.Role;

public interface IRoleService<T> extends IService<T> {

	T findByName(String name);


}
