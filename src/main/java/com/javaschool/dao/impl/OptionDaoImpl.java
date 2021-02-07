package com.javaschool.dao.impl;

import com.javaschool.dao.OptionDao;
import com.javaschool.model.Client;
import com.javaschool.model.Option;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OptionDaoImpl extends AbstractJpaDaoImpl<Option> implements OptionDao {


    public List<Option> getAllNotDeleted(){
        return em.createQuery("select c from   Option  c where c.deleted=false ").getResultList();
    }

    @Override
    public Option getByName(String name) {
        TypedQuery<Option> query = em.createQuery("Select u from Option u where  u.name=?1", Option.class);
        query.setParameter(1, name);
        Option foundOption=null;
        try {
            foundOption = query.getSingleResult();
        }
        catch (NoResultException e){
            //todo!!!Change to log!!!
            System.out.println(e.getCause());
        }
        return foundOption;
    }

    @Override
    public void delete(long id) {
        Option option = getById(id);
        /*
        TypedQuery<Option> query = em.createQuery("Select o from Option o where or"
                + " o.mandatoryOptions=?1", Option.class);
        query.setParameter(1, id);
        if(!Objects.isNull(query.getResultList())) {
            for (Option o :query.getResultList()) {
                o.setBannedOptions(o.getBannedOptions().stream().filter(option1 -> option1.getId()==id).collect(Collectors.toSet()));
                o.setMandatoryOptions(o.getMandatoryOptions().stream().filter(option1 -> option1.getId()==id).collect(Collectors.toSet()));
            }
            System.out.println(" man or ban options");
        }

         */
        if(option.getContracts().isEmpty() && option.getTariff().isEmpty()){
            super.delete(id);
            System.out.println("option without link ");
        }
        else {
            em.createQuery("update Option o set o.deleted=true where o.id=?1").setParameter(1, id).executeUpdate();
            System.out.println("option with link ");
        }
    }
}

