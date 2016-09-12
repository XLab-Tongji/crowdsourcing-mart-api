package com.crazy;

import org.junit.runner.RunWith;
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
//    @Autowired
//    private UserMapper userMapper;
//
//    @Test
//    @Rollback
//    public void findByName() throws Exception {
//        userMapper.insert("AAA", 20);
//        User u = userMapper.findByName("AAA");
//        Assert.assertEquals(20, u.getAge().intValue());
//    }

}
