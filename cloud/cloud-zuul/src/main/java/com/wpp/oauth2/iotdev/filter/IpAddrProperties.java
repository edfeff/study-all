package com.wpp.oauth2.iotdev.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * @author wangpp
 */
@ConfigurationProperties( "ip.filter" )
public class IpAddrProperties {
    private Set<String> black;
    private Set<String> white;

    public Set<String> getBlack() {
        return black;
    }

    public void setBlack(Set<String> black) {
        this.black = black;
    }

    public Set<String> getWhite() {
        return white;
    }

    public void setWhite(Set<String> white) {
        this.white = white;
    }
}
