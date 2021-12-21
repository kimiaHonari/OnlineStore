package ir.onlinestore.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by kimia on 12/28/2016.
 */
@Component
public abstract class AbstarctDao<T> implements Serializable {
    @Autowired
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void update(T entity){sessionFactory.getCurrentSession().update(entity);}
    public void delete(T entity){sessionFactory.getCurrentSession().delete(entity);}
    public T save(T entity){sessionFactory.getCurrentSession().persist(entity); return entity;}

}
