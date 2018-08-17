package com.javamentor.kidstarter.dao.Impl;

import com.javamentor.kidstarter.dao.interfaces.RoleDao;
import com.javamentor.kidstarter.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class RoleDaoImpl extends AbstractDao<Long,Role> implements RoleDao {

    public Role getByName (String name){
        return   entityManager.createQuery("SELECT r from  Role r WHERE r.name = :name", Role.class).setParameter("name",name).getSingleResult();
    }
}
