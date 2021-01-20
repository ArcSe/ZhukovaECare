package com.javaschool.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contract.class)
public abstract class Contract_ extends com.javaschool.model.AbstractModel_ {

	public static volatile SingularAttribute<Contract, Integer> number;
	public static volatile SingularAttribute<Contract, Boolean> isLocked;
	public static volatile SetAttribute<Contract, Option> options;
	public static volatile SingularAttribute<Contract, Client> client;
	public static volatile SingularAttribute<Contract, Boolean> isLockedByAdmin;
	public static volatile SingularAttribute<Contract, Tariff> tariff;

	public static final String NUMBER = "number";
	public static final String IS_LOCKED = "isLocked";
	public static final String OPTIONS = "options";
	public static final String CLIENT = "client";
	public static final String IS_LOCKED_BY_ADMIN = "isLockedByAdmin";
	public static final String TARIFF = "tariff";

}

