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

import java.util.Map;

import com.google.common.base.MoreObjects;

public class Job {
  private String id;
  private String name;
  private Map<String, String> parameters;
  
  public String getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public Map<String, String> getParameters() {
    return parameters;
  }
  
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("parameters", parameters)
        .toString();
  }
}