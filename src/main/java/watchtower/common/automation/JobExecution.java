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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.MoreObjects;

@Entity
@JsonPropertyOrder({"id"})
public class JobExecution {
  @Id
  @Column(name = "id", unique = true, nullable = false)
  private String id;

  private String jobId;

  @Column(columnDefinition = "text")
  private String outcome;

  private JobExecutionStatus status;

  public JobExecution() {
    // Empty constructor
  }

  @JsonCreator
  public JobExecution(@JsonProperty("id") String id, @JsonProperty("jobId") String jobId,
      @JsonProperty("outcome") String outcome, @JsonProperty("status") JobExecutionStatus status) {
    this.id = id;
    this.jobId = jobId;
    this.outcome = outcome;
    this.status = status;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("executionId")
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

  @JsonProperty("outcome")
  public String getOutcome() {
    return outcome;
  }

  @JsonProperty("outcome")
  public void setOutcome(String outcome) {
    this.outcome = outcome;
  }

  @JsonProperty("status")
  public JobExecutionStatus getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(JobExecutionStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null)
      return false;
    if (!(object instanceof JobExecution))
      return false;
    JobExecution other = (JobExecution) object;

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

    if (getOutcome() == null) {
      if (other.getOutcome() != null)
        return false;
    } else if (!getOutcome().equalsIgnoreCase(other.getOutcome()))
      return false;

    if (getStatus() == null) {
      if (other.getStatus() != null)
        return false;
    } else if (!getOutcome().equalsIgnoreCase(other.getOutcome()))
      return false;

    return true;
  }

  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("jobId", jobId)
        .add("outcome", outcome).add("status", status).toString();
  }
}