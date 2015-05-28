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
public class JobExecutionTest {
  public void shouldSerializeValue() {
    JobExecution jobExecution =
        new JobExecution("id", "jobId", "outcome", JobExecutionStatus.SUCCEEDED);

    String jobExecutionJson = JobExecutionUtils.toJson(jobExecution);

    assertEquals(jobExecutionJson,
        "{\"id\":\"id\",\"jobId\":\"jobId\",\"outcome\":\"outcome\",\"status\":\"SUCCEEDED\"}");
  }

  public void shouldSerializeValueUTF() {
    JobExecution jobExecution =
        new JobExecution("id", "jobId", "foôbár", JobExecutionStatus.SUCCEEDED);

    String jobExecutionJson = JobExecutionUtils.toJson(jobExecution);

    assertEquals(jobExecutionJson,
        "{\"id\":\"id\",\"jobId\":\"jobId\",\"outcome\":\"foôbár\",\"status\":\"SUCCEEDED\"}");
  }

  public void shouldSerializeAndDeserialize() {
    JobExecution jobExecution =
        new JobExecution("id", "jobId", "outcome", JobExecutionStatus.SUCCEEDED);

    assertEquals(JobExecutionUtils.fromJson(JobExecutionUtils.toJson(jobExecution).getBytes()),
        jobExecution);
  }

  public void shouldBeEqual() {
    JobExecution jobExecution1 =
        new JobExecution("id", "jobId", "outcome", JobExecutionStatus.SUCCEEDED);
    JobExecution jobExecution2 =
        new JobExecution("id", "jobId", "outcome", JobExecutionStatus.SUCCEEDED);

    assertEquals(jobExecution1, jobExecution2);
  }

  public void shouldNotBeEqual() {
    JobExecution jobExecution1 =
        new JobExecution("id", "jobId1", "outcome", JobExecutionStatus.SUCCEEDED);
    JobExecution jobExecution2 =
        new JobExecution("id", "jobId2", "outcome", JobExecutionStatus.SUCCEEDED);

    assertNotEquals(jobExecution1, jobExecution2);
  }

  public void shouldClone() {
    JobExecution jobExecution =
        new JobExecution("id", "jobId", "outcome", JobExecutionStatus.SUCCEEDED);

    assertEquals(JobExecutionUtils.clone(jobExecution), jobExecution);
  }
}