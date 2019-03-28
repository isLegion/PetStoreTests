package test;

import org.aeonbits.owner.Config;

/**
 * Created by zsmirnova on 26/03/2019.
 */
@Config.Sources({ "file:src/main/resources/config.properties" })
public interface ApplicationConfig extends Config{

    @Config.Key("server.port")
    int port();

    @Config.Key("server.base")
    String base();

    @Config.Key("server.host")
    String host();
}
