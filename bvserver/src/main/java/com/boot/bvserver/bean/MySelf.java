package com.boot.bvserver.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MySelf {

    private Long Id = 19940614L;
    private final static String name = "lwy";
    private static List<String> leanGoodThings;
    private static List<String> throwBadThings;
    private final static String target = "提升能力 2021 年给自己和未来一个交代！";
    private final static String flag = "坚持改变做最好的自己，不随波逐流！";

    static {
        /*
        * 需要改变的事
        * */
        leanGoodThings = new ArrayList<>();
        leanGoodThings.add("工作日正常起床，假期早上 8 点起。");
        leanGoodThings.add("每天早起先洗漱，然后做早餐,吃早餐。8点-9点半");
        leanGoodThings.add("学习编程技术提高自己的能力。 9点半到12点");
        leanGoodThings.add("做午饭,午休。12点到两点。");
        leanGoodThings.add("学习大数据相关知识。（后期考虑转大数据）两点到四点半。");
        leanGoodThings.add("做晚饭吃饭。四点半到五点半。");
        leanGoodThings.add("出门溜达锻炼。五点半到七点。");
        leanGoodThings.add("看技术或其他类正向书籍。七点到九点。");
        leanGoodThings.add("可以适当玩会手机。九点到十点半。");
        leanGoodThings.add("睡觉。十点半到八点。");

        /*
        * 需要戒掉的事
        * */
        throwBadThings = new ArrayList<>();
        throwBadThings.add("睡懒觉");
        throwBadThings.add("熬夜看直播");
        throwBadThings.add("撸啊撸");
    }
}
