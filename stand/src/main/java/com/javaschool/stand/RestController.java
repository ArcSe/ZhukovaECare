package com.javaschool.stand;


import com.javaschool.stand.model.Tariff;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;


import javax.ejb.Singleton;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public class RestController {
    private final static Logger LOGGER = Logger.getLogger(Consumer.class.toString());

    public static List<Tariff> getTariffs() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        LOGGER.fine("HEEEEEEEEREEEEEEEEEEEEEEEEEEEEE");

        WebResource webResource = client.resource("http://localhost:8080/managers/tariffs/hot");
        System.out.println(webResource);


        List<Tariff> tariffs = webResource.type("application/json").get(new GenericType<List<Tariff>>(){
        });
        LOGGER.config(tariffs.toString());

        return tariffs;

    }
}