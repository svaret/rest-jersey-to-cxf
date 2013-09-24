package com.mytutorial.webservice.client;

import com.google.gson.Gson;
import com.mytutorial.webservice.Track;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Ignore;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import javax.ws.rs.core.UriBuilder;
import java.io.StringWriter;
import java.net.URI;

import static com.sun.jersey.api.client.Client.create;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static org.junit.Assert.assertEquals;

public class HelloUserWebServiceImplTest {
    Client client = create();
    WebResource webResource;
    Gson gson = new Gson();

    @Test
    public void postJsonTrackToUserService() {
        webResource = client.resource("http://localhost:8080/userservice/track");
        Track t = new Track();
        t.setSinger("Metallica");
        t.setTitle("Fade to Black");
        ClientResponse response = webResource.type(APPLICATION_JSON).post(ClientResponse.class, gson.toJson(t));

        assertEquals("POST Track updated : Track [title=Fade to Black, singer=Metallica]", response.getEntity(String.class));
    }

    @Test
    public void putJsonTrackToUserService() {
        webResource = client.resource("http://localhost:8080/userservice/track");
        String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";
        ClientResponse response = webResource.type(APPLICATION_JSON).put(ClientResponse.class, input);

        assertEquals("PUT Track created : Track [title=Fade To Black, singer=Metallica]", response.getEntity(String.class));
    }

    @Test
    public void putJsonUserToUserService() {
        webResource = client.resource("http://localhost:8080/userservice/user");
        Customer customer = new Customer("UbbaPut");
        ClientResponse response = webResource.type(APPLICATION_JSON).put(ClientResponse.class, gson.toJson(customer));

        assertEquals("PUT User created : UbbaPut", response.getEntity(String.class));
    }

    @Test
    public void postJsonUserToUserService() {
        webResource = client.resource("http://localhost:8080/userservice/user");
        Customer customer = new Customer("UbbaPost");
        ClientResponse response = webResource.type(APPLICATION_JSON).post(ClientResponse.class, gson.toJson(customer));

        assertEquals("POST User updated : UbbaPost", response.getEntity(String.class));
    }

    @Test
    public void putXmlUserToUserService() throws Exception {
        User user = new User("Urban");
        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        serializer.write(user, writer);
        String xml = writer.toString();
        WebResource webResource = client.resource("http://localhost:8080/userservice/user");
        ClientResponse response = webResource.type(APPLICATION_XML).put(ClientResponse.class, xml);

        assertEquals("PUT User created : Urban", response.getEntity(String.class));
    }

    @Test
    @Ignore
    public void postJsonTrackToMetallicaService() {
        WebResource webResource = client.resource("http://localhost:8080/RESTfulExample/rest/json/metallica/post");
        String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";
        ClientResponse response = webResource.type(APPLICATION_JSON).post(ClientResponse.class, input);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        System.out.println("Output from Server .... \n");
        String output = response.getEntity(String.class);
        System.out.println(output);
    }

    private void getXml(Client client) {
        WebResource webResource = client.resource("http://localhost:8080/userservice/user/Ubbe");
        ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
        System.out.println(output);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/de.vogella.jersey.todo").build();
    }
}
