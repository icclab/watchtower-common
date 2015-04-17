package watchtower.common.automation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@Entity
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
    return id.equalsIgnoreCase(other.getId());
  }

  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("jobId", jobId)
        .add("outcome", outcome).add("status", status).toString();
  }
}