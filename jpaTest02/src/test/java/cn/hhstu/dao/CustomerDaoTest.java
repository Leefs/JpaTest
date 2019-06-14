package cn.hhstu.dao;

import cn.hhstu.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")   //指定spring容器的配置信息
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /*
    * 根据id查询
    * */
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(1l);
        System.out.println(customer);
    }

    /**
     * save:保存或者更新
     *      根据传递的对象是否存在主键id.
     *      如果没有id主键属性，保存
     *      如果存在id主键属性，根据id查询数据库，更新数据
     * */
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("李林超博客3");
        customer.setCustLevel("超级管理员");
        customer.setCustIndustry("Python攻城狮");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId(4l);
        customer.setCustName("李林超博客4");
        customer.setCustLevel("管理员");
        customer.setCustIndustry("Python攻城狮");
        customerDao.save(customer);
    }

    /**
     * 删除操作
     * */
    @Test
    public void testDelete(){

        customerDao.delete(4l);
    }

    /**
     * 查询所有
     * */
    @Test
    public void testFindAll(){

        List<Customer> list = customerDao.findAll();
        for(Customer customer : list){
            System.out.println(customer);
        }
    }

    /**
     * 查询客户的数量
     *  count:统计总条数
     * */
    @Test
    public void testCount(){
        long count = customerDao.count();   //查询全部的客户数量
        System.out.println(count);
    }

    /**
     * 判断id为3的客户是否存在
     *      1.可以查询一下id为3的客户
     *        如果值为空，代表不存在，如果不为空，代表存在
     *      2.判断数据库中id为3的客户的数量
     *        如果数量为0，代表不存在，如果数量大于0，代表存在
     * */
    @Test
    public void testExists(){
         boolean exists = customerDao.exists(3l);
        System.out.println(exists);
    }

    /**
     * 根据id从数据库查询
     * @Transactional:保证getOne正常工作
     *  findOne:
     *      em.find()   :立即加载
     *  getOne:
     *      em.getReference :延迟加载
     *      返回的是一个客户的动态代理对象
     *      什么时候用，什么时候查询
     * */
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(3l);
        System.out.println(customer);
    }
}