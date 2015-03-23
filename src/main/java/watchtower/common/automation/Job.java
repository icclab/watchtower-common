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

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import watchtower.common.incident.Incident;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@Entity
public class Job implements Serializable {
  private static final long serialVersionUID = 5782821667305551652L;

  @NotEmpty(message = "Empty id")
  @Id
  @Column(name = "id", unique = true, nullable = false)
  private String id;

  private Incident incident;

  @NotEmpty(message = "Empty name")
  private String name;

  @NotEmpty(message = "Empty parameters")
  @ElementCollection
  private Map<String, String> parameters;

  private String outcome;

  @JsonCreator
  public Job(@JsonProperty("id") String id, @JsonProperty("incident") Incident incident,
      @JsonProperty("name") String name,
      @JsonProperty("parameters") Map<String, String> parameters,
      @JsonProperty("outcome") String outcome) {
    this.id = id;
    this.incident = incident;
    this.name = name;
    this.parameters = parameters;
    this.outcome = outcome;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("incident")
  public Incident getIncident() {
    return incident;
  }

  @JsonProperty("incident")
  public void setIncident(Incident incident) {
    this.incident = incident;
  }

  @JsonProperty("parameters")
  public Map<String, String> getParameters() {
    return parameters;
  }

  @JsonProperty("outcome")
  public String getOutcome() {
    return outcome;
  }

  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("incident", incident)
        .add("name", name).add("parameters", parameters).toString();
  }
}