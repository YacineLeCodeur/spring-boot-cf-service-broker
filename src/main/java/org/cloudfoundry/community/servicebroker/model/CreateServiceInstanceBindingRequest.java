package org.cloudfoundry.community.servicebroker.model;

import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Request sent from the cloud controller to bind to a service 
 * instance.
 * 
 * @author sgreenberg@gopivotal.com
 *
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class CreateServiceInstanceBindingRequest {

	@NotEmpty
	@JsonSerialize
	@JsonProperty("service_id")
	private String serviceDefinitionId;
	
	@NotEmpty
	@JsonSerialize
	@JsonProperty("plan_id")
	private String planId;

	@NotEmpty
	@JsonSerialize
	@JsonProperty("app_guid")
	private String appGuid;

    @JsonSerialize
    @JsonProperty("parameters")
    private Object parameters;

	@JsonIgnore
	private String serviceInstanceId;

	@JsonIgnore
	private String bindingId;
	
	public CreateServiceInstanceBindingRequest() {}
	
	public CreateServiceInstanceBindingRequest(String serviceDefinitionId, String planId, String appGuid) {
		this.serviceDefinitionId = serviceDefinitionId;
		this.planId = planId;
		this.appGuid = appGuid;
	}

	public String getServiceDefinitionId() {
		return serviceDefinitionId;
	}
	
	public void setServiceDefinitionId(String serviceDefinitionId) {
		this.serviceDefinitionId = serviceDefinitionId;
	}

	public String getPlanId() {
		return planId;
	}
	
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getAppGuid() {
		return appGuid;
	}

	public void setAppGuid(String appGuid) {
		this.appGuid = appGuid;
	}
	
	public String getBindingId() { 
		return bindingId;
	}
	
	public String getServiceInstanceId() { 
		return serviceInstanceId;
	}
	
	public Map<?, ?> getParameters(){
	    return getParameters(Map.class);
	}
	
	public <T> T getParameters(Class<T> cls) throws IllegalArgumentException{
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(parameters, cls);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
	
	public void setParameters(Object parameters) {
        this.parameters = parameters;
    }

	public CreateServiceInstanceBindingRequest withServiceInstanceId(final String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
		return this;
	}

	public CreateServiceInstanceBindingRequest withBindingId(final String bindingId) {
		this.bindingId = bindingId;
		return this;
	}
	
	public CreateServiceInstanceBindingRequest and() {
		return this;
	}
}
