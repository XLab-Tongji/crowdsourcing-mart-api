package com.crazy;

import com.crazy.mapper.AccountMapper;
import com.crazy.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SHIKUN on 2016/9/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class ApplicationTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountMapper accountMapper;
//
//    @Test
//    @Rollback
//    public void findByName() throws Exception {
//        userMapper.insert("AAA", 20);
//        User u = userMapper.findByName("AAA");
//        Assert.assertEquals(20, u.getAge().intValue());
//    }

    @Test
    public void finduser() throws Exception {
        System.out.println(accountMapper.getNameAndPassword());
    }

}
