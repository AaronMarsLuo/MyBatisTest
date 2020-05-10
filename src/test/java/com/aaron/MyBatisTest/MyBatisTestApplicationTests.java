package com.aaron.MyBatisTest;

import com.aaron.MyBatisTest.entity.Account;
import com.aaron.MyBatisTest.entity.Classes;
import com.aaron.MyBatisTest.entity.Customer;
import com.aaron.MyBatisTest.entity.Student;
import com.aaron.MyBatisTest.repository.AccountRepository;
import com.aaron.MyBatisTest.repository.ClassesReposity;
import com.aaron.MyBatisTest.repository.CustomerReposity;
import com.aaron.MyBatisTest.repository.StudentReposity;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MyBatisTestApplicationTests {

	@Test
	void t1() {
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		String statement="com.aaron.MyBatisTest.mapper.AccountMapper.save";
		Account account=Account.builder()
				.age(25)
				.username("aaron")
				.password("sdfs")
				.build();
		sqlSession.insert(statement,account);
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	void t2(){
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		AccountRepository accountRepository=sqlSession.getMapper(AccountRepository.class);
		List<Account> all = accountRepository.findByNameAndAge("aaron",24);
		for (Account account : all) {
			System.out.println(account);
		}
		sqlSession.close();

	}

	@Test
	void t3(){
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		StudentReposity studentReposity=sqlSession.getMapper(StudentReposity.class);
		Student student = studentReposity.findStudentById(1);
		System.out.println(student.toString());
		sqlSession.close();
	}

	@Test
	void t4(){
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		ClassesReposity classesReposity=sqlSession.getMapper(ClassesReposity.class);
		Classes classes = classesReposity.findClassById(1);
		System.out.println(classes);
		sqlSession.close();
	}

	@Test
	void t5(){
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		CustomerReposity customerReposity=sqlSession.getMapper(CustomerReposity.class);
		Customer goodsByCustomerId = customerReposity.getGoodsByCustomerId(1);
		System.out.println(goodsByCustomerId);
		sqlSession.close();
	}

	@Test
	void t6(){
		//测试延时加载
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		StudentReposity studentReposity=sqlSession.getMapper(StudentReposity.class);
		Student studentByIdLazy = studentReposity.findStudentByIdLazy(1);
		System.out.println(studentByIdLazy.getName());
	}

	@Test
	void t7(){
		//测试SqlSession级别缓存
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		AccountRepository accountRepository=sqlSession.getMapper(AccountRepository.class);
		List<Account> all = accountRepository.findByNameAndAge("aaron",24);
		for (Account account : all) {
			System.out.println(account);
		}
		List<Account> all1 = accountRepository.findByNameAndAge("aaron",24);
		for (Account account : all1) {
			System.out.println(account);
		}
		sqlSession.close();

	}
	@Test
	void t8(){
		//测试Mapper级别缓存
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);

		SqlSession sqlSession=sqlSessionFactory.openSession();
		AccountRepository accountRepository=sqlSession.getMapper(AccountRepository.class);
		List<Account> all = accountRepository.findByNameAndAge("aaron",24);
		for (Account account : all) {
			System.out.println(account);
		}
		sqlSession.commit();

		SqlSession sqlSession1=sqlSessionFactory.openSession();
		AccountRepository accountRepository1=sqlSession1.getMapper(AccountRepository.class);
		List<Account> all1 = accountRepository1.findByNameAndAge("aaron",24);
		for (Account account : all1) {
			System.out.println(account);
		}
		sqlSession.close();
		sqlSession1.close();
	}

	@Test
	//测试动态SQL
	void t9(){
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		AccountRepository accountRepository=sqlSession.getMapper(AccountRepository.class);
		Account account=Account.builder()
				.password("233")
				.age(23)
				.build();
		Account dynamically = accountRepository.findTrimly(account);
		System.out.println(dynamically);
		sqlSession.close();
	}
	@Test
		//测试动态SQL Foreach
	void t10(){
		InputStream inputStream=MyBatisTestApplicationTests.class.getClassLoader().getResourceAsStream("Config.xml");
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		SqlSession sqlSession=sqlSessionFactory.openSession();
		AccountRepository accountRepository=sqlSession.getMapper(AccountRepository.class);
		List<Integer> list=new ArrayList(){{
			add(1);add(2);
		}};
		List<Account> accounts=accountRepository.findByIds(list);
		for (Account account : accounts) {
			System.out.println(account);
		}
		sqlSession.close();
	}
}
