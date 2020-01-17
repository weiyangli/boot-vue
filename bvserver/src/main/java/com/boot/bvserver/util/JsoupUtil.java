package com.boot.bvserver.util;

import com.boot.bvserver.bean.EsDemo;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Log4j
public class JsoupUtil {

    @Autowired
    private IdWorker idWorker;

    public static void goLol() {
        try {
            log.info("连接地址");
            Document document =  Jsoup.connect("http://lol.52pk.com/skinlist_3851_4_1.shtml").get();
            Element element =  document.body();
            System.out.println(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws  Exception{
        Document document= Jsoup.connect("https://lol.qq.com/act/a20200116Lunarnewyear/index.html").get();
        System.out.println(document.outerHtml());
    }

    /**
     * 爬取数据存入 es 测试使用
     *
     * @return
     * @throws Exception
     */
    public List<EsDemo> getPf() throws Exception {
        List<EsDemo> list = new ArrayList<>();
        for (int index = 25; index < 30; index++) {
            Document document= Jsoup.connect("http://lol.52pk.com/skinlist_3851_4_" + index + ".shtml").get();
            Element element = document.getElementsByClass("listBox").first();
            Elements elements = element.getElementsByTag("dl");
            for(Element element2:elements) {
                String pngUrl = element2.getElementsByTag("dt")
                        .first().getElementsByTag("a").first().getElementsByTag("img").attr("src");
                String name = element2.getElementsByTag("dd").first().getElementsByTag("a").first().text();
                String level = element2.getElementsByTag("dd").select("p").first().getElementsByTag("a").first().text();
                String price = element2.getElementsByTag("dd").select("p").get(1).text().replaceAll(" ","").split("购买")[0].replace("价格：","");
                list.add(new EsDemo().setId(idWorker.nextId()).setContent(name).setDate(new Date()).setPrice(price).setUrl(pngUrl).setLevel(level));
            }
        }
        return list;
    }
}
