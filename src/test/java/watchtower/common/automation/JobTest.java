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
package watchtower.common.automation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

@Test
public class JobTest {
  public void shouldSerializeValue() {
    Job job = new Job();

    String jobJson = JobUtils.toJson(job);

    assertEquals(
        jobJson,
        "{\"id\":null,\"jobId\":null,\"incidentId\":null,\"name\":null,\"parameters\":null,\"executions\":null}");

    job = new Job("id", "jobId", "incidentId", "name", null, null);

    jobJson = JobUtils.toJson(job);

    assertEquals(
        jobJson,
        "{\"id\":\"id\",\"jobId\":\"jobId\",\"incidentId\":\"incidentId\",\"name\":\"name\",\"parameters\":null,\"executions\":null}");
  }

  public void shouldSerializeValueUTF() {
    Job job = new Job("id", "jobId", "incidentId", "foôbár", null, null);

    String jobJson = JobUtils.toJson(job);

    assertEquals(
        jobJson,
        "{\"id\":\"id\",\"jobId\":\"jobId\",\"incidentId\":\"incidentId\",\"name\":\"foôbár\",\"parameters\":null,\"executions\":null}");
  }

  public void shouldSerializeAndDeserialize() {
    Job expected = new Job("id", "jobId", "incidentId", "foôbár", null, null);

    assertEquals(JobUtils.fromJson(JobUtils.toJson(expected).getBytes()), expected);
  }

  public void shouldBeEqual() {
    Job job1 = new Job("id", "jobId", "incidentId", "name", null, null);
    Job job2 = new Job("id", "jobId", "incidentId", "name", null, null);

    assertEquals(job1, job2);
  }

  public void shouldNotBeEqual() {
    Job job1 = new Job("id", "jobId1", "incidentId", "name", null, null);
    Job job2 = new Job("id", "jobId2", "incidentId", "name", null, null);

    assertNotEquals(job1, job2);
  }

  public void shouldClone() {
    Job expected = new Job("id", "jobId", "incidentId", "foôbár", null, null);

    assertEquals(JobUtils.clone(expected), expected);
  }
}