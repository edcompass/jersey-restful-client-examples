package eu.blky.helloworld.net.rest.jersey;

import static org.junit.Assert.*;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder; 
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response; 

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.Test;

import eu.blky.helloworld.net.rest.jersey.remote.Employee;
import eu.blky.helloworld.net.rest.jersey.remote.Employees; 

public class JerseyClientTest {
	
    private static final int STATUS_OK = 200;

	/**
     * Rigourous Test :-)
     */
	@Test
    public void testApp()
    {
        for(URL url: ((URLClassLoader)ClassLoader.getSystemClassLoader()).getURLs()){
        	System.out.println(url.getFile());
        } 
         
    }

	@Test
	// 3. HTTP GET – Collection/List of Entities
	public void testList() {
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		 // http://localhost:8080/jersey-restful-client-example/rest/show-on-screen/employees
		WebTarget webTarget = client.target("http://localhost:8080/jersey-restful-client-example/rest/").path("show-on-screen").path("employees");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		 
		Employees employees = response.readEntity(Employees.class);
		List<Employee> listOfEmployees = employees.getEmployeeList();
		     
		System.out.println(response.getStatus());
		System.out.println(Arrays.toString( listOfEmployees.toArray(new Employee[listOfEmployees.size()]) ));
		
		
		for (Employee e: listOfEmployees) {
			System.out.println(e);
		}
	}

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
		
		assertEquals(STATUS_OK, response.getStatus());
		assertNotNull(response);
		
		
		assertTrue(  response.getAllowedMethods().size() == 0 );
	}

}
