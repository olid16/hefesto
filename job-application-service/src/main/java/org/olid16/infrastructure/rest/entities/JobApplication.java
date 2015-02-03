package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobApplication {
    
    @JsonProperty
    private final String jobId;
    @JsonProperty
    private final String jobseekerId;
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String resumeId;

    @JsonCreator
    public JobApplication(@JsonProperty("jobId") String jobId,
                          @JsonProperty("jobseekerId") String jobseekerId,
                          @JsonProperty("id") String id,
                          @JsonProperty("resumeId") String resumeId) {
        this.jobId = jobId;
        this.jobseekerId = jobseekerId;
        this.id = id;
        this.resumeId = resumeId;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobseekerId() {
        return jobseekerId;
    }

    public String getId() {
        return id;
    }

    public String getResumeId() {
        return resumeId;
    }
}