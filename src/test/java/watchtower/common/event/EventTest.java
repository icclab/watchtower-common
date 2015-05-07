package watchtower.common.event;

import static org.testng.Assert.assertEquals;

import java.util.Date;

import org.testng.annotations.Test;

@Test
public class EventTest {
  public void shouldSerializeValue() {
    Event event =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));

    String eventJson = EventUtils.toJson(event);

    assertEquals(
        eventJson,
        "{\"id\":\"id\",\"incidentId\":\"incidentId\",\"name\":\"name\",\"message\":\"message\",\"serviceModel\":\"IaaS\",\"date\":1399986000}");
  }

  public void shouldSerializeValueUTF() {
    Event event =
        new Event("id", "incidentId", "foôbár", "message", EventServiceModel.IaaS, new Date(
            1399986000));

    String eventJson = EventUtils.toJson(event);

    assertEquals(
        eventJson,
        "{\"id\":\"id\",\"incidentId\":\"incidentId\",\"name\":\"foôbár\",\"message\":\"message\",\"serviceModel\":\"IaaS\",\"date\":1399986000}");
  }

  public void shouldSerializeAndDeserialize() {
    Event expected =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));

    String eventJson = EventUtils.toJson(expected);

    Event event = EventUtils.fromJson(eventJson.getBytes());
    assertEquals(event, expected);
  }
}