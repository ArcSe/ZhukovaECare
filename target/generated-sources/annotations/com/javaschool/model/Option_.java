package com.javaschool.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Option.class)
public abstract class Option_ extends com.javaschool.model.AbstractModel_ {

	public static volatile SingularAttribute<Option, Integer> serviceCost;
	public static volatile SingularAttribute<Option, Integer> price;
	public static volatile SingularAttribute<Option, String> name;

	public static final String SERVICE_COST = "serviceCost";
	public static final String PRICE = "price";
	public static final String NAME = "name";

}

