package es.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by kevin on 2017/10/21.
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfig {

    private String clusterName;

    private String clusterNodes;

    private int port;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Bean
    public TransportClient getESClient() throws UnknownHostException {

        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("192.168.145.131"),9300);

        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", clusterName).build();

        //创建client
        TransportClient client = new PreBuiltTransportClient(settings);

        client.addTransportAddress(node);

        return client;
    }
}


