package com.example;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;
    private MyResource mockedMyResource;
    
    @Mock
    Database mockedDatabase;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
        
        //mockedDatabase = Mockito.mock(Database.class);
        //Mockito.when(mockedMyResource.getIt("1")).thenReturn("BO_2");
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    /**
     * Retorna BO + _id
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("/myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    /**
     * Retorna BO + _id
     */
    @Test
    public void testGetItById() {
        String responseMsg = target.path("/myresource/{boId}").resolveTemplate("boId", "1").request().get(String.class);
        assertEquals("BO_1", responseMsg);
    }
}
