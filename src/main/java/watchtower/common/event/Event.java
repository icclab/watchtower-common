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
package watchtower.common.event;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@Entity
public class Event implements Serializable {
  private static final long serialVersionUID = -1783919866246632640L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id", unique = true, nullable = false)
  private String id;

  private String incidentId;
  private String name;
  private String message;

  @Enumerated(EnumType.STRING)
  private EventServiceModel serviceModel;

  private Date date;

  public Event() {
    // Empty constructor
  }

  @JsonCreator
  public Event(@JsonProperty("id") String id, @JsonProperty("incidentId") String incidentId,
      @JsonProperty("name") String name, @JsonProperty("message") String message,
      @JsonProperty("serviceModel") EventServiceModel serviceModel, @JsonProperty("date") Date date) {
    this.id = id;
    this.incidentId = incidentId;
    this.name = name;
    this.message = message;
    this.serviceModel = serviceModel;
    this.date = date;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("incidentId")
  public String getIncidentId() {
    return incidentId;
  }

  @JsonProperty("incidentId")
  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  @JsonProperty("message")
  public void setMessage(String message) {
    this.message = message;
  }

  @JsonProperty("serviceModel")
  public EventServiceModel getServiceModel() {
    return serviceModel;
  }

  @JsonProperty("serviceModel")
  public void setServiceModel(EventServiceModel serviceModel) {
    this.serviceModel = serviceModel;
  }

  @JsonProperty("date")
  public Date getDate() {
    return date;
  }

  @JsonProperty("date")
  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isSimilarTo(Event event) {
    if (equals(event))
      return true;

    if (!event.getName().equalsIgnoreCase(name))
      return false;

    if (!event.getMessage().equalsIgnoreCase(message))
      return false;

    if (serviceModel != event.getServiceModel())
      return false;

    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;

    if (this == obj)
      return true;

    if (getClass() != obj.getClass())
      return false;

    Event other = (Event) obj;

    if (!other.getId().equalsIgnoreCase(id))
      return false;

    if (!other.getIncidentId().equalsIgnoreCase(incidentId))
      return false;

    if (!other.getName().equalsIgnoreCase(name))
      return false;

    if (!other.getMessage().equalsIgnoreCase(message))
      return false;

    if (serviceModel != other.getServiceModel())
      return false;

    if (other.getDate().equals(date))
      return false;

    return true;
  }

  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("incidentId", incidentId)
        .add("name", name).add("message", message).add("serviceModel", serviceModel)
        .add("date", date).toString();
  }
}