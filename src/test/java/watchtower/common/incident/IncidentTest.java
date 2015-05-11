package watchtower.common.incident;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Date;

import org.testng.annotations.Test;

@Test
public class IncidentTest {
  public void shouldSerializeValue() {
    Incident incident =
        new Incident("id", "summary", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MINOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    String incidentJson = IncidentUtils.toJson(incident);

    assertEquals(
        incidentJson,
        "{\"id\":\"id\",\"summary\":\"summary\",\"status\":\"NEW\",\"priority\":\"NORMAL\",\"severity\":\"MINOR\",\"impact\":\"INSIGNIFICANT\",\"events\":null,\"jobs\":null,\"dateCreated\":1399986000,\"dateLastUpdated\":1399986001,\"version\":0}");
  }

  public void shouldSerializeValueUTF() {
    Incident incident =
        new Incident("id", "foôbár", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MINOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    String incidentJson = IncidentUtils.toJson(incident);

    assertEquals(
        incidentJson,
        "{\"id\":\"id\",\"summary\":\"foôbár\",\"status\":\"NEW\",\"priority\":\"NORMAL\",\"severity\":\"MINOR\",\"impact\":\"INSIGNIFICANT\",\"events\":null,\"jobs\":null,\"dateCreated\":1399986000,\"dateLastUpdated\":1399986001,\"version\":0}");
  }

  public void shouldSerializeAndDeserialize() {
    Incident expected =
        new Incident("id", "summary", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MINOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    String incidentJson = IncidentUtils.toJson(expected);

    Incident incident = IncidentUtils.fromJson(incidentJson.getBytes());

    assertEquals(incident, expected);
  }

  public void shouldBeEqual() {
    Incident incident1 =
        new Incident("id", "summary", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MINOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    Incident incident2 =
        new Incident("id", "summary", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MINOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    assertEquals(incident1, incident2);
  }

  public void shouldNotBeEqual() {
    Incident incident1 =
        new Incident("id", "summary", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MINOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    Incident incident2 =
        new Incident("id", "summary", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MAJOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    assertNotEquals(incident1, incident2);
  }
}