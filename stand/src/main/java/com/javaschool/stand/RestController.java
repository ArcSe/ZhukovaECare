package com.javaschool.stand;


import com.javaschool.stand.model.Tariff;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.log4j.Logger;


import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class RestController {
    private final static Logger LOGGER = Logger.getLogger(Consumer.class.toString());


    public List<Tariff> getTariffs() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        WebResource webResource = client.resource("http://localhost:8080/hot");
        System.out.println(webResource.getProperties());


        List<Tariff> tariffs = webResource.type("application/json").get(new GenericType<List<Tariff>>(){
        });

        return tariffs;

    }
}