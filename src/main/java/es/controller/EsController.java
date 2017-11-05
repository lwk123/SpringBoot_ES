package es.controller;


import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin on 2017/10/21.
 */

@RestController
public class EsController {
    @Autowired
    private TransportClient client;

    @RequestMapping(value = "/people")
    public String getBlogInfo() {
        //搜索数据
        GetResponse response = client.prepareGet("people", "man", "1").get();
        //输出结果1
        System.out.println(response.getSourceAsString());
        //关闭client
        client.close();
        return response.getSourceAsString();
    }
}
