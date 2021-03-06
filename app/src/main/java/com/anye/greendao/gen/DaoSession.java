package com.anye.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hs.administrator.test.model.LoginBean;
import com.hs.administrator.test.model.TestGreeDaoBean;

import com.anye.greendao.gen.LoginBeanDao;
import com.anye.greendao.gen.TestGreeDaoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig loginBeanDaoConfig;
    private final DaoConfig testGreeDaoBeanDaoConfig;

    private final LoginBeanDao loginBeanDao;
    private final TestGreeDaoBeanDao testGreeDaoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        loginBeanDaoConfig = daoConfigMap.get(LoginBeanDao.class).clone();
        loginBeanDaoConfig.initIdentityScope(type);

        testGreeDaoBeanDaoConfig = daoConfigMap.get(TestGreeDaoBeanDao.class).clone();
        testGreeDaoBeanDaoConfig.initIdentityScope(type);

        loginBeanDao = new LoginBeanDao(loginBeanDaoConfig, this);
        testGreeDaoBeanDao = new TestGreeDaoBeanDao(testGreeDaoBeanDaoConfig, this);

        registerDao(LoginBean.class, loginBeanDao);
        registerDao(TestGreeDaoBean.class, testGreeDaoBeanDao);
    }
    
    public void clear() {
        loginBeanDaoConfig.getIdentityScope().clear();
        testGreeDaoBeanDaoConfig.getIdentityScope().clear();
    }

    public LoginBeanDao getLoginBeanDao() {
        return loginBeanDao;
    }

    public TestGreeDaoBeanDao getTestGreeDaoBeanDao() {
        return testGreeDaoBeanDao;
    }

}
