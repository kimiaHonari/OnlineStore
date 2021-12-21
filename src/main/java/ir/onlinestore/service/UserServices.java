package ir.onlinestore.service;

import ir.onlinestore.dao.AccountDao;
import ir.onlinestore.model.Account;
import ir.onlinestore.model.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by kimia on 12/28/2016.
 */
@Service("userservice")
@Transactional
public class UserServices {
    @Autowired
    AccountDao userDao;

    public AccountDao getUserDao() {
        return userDao;
    }

    public void setUserDao(AccountDao userDao) {
        this.userDao = userDao;
    }

    public Account loadUserByUsername(String username){
        return userDao.FindById(username);
    }

    public Account validate(String email,String password){
        System.out.println(email);
        System.out.println(password);
        Account account=userDao.FindById(email);
        if(account == null)
            return null;
        else if(account.getPassword().equals(password)) {

            return account;
        }
        else {
            System.out.println("password not match");
            return null;
        }
    }
    public Boolean Register(Account account,AccountDetails details){

            return  userDao.Register(account,details);
    }
    public double getCreditById(String id){
        return userDao.getCreditById(id);
    }

    public Account getById(String id){
        return userDao.FindById(id);
    }
    public AccountDetails getDetails(Account account){
        return userDao.getDetails(account);
    }
    public void update(Account account){
        userDao.update(account);
    }
}
