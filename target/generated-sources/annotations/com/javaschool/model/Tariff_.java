package com.javaschool.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tariff.class)
public abstract class Tariff_ extends com.javaschool.model.AbstractModel_ {

	public static volatile SingularAttribute<Tariff, String> name;
	public static volatile SetAttribute<Tariff, Option> options;

	public static final String NAME = "name";
	public static final String OPTIONS = "options";

}

