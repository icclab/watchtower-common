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
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import watchtower.common.automation.Job;
import watchtower.common.event.Event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@Entity
public class Incident implements Serializable {
  private static final long serialVersionUID = -7459504722479802812L;

  @Id
  // @GeneratedValue(generator = "system-uuid")
  // @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id", unique = true, nullable = false)
  private String id;

  private String summary;

  @Enumerated(EnumType.STRING)
  private IncidentStatus status;

  @Enumerated(EnumType.STRING)
  private IncidentPriority priority;

  @Enumerated(EnumType.STRING)
  private IncidentSeverity severity;

  @Enumerated(EnumType.STRING)
  private IncidentImpact impact;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "incidentId")
  private List<Event> events;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "incidentId")
  private List<Job> jobs;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateLastUpdated;

  @Version
  @Column(name = "optlock")
  private long version;

  public Incident() {
    // Empty constructor
  };

  @JsonCreator
  public Incident(@JsonProperty("id") String id, @JsonProperty("summary") String summary,
      @JsonProperty("status") IncidentStatus status,
      @JsonProperty("priority") IncidentPriority priority,
      @JsonProperty("severity") IncidentSeverity severity,
      @JsonProperty("impact") IncidentImpact impact, @JsonProperty("events") List<Event> events,
      @JsonProperty("jobs") List<Job> jobs, @JsonProperty("dateCreated") Date dateCreated,
      @JsonProperty("dateLastUpdated") Date dateLastUpdated, @JsonProperty("version") long version) {
    this.id = id;
    this.summary = summary;
    this.status = status;
    this.priority = priority;
    this.severity = severity;
    this.impact = impact;
    this.events = events;

    if (this.events != null)
      for (Event event : this.events)
        event.setIncidentId(getId());

    this.jobs = jobs;

    if (this.jobs != null)
      for (Job job : this.jobs)
        job.setIncidentId(getId());

    this.dateCreated = dateCreated;
    this.dateLastUpdated = dateLastUpdated;
    this.version = version;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("summary")
  public String getSummary() {
    return summary;
  }

  @JsonProperty("summary")
  public void setSummary(String summary) {
    this.summary = summary;
  }

  @JsonProperty("status")
  public IncidentStatus getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(IncidentStatus status) {
    this.status = status;
  }

  @JsonProperty("priority")
  public IncidentPriority getPriority() {
    return priority;
  }

  @JsonProperty("priority")
  public void setPriority(IncidentPriority priority) {
    this.priority = priority;
  }

  @JsonProperty("severity")
  public IncidentSeverity getSeverity() {
    return severity;
  }

  @JsonProperty("severity")
  public void setSeverity(IncidentSeverity severity) {
    this.severity = severity;
  }

  @JsonProperty("impact")
  public IncidentImpact getImpact() {
    return impact;
  }

  @JsonProperty("impact")
  public void setImpact(IncidentImpact impact) {
    this.impact = impact;
  }

  @JsonProperty("events")
  public List<Event> getEvents() {
    return events;
  }

  @JsonProperty("events")
  public void setEvents(List<Event> events) {
    this.events = events;

    if (this.events != null)
      for (Event event : this.events)
        event.setIncidentId(getId());
  }

  @JsonProperty("jobs")
  public List<Job> getJobs() {
    return jobs;
  }

  @JsonProperty("jobs")
  public void setJobs(List<Job> jobs) {
    this.jobs = jobs;

    if (this.jobs != null)
      for (Job job : this.jobs)
        job.setIncidentId(getId());
  }

  @JsonProperty("dateCreated")
  public Date getDateCreated() {
    return dateCreated;
  }

  @JsonProperty("dateCreated")
  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  @JsonProperty("dateLastUpdated")
  public Date getDateLastUpdated() {
    return dateLastUpdated;
  }

  @JsonProperty("dateLastUpdated")
  public void setDateLastUpdated(Date dateLastUpdated) {
    this.dateLastUpdated = dateLastUpdated;
  }

  @JsonProperty("version")
  public long getVersion() {
    return version;
  }

  @JsonProperty("version")
  public void setVersion(long version) {
    this.version = version;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null)
      return false;
    if (!(object instanceof Incident))
      return false;
    Incident other = (Incident) object;

    if (!getId().equals(other.getId()))
      return false;

    if (!getSummary().equalsIgnoreCase(other.getSummary()))
      return false;

    if (!getStatus().equals(other.getStatus()))
      return false;

    if (!getPriority().equals(other.getPriority()))
      return false;

    if (!getSeverity().equals(other.getSeverity()))
      return false;

    if (!getImpact().equals(other.getImpact()))
      return false;

    if (getEvents() == null) {
      if (other.getEvents() != null)
        return false;
    } else if (!getEvents().equals(other.getEvents()))
      return false;

    if (getJobs() == null) {
      if (other.getJobs() != null)
        return false;
    } else if (!getJobs().equals(other.getJobs()))
      return false;

    if (getDateCreated().compareTo(other.getDateCreated()) != 0)
      return false;

    if (getDateLastUpdated().compareTo(other.getDateLastUpdated()) != 0)
      return false;

    if (getVersion() != other.getVersion())
      return false;

    if (!getSeverity().equals(other.getSeverity()))
      return false;

    return true;
  }

  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("summary", summary)
        .add("status", status).add("priority", priority).add("severity", severity)
        .add("impact", impact).add("events", events).add("jobs", jobs)
        .add("dateCreated", dateCreated).add("dateLastUpdated", dateLastUpdated)
        .add("version", version).toString();
  }
}