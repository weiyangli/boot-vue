package com.liweiyang.spark;

import com.alibaba.fastjson.JSONObject;
import com.liweiyang.spark.bean.Demo;
import com.liweiyang.spark.bean.User;
import com.liweiyang.spark.controller.DemoControler;
import com.liweiyang.spark.util.FreemarkerUtil;
import com.liweiyang.spark.zk.BaseLockHandler;
import com.liweiyang.spark.zk.ShardReentrantLockComponent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class SparkApplicationTests {


    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private ShardReentrantLockComponent lockComponent;

    @Autowired
    private FreemarkerUtil freemarkerUtil;


    /**
     * 用来实现具体逻辑，对该计数器加1
     */
    private int count;

    @Test
    public void contextLoads() throws Exception {
        Stat stat = curatorFramework.checkExists().forPath("/demo");
        System.out.println(stat);
        String forPath = curatorFramework.create().creatingParentsIfNeeded().forPath("/demo", "create init !".getBytes());
        // String forPath = client.create().forPath(path);
        System.out.println(forPath);
    }


    /**
     * 不加锁实现多个线程同时对 count 执行 ++ 操作
     * 会出现数据不一致现象
     * @throws Exception
     */
    @Test
    public void noAcquireLockTest() throws Exception {
        //初始化一个拥有 100 个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        //使用 CountDownLatch 实现线程的协调
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i = 0; i < 100; i++) {
            final int index = i;
            //提交线程
            executorService.submit(() -> {
                //name 表示该线程的名称
                String name = "client" + (index + 1);
                //执行 count++
                count++;
                try {
                    //睡眠 50ms ,使测试结果更明显
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //打印各个线程执行结果
                System.out.println(name + "    执行业务方法，对 count 执行 ++ 操作后 count 的值为 : " + count);
                //调用countDown方法，表示该线程执行完毕
                countDownLatch.countDown();
            });
        }
        //使该方法阻塞住，不然看不到效果
        countDownLatch.await();
    }


    /**
     * 使用 zookeeper 加锁实现多个线程同时对 count 执行 ++ 操作
     * @throws Exception
     */
    @Test
    public void acquireLockTest() throws Exception {
        //要加锁节点的路径
        String path = "/path/test";
        //初始化一个拥有 100 个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        //使用 CountDownLatch 实现线程的协调
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for(int i = 0; i < 100; i++) {
            final int index = i;
            //提交线程
            executorService.submit(() -> {
                //name 表示该线程的名称
                String name = "client" + (index + 1);
                //result 获取执行完业务逻辑后返回值
                String result = null;
                while (result == null) {
                    //result 为 null 表示没有的锁，会继续执行while循环去竞争获取锁
                    result = lockComponent.acquireLock(new BaseLockHandler(path) {

                        //执行具体的业务逻辑
                        @Override
                        public String handler() {
                            //执行 count++
                            count++;
                            try {
                                //睡眠 50ms ,使测试结果更明显
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //打印各个线程执行结果
                            System.out.println(name + "    执行业务方法，对count++ : " + count);

                            //执行成功后不要返回null，如果返回null，会继续执行while去竞争获取锁
                            return this.getPath();
                        }
                    });
                }
                //调用countDown方法，表示该线程执行完毕
                countDownLatch.countDown();
            });
        }
        //使该方法阻塞住，不然看不到效果
        countDownLatch.await();
    }

    @Test
    public void genTemp() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enabled", "true");
        jsonObject.put("orderNum", "No458415151");
        jsonObject.put("phone", "15510557605");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = User.builder().id(i + 1L).nickname("赵三").username("tony").build();
            users.add(user);
        }
        Demo demo = Demo.builder().id(4554544L).name("李明").otherInfo(jsonObject).userList(users).build();
        Demo demo2 = Demo.builder().id(89757L).name("找你你").otherInfo(jsonObject).userList(users).build();
        List<Demo> demoList = new ArrayList<>();
        demoList.add(demo);
        demoList.add(demo2);
        freemarkerUtil.generateTemplate("index.ftl", "index.html", demoList);
    }


}
