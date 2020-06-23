package com.liweiyang.lwyFrame.util;

import com.alibaba.fastjson.JSONObject;
import com.liweiyang.lwyFrame.bean.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


public class java8Test {

    private static final Logger broadcastAPILog = LoggerFactory.getLogger(java8Test.class);

    static int aa = 222;

    interface BaseFilter {
        boolean test(Demo demo);
    }

    public static void main2(String[] args) throws Exception {
        List<Demo> demos = Arrays.asList(Demo.builder().age(18).id(1L).name("lIWEIYANG").build(),
                Demo.builder().age(49).id(1L).name("零九年").build(),
                Demo.builder().age(31).id(1L).name("招式").build(),
                Demo.builder().age(14).id(1L).name("liyang").build(),
                Demo.builder().age(16).id(1L).name("张三").build());

        // 过滤大于 18 岁， 名字中有 l 的数据
        List<Demo> result = new ArrayList<>();
        BaseFilter filterAge = (demo) ->  demo.getAge() <= 18;
        BaseFilter filterName = (demo) -> demo.getName().indexOf("l") > -1;
        demos.forEach(demo -> {
            if (filterAge.test(demo) && filterName.test(demo)) {
                result.add(demo);
            }
        });
        result.sort(new Comparator<Demo>() {
            @Override
            public int compare(Demo o1, Demo o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        }.reversed());

//        Collections.sort(result, Comparator.comparing(Demo::getAge));
        System.out.println(JSONObject.toJSONString(result));

        //  函数式编程
        String oneLine =
                processFile((BufferedReader br) -> br.readLine());

        String twoLines =
                processFile((BufferedReader br) -> br.readLine() + br.readLine());
        Map<Integer, List<Demo>> maps =result.stream().collect(groupingBy(Demo::getAge));
        /**
         * lambda 表达式闭包范围类不能修改局部变量的值，局部变量值不能被其他线程共享，如果修改了涉及
         * 多线程变量值不一致的问题。
         *
         */
        int aa = 895;
        result.stream().map(x -> x.getAge() + aa);
        Predicate<String> p = s -> result.add(Demo.builder().age(49).id(1L).name("零九年").build());
// Consumer返回了一个void
        Consumer<String> b = s -> result.add(Demo.builder().age(49).id(1L).name("零九年").build());
        Inter o = () -> System.out.println("Tricky example");
        result.sort(Comparator.comparing(Demo::getAge));

        Demo demoBig = new Demo();
        Supplier<Demo> c1 = () -> new Demo();
        Demo demo = c1.get();

        Predicate<Demo> demoPredicate = Demo::getBool;
        demoPredicate.test(demo);

    }

    public interface Inter {
        void test();
    }

    public static String processFile(BufferedReaderProcessor p) throws
            IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }


    /**
     * 把 List<T> 根据 key 进行分组为 Map<K, List<T>>，key 为类 T 的方法引用返回的值，例如用户名，ID 等。
     * 使用案例，把 users 根据用户名和 ID 进行分组，相同用户名的用户作为 map 的 value (List<User>)，并且限制每个 list 大小为 2:
     * List<User> users = new LinkedList<>();
     * Map<String, List<User>> usersMap1 = groupAndLimitMapListValueSize(users, 2, User::getUsername);
     * Map<Long,   List<User>> usersMap2 = groupAndLimitMapListValueSize(users, 2, User::getId);
     *
     * @param list       要进行分组的 list
     * @param size       每组元素的个数
     * @param classifier 分组的 key 的函数接口实现
     * @param <K>        map 的 key 的类型
     * @param <T>        map 的 value 的类型
     * @return 返回分组后的 map
     */
    public static <K, T> Map<K, List<T>> groupAndLimitMapListValueSize(List<T> list, int size, Function<? super T, ? extends K> classifier) {
        Map<K, List<T>> map = list.stream().collect(groupingBy(classifier));

        map.forEach((key, valueList) -> {
            if (valueList.size() > size) {
                map.put(key, valueList.subList(0, size));
            }
        });
        return map;
    }

    public static void Comparatormain(String[] args) {
        List<String> strs = Arrays.asList("lol", "dsaad", "sdfslol", "dsddsaad");
        Map<String, List<String>> strMap =  strs.stream().collect(Collectors.groupingBy(x -> {
            if (x.indexOf("lol") > -1) {
                return "yx";
            } else if (x.indexOf("dsaad") > -1) {
                return "fsd";
            }
            return "default";
        }));
        System.out.println(JSONObject.toJSONString(strMap));
    }

}
