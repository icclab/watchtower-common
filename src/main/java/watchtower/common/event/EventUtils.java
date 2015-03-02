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

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EventUtils {
  private static final Logger logger = LoggerFactory.getLogger(EventUtils.class);
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  
  static {
    OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    OBJECT_MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    OBJECT_MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
  }
  
  public static Event fromJson(byte[] eventJson) {
    try {
      String jsonString = StringEscapeUtils.unescapeJava(new String(eventJson, "UTF-8"));
      return OBJECT_MAPPER.readValue(jsonString, Event.class);
    } catch (Exception e) {
      logger.error("Failed to parse event json: {}", e.toString());
    }
    
    return null;
  }

  public static String toJson(Event event) {
    try {
      return OBJECT_MAPPER.writeValueAsString(event);
    } catch (JsonProcessingException e) {
      logger.error("Failed to convert event to json: {}", e.toString());
    }
    
    return null;
  }
  
  public static Event clone(Event event) {
    if (event == null)
      return null;
    
    return new Event(event.getId(), event.getServiceModel(), event.getMessage());
  }
}