package com.star.config;

import com.star.util.SnowFlake;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: zzStar
 * @Date: 03-19-2021 11:53
 */
@Component
public class ServerInitConfig implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        assert address != null;
        return "http://" + address.getHostAddress() + ":" + this.serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    @Bean("snowFlake")
    public SnowFlake snowFlake(){
        return new SnowFlake(219);
    }

}
