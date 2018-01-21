package com.lab409.crowdingsourcing;

import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrowdingSourcingApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private AccountRepository accountRepository;
	@Test
	public void testjpa() throws Exception{
        accountRepository.save(new Account());
    }

}
