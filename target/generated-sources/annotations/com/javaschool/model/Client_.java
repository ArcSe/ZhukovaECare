package com.javaschool.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ extends com.javaschool.model.AbstractModel_ {

	public static volatile SingularAttribute<Client, Date> birthday;
	public static volatile SingularAttribute<Client, String> address;
	public static volatile SingularAttribute<Client, Integer> passport;
	public static volatile SingularAttribute<Client, String> surname;
	public static volatile SetAttribute<Client, Role> roles;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SetAttribute<Client, Contract> contracts;
	public static volatile SingularAttribute<Client, String> email;

	public static final String BIRTHDAY = "birthday";
	public static final String ADDRESS = "address";
	public static final String PASSPORT = "passport";
	public static final String SURNAME = "surname";
	public static final String ROLES = "roles";
	public static final String NAME = "name";
	public static final String CONTRACTS = "contracts";
	public static final String EMAIL = "email";

}

