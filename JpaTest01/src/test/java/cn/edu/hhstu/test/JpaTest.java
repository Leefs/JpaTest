package cn.edu.hhstu.test;

import cn.edu.hhstu.domain.Customer;
import cn.edu.hhstu.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     * Jpa的操作步骤：
     *      1.加载配置文件创建工厂（实体管理器工厂）对象
     *      2.通过实体类管理工厂获取实体管理器
     *      3.获取事物对象，开启事物
     *      4.完成增删改查操作
     *      5.提交事物（回滚事物）
     *      6.释放资源
     * */
    @Test
    public void testSave(){

        //1.加载配置文件创建工厂（实体管理器工厂）对象
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体类管理工厂获取实体管理器
        //EntityManager em = factory.createEntityManager();
        EntityManager em = JpaUtils.getEntityManager();
        // 3.获取事物对象，开启事物
        EntityTransaction tx = em.getTransaction(); //获取事物对象
        tx.begin();//开启事物
        //4.保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("李林超博客");
        customer.setCustAddress("杭州");
        customer.setCustIndustry("Java攻城狮");

        em.persist(customer);   //保存操作
        tx.commit();
        em.close();
       // factory.close();
    }

    /***
     *  根据id查询用户
     *  使用find方法查询：
     *      1.查询的对象就是当前客户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     */
    @Test
    public void testFind(){
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查  -- 根据id查询客户
        /**
         * find:根据id查询数据
         *      class:查询数据的结果需要包装的实体类类型的字节码
         *      id:查询的主键的取值
         * */
        Customer customer = entityManager.find(Customer.class,1l);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 根据id查询用户
     *  使用getReference方法：
     *      1.获取的对象是一个动态代理对象
     *      2.使用getReference方法不会立即发送sql语句查询数据库
     *          当调用查询结果对象的时候，才会发送查询的sql语句，什么时候用，什么时候发送sql语句查询数据库
     *
     *   延迟加载（懒加载）
     *      得到的是一个动态代理对象
     *      什么时候用，什么时候才会查询
     * */
    @Test
    public void testReference(){
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查  -- 根据id查询客户
        /**
         * find:根据id查询数据
         *      class:查询数据的结果需要包装的实体类类型的字节码
         *      id:查询的                主键的取值
         * */
        Customer customer = entityManager.getReference(Customer.class,1l);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 删除客户操作
     * */
    @Test
    public void testRemove(){
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查  -- 根据id查询客户
        /**
         * find:根据id查询数据
         *      class:查询数据的结果需要包装的实体类类型的字节码
         *      id:查询的                主键的取值
         * */
        Customer customer = entityManager.find(Customer.class,1l);
        //System.out.println(customer);
        entityManager.remove(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 更新客户操作
     * */
    @Test
    public void testUpdate(){
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查  --更新操作

        //查询客户
        Customer customer = entityManager.find(Customer.class,1l);
        //更新客户
        customer.setCustIndustry("C++攻城狮");
        entityManager.merge(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }
}
