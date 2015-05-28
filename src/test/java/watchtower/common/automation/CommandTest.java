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
public class CommandTest {
  public void shouldSerializeValue() {
    Command command = new Command(CommandType.RUN_JOB, new Job());

    String commandJson = CommandUtils.toJson(command);

    assertEquals(
        commandJson,
        "{\"type\":\"RUN_JOB\",\"job\":{\"id\":null,\"jobId\":null,\"incidentId\":null,\"name\":null,\"parameters\":null,\"executions\":null}}");
  }

  public void shouldSerializeValueUTF() {
    Command command =
        new Command(CommandType.RUN_JOB, new Job("id", "jobId", "incidentId", "foôbár", null, null));

    String commandJson = CommandUtils.toJson(command);

    assertEquals(
        commandJson,
        "{\"type\":\"RUN_JOB\",\"job\":{\"id\":\"id\",\"jobId\":\"jobId\",\"incidentId\":\"incidentId\",\"name\":\"foôbár\",\"parameters\":null,\"executions\":null}}");
  }

  public void shouldSerializeAndDeserialize() {
    Command command = new Command(CommandType.RUN_JOB, new Job());

    assertEquals(CommandUtils.fromJson(CommandUtils.toJson(command).getBytes()), command);
  }

  public void shouldBeEqual() {
    Command command1 =
        new Command(CommandType.RUN_JOB, new Job("id", "jobId", "incidentId", "test", null, null));
    Command command2 =
        new Command(CommandType.RUN_JOB, new Job("id", "jobId", "incidentId", "test", null, null));

    assertEquals(command1, command2);
  }

  public void shouldNotBeEqual() {
    Command command1 =
        new Command(CommandType.RUN_JOB, new Job("id1", "jobId", "incidentId", "test", null, null));
    Command command2 =
        new Command(CommandType.RUN_JOB, new Job("id2", "jobId", "incidentId", "test", null, null));

    assertNotEquals(command1, command2);
  }

  public void shouldClone() {
    Command command =
        new Command(CommandType.RUN_JOB, new Job("id", "jobId", "incidentId", "test", null, null));

    assertEquals(CommandUtils.clone(command), command);
  }
}