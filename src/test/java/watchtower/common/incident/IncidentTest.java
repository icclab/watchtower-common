/*
 * Copyright 2015 Zurich Universityimport static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Date;

import org.testng.annotations.Test;
se. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package watchtower.common.incident;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Date;

import org.testng.annotations.Test;

@Test
public class IncidentTest {
  public void shouldSerializeValue() {
    Incident incident = new Incident();

    String incidentJson = IncidentUtils.toJson(incident);

    assertEquals(
        incidentJson,
        "{\"id\":null,\"summary\":null,\"status\":null,\"priority\":null,\"severity\":null,\"impact\":null,\"events\":null,\"jobs\":null,\"dateCreated\":null,\"dateLastUpdated\":null,\"version\":0}");

    incident =
        new Incident("id", "summary", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MINOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    incidentJson = IncidentUtils.toJson(incident);

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

    assertEquals(IncidentUtils.fromJson(IncidentUtils.toJson(expected).getBytes()), expected);
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

  public void shoudClone() {
    Incident expected =
        new Incident("id", "summary", IncidentStatus.NEW, IncidentPriority.NORMAL,
            IncidentSeverity.MINOR, IncidentImpact.INSIGNIFICANT, null, null, new Date(1399986000),
            new Date(1399986001), 0);

    assertEquals(IncidentUtils.clone(expected), expected);
  }
}