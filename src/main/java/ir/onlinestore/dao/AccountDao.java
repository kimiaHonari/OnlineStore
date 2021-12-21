package ir.onlinestore.dao;


import ir.onlinestore.model.Account;
import ir.onlinestore.model.AccountDetails;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by kimia on 1/2/2017.
 */
@Component
@Qualifier("AccountDao")
public class AccountDao extends AbstarctDao<Account> {
    public Account FindById(String email){
        return  (Account) sessionFactory.getCurrentSession().get(Account.class,email);
    }
    public Boolean Register(Account account,AccountDetails details){
          account.setAccountDetails(details);
        sessionFactory.getCurrentSession().save(account);
        return true;
    }
    public double getCreditById(String id){
        Account account=FindById(id);
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(AccountDetails.class);
        criteria.add(Restrictions.eq("account",account));
        ArrayList<AccountDetails> list= (ArrayList<AccountDetails>) criteria.list();

        if(list.size()==0)
            return -2;
        else
            return list.get(0).getCredit();
    }
    public AccountDetails getDetails(Account account){
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(AccountDetails.class);
        criteria.add(Restrictions.eq("account",account));
        ArrayList<AccountDetails> list= (ArrayList<AccountDetails>) criteria.list();
        return list.get(0);
    }

}
