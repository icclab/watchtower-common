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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class Job implements Serializable {
  private static final long serialVersionUID = 5782821667305551652L;
  
  @NotEmpty(message="Empty id")
  private String id;
  
  @NotEmpty(message="Empty name")
  private String name;
  
  @NotEmpty(message="Empty parameters")
  private Map<String, String> parameters;

  @JsonCreator
  public Job(@JsonProperty("id") String id, @JsonProperty("name") String name,
      @JsonProperty("parameters") Map<String, String> parameters) {
    this.id = id;
    this.name = name;
    this.parameters = parameters;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("parameters")
  public Map<String, String> getParameters() {
    return parameters;
  }

  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("name", name)
        .add("parameters", parameters).toString();
  }
}