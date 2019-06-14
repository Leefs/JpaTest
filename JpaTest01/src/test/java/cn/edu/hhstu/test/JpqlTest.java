package cn.edu.hhstu.test;

import cn.edu.hhstu.utils.JpaUtils;
import cn.edu.hhstu.domain.Customer;
import org.junit.Test;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 * */
public class JpqlTest {

    /**
     * 查询全部
     *  Jpql:from cn.edu.hhstu.domain.Customer
     *  sql:SELECT * FROM cst_customer
     * */
    @Test
    public void testFindAll(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql = "from Customer ";
        Query query = em.createQuery(jpql);//创建Query查询对象，query

        //发送查询，并封装结果集
        List list = query.getResultList();

        for(Object obj:list){
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 排序查询：倒序查询全部客户（根据id倒序查询）
     *      sql:SELECT * FROM cst_customer ORDER BY　ｃｕｓｔ＿ｉｄ　ＤＥＳＣ
     *      ｊｐｑｌ：from Customer order by custId desc　
     * 进行jpql查询
     *      1.创建query查询对象
     *      2.对象参数进行赋值
     *      3.查询，并得到返回结果
     * */
    @Test
    public void testOrders() {
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql = "from Customer order by custId desc";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象

        //发送查询，并封装结果集
        /**
         * getResultList:直接查询结果封装为list集合
         * */
        List list = query.getResultList();

        for (Object obj : list) {
            System.out.println(obj);
        }

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 使用jpql查询：统计客户的总数
     *      sql:SELECT COUNT(cust_id) FROM cst_customer
     *      jpql:select count(custId) from Customer
     * */
    @Test
    public void testCount(){

        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);

        //ii.对参数赋值
        //iii.发送查询，并封装结果

        /**
         * getSingleResult:查到唯一的结果集
         * */
        Object result = query.getSingleResult();

        System.out.println(result);

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 分页查询
     *  sql:select * from cst_customer limit 0,2
     *  jpql: from Customer
     * */
    @Test
    public void testPaged(){

        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);

        //ii.对参数进行赋值  分页参数
        //起始索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);

        List list = query.getResultList();

        for(Object obj : list){
            System.out.println(obj);
        }


        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 条件查询
     *      sql:select * from cst_customer where cust_name like ?
     *      jpql:from Customer where custName like ?
     * */
    @Test
    public void testCondition(){

        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql = "from Customer where custName like ?";
        Query query = em.createQuery(jpql);

        //ii.对参数进行赋值  --占位符
        //第一个参数：占位符的索引位置（从1开始），  第二个参数：取值
        query.setParameter(1,"李林超博客%");

        List list = query.getResultList();

        for(Object obj : list){
            System.out.println(obj);
        }


        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }
}

