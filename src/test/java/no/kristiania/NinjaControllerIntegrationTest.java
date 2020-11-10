package no.kristiania;

import no.kristiania.model.Ninja;
import org.apache.coyote.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NinjaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getUrl(){
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads(){}

    @Test
    public void testGetAllNinjas(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getUrl() + "/ninjas",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetNinjaId(){
        Ninja ninja = testRestTemplate.getForObject(getUrl() + "/ninjas/1", Ninja.class);
        assertNotNull(ninja.getName());
    }

    @Test
    public void testCreateNinja(){
        Ninja ninja = new Ninja();
        ninja.setName("Bruce Wayne");
        ninja.setKatana("Shinobigatana");
        ResponseEntity<Ninja> postResponse = testRestTemplate.postForEntity(getUrl() + "/ninjas", ninja, Ninja.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateNinja(){
        int id = 1;
        Ninja ninja = testRestTemplate.getForObject(getUrl() + "/ninjas/" + id, Ninja.class);
        ninja.setKatana("New Katana");
        testRestTemplate.put(getUrl() + "/ninjas/" + id, ninja);
        Ninja updatedNinja = testRestTemplate.getForObject(getUrl() + "/ninjas/" + id, Ninja.class);
        assertEquals("New Katana", updatedNinja.getKatana());
    }

    @Test
    public void testDeleteNinja(){
        int id = 2;
        Ninja ninja = testRestTemplate.getForObject(getUrl() + "/ninjas/" + id, Ninja.class);
        assertNotNull(ninja);
        testRestTemplate.delete(getUrl() + "/ninjas/" + id, Ninja.class);
        try {
            ninja = testRestTemplate.getForObject(getUrl() + "/ninjas/" + id, Ninja.class);
        } catch (final HttpClientErrorException e){
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
