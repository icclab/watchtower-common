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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@Entity
public class Job implements Serializable {
  private static final long serialVersionUID = 5782821667305551652L;

  @NotEmpty(message = "Empty id")
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id", unique = true, nullable = false)
  private String id;

  private String jobId;

  private String incidentId;

  @NotEmpty(message = "Empty name")
  private String name;

  @ElementCollection
  private Map<String, String> parameters;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "jobId")
  private List<JobExecution> executions;

  public Job() {
    // Empty constructor
  }

  @JsonCreator
  public Job(@JsonProperty("id") String id, @JsonProperty("jobId") String jobId,
      @JsonProperty("incidentId") String incidentId, @JsonProperty("name") String name,
      @JsonProperty("parameters") Map<String, String> parameters,
      @JsonProperty("execution") List<JobExecution> executions) {
    this.id = id;
    this.jobId = jobId;
    this.incidentId = incidentId;
    this.name = name;
    this.parameters = parameters;
    this.executions = executions;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("jobId")
  public String getJobId() {
    return jobId;
  }

  @JsonProperty("jobId")
  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("incidentId")
  public String getIncidentId() {
    return incidentId;
  }

  @JsonProperty("incidentId")
  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }

  @JsonProperty("parameters")
  public Map<String, String> getParameters() {
    return parameters;
  }

  @JsonProperty("parameters")
  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }

  @JsonProperty("executions")
  public List<JobExecution> getExecutions() {
    return executions;
  }

  @JsonProperty("executions")
  public void setExecution(List<JobExecution> executions) {
    this.executions = executions;
  }

  public void addExecution(JobExecution execution) {
    if (executions == null)
      executions = new ArrayList<JobExecution>();

    executions.add(execution);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null)
      return false;
    if (!(object instanceof Job))
      return false;
    Job other = (Job) object;

    if (getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!getId().equalsIgnoreCase(other.getId()))
      return false;

    if (getJobId() == null) {
      if (other.getJobId() != null)
        return false;
    } else if (!getJobId().equalsIgnoreCase(other.getJobId()))
      return false;

    if (getName() == null) {
      if (other.getName() != null)
        return false;
    } else if (!getName().equalsIgnoreCase(other.getName()))
      return false;

    if (getParameters() == null) {
      if (other.getParameters() != null)
        return false;
    } else if (!getParameters().equals(other.getParameters()))
      return false;

    if (getExecutions() == null) {
      if (other.getExecutions() != null)
        return false;
    } else if (!getExecutions().equals(other.getExecutions()))
      return false;

    return true;
  }

  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("jobId", jobId)
        .add("incidentId", incidentId).add("name", name).add("parameters", parameters)
        .add("executions", executions).toString();
  }
}