/*
 * Copyright 2015 Zurich University of Applied Sciences
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package watchtower.common.event;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.testng.annotations.Test;

@Test
public class EventTest {
  public void shouldSerializeValue() {
    Event event = new Event();

    String eventJson = EventUtils.toJson(event);

    assertEquals(
        eventJson,
        "{\"id\":null,\"incidentId\":null,\"name\":null,\"message\":null,\"serviceModel\":null,\"date\":null}");

    event =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));

    eventJson = EventUtils.toJson(event);

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

  public void shouldBeSimilar() {
    Event event1 =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));
    Event event2 =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));

    assertTrue(event1.isSimilarTo(event2));
  }

  public void shouldNotBeSimilar() {
    Event event1 =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));
    Event event2 =
        new Event("id", "incidentId", "name", "message", EventServiceModel.PaaS, new Date(
            1399986000));

    assertFalse(event1.isSimilarTo(event2));
  }

  public void shouldBeEqual() {
    Event event1 =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));
    Event event2 =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));

    assertEquals(event2, event1);
  }

  public void shouldNotBeEqual() {
    Event event1 =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));
    Event event2 =
        new Event("id", "incidentId", "name", "message", EventServiceModel.PaaS, new Date(
            1399986000));

    assertNotEquals(event2, event1);
  }

  public void shouldClone() {
    Event expected =
        new Event("id", "incidentId", "name", "message", EventServiceModel.IaaS, new Date(
            1399986000));

    assertEquals(EventUtils.clone(expected), expected);
  }
}