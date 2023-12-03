package com.joinventure.entities.DTOs;

import java.time.LocalDate;

public class IntegrationRequest {
	private Long projectId;
    private Long userId;
    private LocalDate integratedAt;
    
	public IntegrationRequest() {
		super();
	}
	public IntegrationRequest(Long projectId, Long userId, LocalDate integratedAt) {
		super();
		this.projectId = projectId;
		this.userId = userId;
		this.integratedAt = integratedAt;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LocalDate getIntegratedAt() {
		return integratedAt;
	}
	public void setIntegratedAt(LocalDate integratedAt) {
		this.integratedAt = integratedAt;
	}
	@Override
	public String toString() {
		return "IntegrationRequest [projectId=" + projectId + ", userId=" + userId + ", integratedAt=" + integratedAt
				+ "]";
	}
}
