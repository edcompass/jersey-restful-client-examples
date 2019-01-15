package eu.blky.helloworld.net.rest.jersey;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.Test; 

public class JerseyClientTest {

	@Test
	// 2. Jersey ClientBuilder
	public void test() {
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		 // http://localhost:8080/jersey-restful-client-example/rest/show-on-screen/employees/3
		WebTarget webTarget = client.target("http://localhost:8080/jersey-restful-client-example/rest/").path("show-on-screen").path("3");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
		 
		//Object emp = null;
		//Response response = invocationBuilder.post(Entity.entity(emp, MediaType.APPLICATION_XML));
		Response response = invocationBuilder.get();
		
		assertEquals(response.getStatus(),200);
		assertNotNull(response);
		
		
		assertTrue(  response.getAllowedMethods().size() == 0 );
	}

}
