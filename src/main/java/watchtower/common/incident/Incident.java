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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class Incident implements Serializable {
  private static final long serialVersionUID = -7459504722479802812L;
  
  private String id;
  private String summary;
  private IncidentPriority priority;
  private IncidentSeverity severity;
  private IncidentImpact impact;
  private List<Event> events;
  
  @JsonCreator
  public Incident(@JsonProperty("id") String id, @JsonProperty("summary") String summary,
      @JsonProperty("priority") IncidentPriority priority,
      @JsonProperty("severity") IncidentSeverity severity,
      @JsonProperty("impact") IncidentImpact impact,
      @JsonProperty("events") List<Event> events) {
    this.id = id;
    this.summary = summary;
    this.priority = priority;
    this.severity = severity;
    this.impact = impact;
    this.events = events;
  }
  
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  
  @JsonProperty("summary")
  public String getSummary() {
    return summary;
  }
  
  @JsonProperty("priority")
  public IncidentPriority getPriority() {
    return priority;
  }
  
  @JsonProperty("severity")
  public IncidentSeverity getIncidentSeverity() {
    return severity;
  }
  
  @JsonProperty("impact")
  public IncidentImpact getIncidentImpact() {
    return impact;
  }
  
  @JsonProperty("events")
  public List<Event> getEvents() {
    return events;
  }
  
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