package com.liweiyang.lwyFrame.util;

import com.liweiyang.lwyFrame.bean.Demo;
import com.liweiyang.lwyFrame.bean.Trader;
import com.liweiyang.lwyFrame.bean.Transaction;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Learn {
    /**
     * 复合 Lambda 表达式的有用方法
     */
    public static void jiji(String param) {
        LeYang<Demo, String> leYang = (demo2) -> "你好好";
        Predicate<String> demo = leYang.and((x) -> x.indexOf("l") > -1);
        boolean flag = demo.test(param);
        System.out.println(flag);
    }

    public static void main(String[] args) {
        List<Demo> stringList = Arrays.asList(Demo.builder().id(1L).age(14).name("里,斯").from("苗").build(),
                Demo.builder().id(2L).age(17).name("李,飒,积极").from("苗").build(),
                Demo.builder().id(3L).age(19).name("李,飒,咯").from("苗").build()
                );
        stringList.stream().filter(Demo::getMz).collect(Collectors.toList());
        stringList.stream().filter(Demo::getXs).limit(3).filter(Demo::getBool).count();
        Function<String [], Stream<String>>  mapper = new Function<String[], Stream<String>>() {
            @Override
            public Stream<String> apply(String[] strings) {
                return Arrays.stream(strings);
            }
        };
        List<String> strs =  stringList.stream().map(Demo::getName).map(str -> str.split(",")).flatMap(mapper).distinct().collect(Collectors.toList());
        System.out.println(Arrays.toString(strs.toArray()));
        /**
         * 1) 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4,
         * 5]，应该返回[1, 4, 9, 16, 25]
         */
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        nums.stream().map(x -> x * x).collect(Collectors.toList());
        /**
         * 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
         * 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
         * 表数对。
         */
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                list1.stream()
                        .flatMap(i -> list2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());
        Integer shh = list1.stream().findAny().orElse(1);
        Optional.ofNullable(1).get();
        Demo demo2 = new Demo();
        Optional.ofNullable(demo2.getName()).isPresent();
        int sum = numbers.stream().reduce(0, (a, b) -> a+ b);
        int sum2 = numbers.stream().reduce(0, Integer::max);

        // reduce 将经过接口函数计算的结果值，来回进行计算得到最后的结果
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> filters = transactions.stream().filter(x -> x.getYear() == 2011).sorted(Comparator.comparingInt(Transaction::getMoney).reversed()).collect(Collectors.toList());
        printArray(filters, "找出2011年发生的所有交易");
        // (2) 交易员都在哪些不同的城市工作过？
        List<String> cities = transactions.stream().map(Transaction::getTrader).map(Trader::getAddress).distinct().collect(Collectors.toList());
        printArray(cities, "交易员都在哪些不同的城市工作过");
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        transactions.stream().map(Transaction::getTrader).sorted(Comparator.comparing(Trader::getName));
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        transactions.stream().map(Transaction::getTrader).map(Trader::getName).sorted();
        // (5) 有没有交易员是在米兰工作的？
        List<Trader> traders = transactions.stream().map(Transaction::getTrader).filter(x -> x.getAddress().equals("Milan")).distinct().collect(Collectors.toList());
        printArray(traders,"有没有交易员是在米兰工作的");
        // (6) 打印生活在剑桥的交易员的所有交易额
        int sumMoney =  transactions.stream().filter(x -> x.getTrader().getAddress().equals("Cambridge")).map(Transaction::getMoney).reduce(0, Integer::sum);
        printArray(sumMoney, "打印生活在剑桥的交易员的所有交易额");
        // (7) 所有交易中，最高的交易额是多少？
        transactions.stream().max(Comparator.comparing(Transaction::getMoney));
        int max =  transactions.stream().mapToInt(Transaction::getMoney).max().getAsInt();
        printArray(max, "最高的交易额是多少");
        // (8)  找到交易额最小的交易。
        int min =  transactions.stream().map(Transaction::getMoney).reduce(Integer::min).get();
        printArray(min, "最小的交易");
        List<Transaction> minlist = transactions.stream().filter(x -> x.getMoney() == min).collect(Collectors.toList());
        printArray(minlist, "找到交易额最小的交易");
        minlist.stream().map(x -> {
            Map<String, String> map = new HashMap<>();
            map.put("data", x.getTrader().getAddress());
            return map;
        });
        try {
            FileChannel fc = new FileOutputStream("data.txt").getChannel();
        } catch (Exception e) {

        }
    }

    private static <T> void printArray(T data, String message) {
        if (data instanceof String) {
            System.out.println(message + data);
        } else if (data instanceof List) {
            System.out.println(message + Arrays.toString(((List)data).toArray()));
        } else if (data instanceof Integer) {
            System.out.println(message + "," + data);
        }
    }


    /**
     * 原始类型流特化, Java 8引入了三个原始类型特化流接口来解决这个问题：IntStream、DoubleStream和
     *  LongStream
     */
    public void streamToInt() {
        IntStream.of(1, 3, 4, 5).max();
    }
}
