package watchtower.common.automation;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JobExecutionUtils {
  private static final Logger logger = LoggerFactory.getLogger(JobExecutionUtils.class);
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  static {
    OBJECT_MAPPER
        .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    OBJECT_MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    OBJECT_MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
  }

  public static JobExecution fromJson(byte[] jobJson) {
    try {
      String jsonString = StringEscapeUtils.unescapeJava(new String(jobJson, "UTF-8"));
      return OBJECT_MAPPER.readValue(jsonString, JobExecution.class);
    } catch (Exception e) {
      logger.error("Failed to parse job execution json: {}", e.toString());
    }

    return null;
  }

  public static String toJson(JobExecution execution) {
    try {
      return OBJECT_MAPPER.writeValueAsString(execution);
    } catch (JsonProcessingException e) {
      logger.error("Failed to convert job execution to json: {}", e.toString());
    }

    return null;
  }

  public static JobExecution clone(JobExecution execution) {
    if (execution == null)
      return null;

    return new JobExecution(execution.getId(), execution.getJobId(), execution.getOutcome(),
        execution.getStatus());
  }
}