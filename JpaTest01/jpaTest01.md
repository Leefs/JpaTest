Jpa练习01
====
### 一、搭建环境的过程
##### 1.创建maven工程导入坐标  
##### 2.需要配置jpa的核心配置文件  
位置：配置到类路径下的一个叫做META-INF的文件夹下  
命名：persistence.xml  
#### 3.编写客户的实体类 
#### 4.配置实体类和表，类中属性和表中字段的映射关系  
#### 5.保存客户到数据库中

### 二、JPA的基本操作
#### 案例：测试对客户的增删改查操作  
#### 创建客户数据库表： 
>/*创建客户表*/
     CREATE TABLE cst_customer (
       cust_id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
       cust_name varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
       cust_source varchar(32) DEFAULT NULL COMMENT '客户信息来源',
       cust_industry varchar(32) DEFAULT NULL COMMENT '客户所属行业',
       cust_level varchar(32) DEFAULT NULL COMMENT '客户级别',
       cust_address varchar(128) DEFAULT NULL COMMENT '客户联系地址',
       cust_phone varchar(64) DEFAULT NULL COMMENT '客户联系电话',
       PRIMARY KEY (`cust_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
>  
如果报错：this is incompatible with sql_mode=only_full_group_by则执行如下代码：    
>set sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES';
#### JPA操作的操作步骤：
##### 1.加载配置文件创建实体管理工厂  
Persistence:静态方法(根据持久化单元名称创建实体管理器工厂)  
  createEntityManagerFactory(持久化单元名称)  
作用：创建实体管理工厂  
##### 2.根据实体管理器工厂，创建实体管理器  
EntityManagerFactory：获取EntityManager对象  
方法：createEntityManager  
内部维护的很多的内容：  
  内部维护了数据库信息  
  维护了缓存信息  
  维护了所有的实体管理器对象  
  再创建EntityManagerFactory的过程中会根据配置创建数据库表  
EntityManagerFactory的创建过程比较浪费资源  
特点：线程安全的对象  
多个线程访问同一个EntityManagerFactory不会有线程安全问题  

解决EntityManagerFactory的创建过程浪费资源（耗时）的问题？  
思路：创建一个公共的EntityManagerFactory的对象 
  以静态代码块的形式创建EntityManagerFactory  

##### 3.创建事务对象，开启事务
  EntityManager对象：实体类管理器  
  beginTransaction:创建事务对象  
    persist:保存  
    merge:更新  
    remove:删除  
    find/getRefrence:根据id查询  
  
  Transaction对象：事务  
     begin:开启事务  
     commit:提交事务  
     rollback:回滚  
#### 4.增删改查操作
#### 5.提交事务
#### 6.释放资源  