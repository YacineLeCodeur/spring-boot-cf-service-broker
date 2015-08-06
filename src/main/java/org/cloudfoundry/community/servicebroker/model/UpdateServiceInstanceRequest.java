package org.cloudfoundry.community.servicebroker.model;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A request sent by the cloud controller to update an instance of a service.
 * 
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateServiceInstanceRequest {

	@NotEmpty
	@JsonSerialize
	@JsonProperty("plan_id")
	private String planId;

	@JsonSerialize
	@JsonProperty("parameters")
	private Map<String, Object> parameters;
	
	@JsonIgnore
	private String serviceInstanceId;

	@JsonIgnore
	private boolean async;

	public UpdateServiceInstanceRequest() {
	}

	public UpdateServiceInstanceRequest(String planId) {
		this.planId = planId;
	}

	public UpdateServiceInstanceRequest(String planId, Map<String, Object> parameters) {
		this(planId);
		this.parameters = parameters;
	}

	public String getPlanId() {
		return planId;
	}
	
	public String getServiceInstanceId() { 
		return serviceInstanceId;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public <T> T getParameters(Class<T> cls) throws IllegalArgumentException {
		try {
			T bean = cls.newInstance();
			BeanUtils.populate(bean, parameters);
			return bean;
		} catch (Exception e) {
			throw new IllegalArgumentException("Error mapping parameters to class of type " + cls.getName());
		}
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public UpdateServiceInstanceRequest withInstanceId(String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId; 
		return this;
	}

	public UpdateServiceInstanceRequest withAsyncClient(boolean acceptsIncomplete) {
		this.async = acceptsIncomplete;
		return this;
	}

	public boolean asyncClient() {
		return async;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UpdateServiceInstanceRequest that = (UpdateServiceInstanceRequest) o;
		return Objects.equals(planId, that.planId) &&
				Objects.equals(parameters, that.parameters);
	}

	@Override
	public int hashCode() {
		return Objects.hash(planId, parameters);
	}

}
