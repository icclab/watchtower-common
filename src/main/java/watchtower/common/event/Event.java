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

//import io.dropwizard.validation.ValidationMethod;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class Event implements Serializable {
  private static final long serialVersionUID = -1783919866246632640L;
  
  @NotEmpty(message="Empty id")
  private String id;
  
  @NotEmpty(message="Empty service model")
  private EventServiceModel serviceModel;
  
  @NotEmpty(message="Empty message")
  private String message;
  
  public Event() {
    // Empty constructor so that Tomcat doesn't crash
  }
  
  @JsonCreator
  public Event(@JsonProperty("id") String id, @JsonProperty("serviceModel") EventServiceModel serviceModel,
      @JsonProperty("message") String message) {
    this.id = id;
    this.serviceModel = serviceModel;
    this.message = message;
  }
  
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  
  @JsonProperty("serviceModel")
  public EventServiceModel getServiceModel() {
    return serviceModel;
  }
  
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    
    if (obj == null)
      return false;
    
    if (getClass() != obj.getClass())
      return false;
    
    Event other = (Event) obj;
    
    if (!other.getId().equalsIgnoreCase(id))
      return false;
    
    if (!other.getMessage().equalsIgnoreCase(message))
      return false;
    
    return true;
  }
  
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("serviceModel", serviceModel)
        .add("message", message)
        .toString();
  }
}