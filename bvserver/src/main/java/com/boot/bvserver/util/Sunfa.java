package com.boot.bvserver.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Sunfa {
    @Getter
    @Setter
    class Node {
        private  String id;
        private String nodeName;
        private String parentId;
        private List<Node> child;
    }

    /**
     * 生成一颗数，每个父节点下的子节点个数随机生成
     *
     * @param parentNum   父节点数
     * @param depentNum   深度
     */
    public List<Node> genTree(int parentNum, int depentNum) {
        List<Node> nodes = new ArrayList<>();
        // 生成父节点
        for (int i = 0; i < parentNum; i++) {
            Node node = new Node();
            node.setId(UUID.randomUUID().toString().substring(0, 32).replace("-", ""));
            node.setNodeName("第" + (i + 1) + "章");
            nodes.add(node);
        }
        // 生成子节点
        genChild(nodes, depentNum);
        return nodes;
    }
    public void genChild(List<Node> nodes, int depentNum) {
        Random random = new Random();
        for (Node node : nodes) {
            // 父节点下的子节点个数随机生成
            int num = random.nextInt(depentNum) + 1;
            List<Node> childNodes = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                Node node2 = new Node();
                node2.setId(UUID.randomUUID().toString().substring(0, 32).replace("-", ""));
                node2.setNodeName("第" + (i + 1) + "节");
                node2.setParentId(node.getId());
                childNodes.add(node2);
            }
            node.setChild(childNodes);
            // 子节点个数小于深度的一半递归添加子节点
            if (num < depentNum - (depentNum / 2)) {
                genChild(childNodes, depentNum);
            }
        }
    }
    public static void main(String[] args) {
        Sunfa sunfa = new Sunfa();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(sunfa.genTree(8, 10)));
        System.out.println(jsonArray);
    }

}
