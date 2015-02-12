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
package watchtower.common.incident;

import java.io.Serializable;
import java.util.List;

import watchtower.common.event.Event;

import com.google.common.base.MoreObjects;

public class Incident implements Serializable {
  private static final long serialVersionUID = -7459504722479802812L;
  
  public String id;
  public String summary;
  public IncidentPriority priority;
  public IncidentSeverity severity;
  public IncidentImpact impact;
  public List<Event> events;
  
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("summary", summary)
        .add("priority", priority)
        .add("severity", severity)
        .add("impact", impact)
        .add("events", events)
        .toString();
  }
}
