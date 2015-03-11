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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class Command implements Serializable {
  private static final long serialVersionUID = 4913484717464460393L;
  
  @NotEmpty(message="Empty command type")
  private CommandType type;
  
  @NotEmpty(message="Empty job")
  private Job job;
  
  @JsonCreator
  public Command(@JsonProperty("type") CommandType type, @JsonProperty("job") Job job) {
    this.type = type;
    this.job = job;
  }
  
  @JsonProperty("type")
  public CommandType getType() {
    return type;
  }
  
  @JsonProperty("job")
  public Job getJob() {
    return job;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    
    if (obj == null)
      return false;
    
    if (getClass() != obj.getClass())
      return false;
    
    Command other = (Command) obj;
    
    if (other.getType() != type)
      return false;
    
    if (!other.getJob().equals(job))
      return false;
    
    return true;
  }
  
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("type", type)
        .add("job", job)
        .toString();
  }
}