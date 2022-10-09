package com.kkb.gcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkb.gcp.model.JobState;

public interface JobStateRepository extends JpaRepository<JobState, Long> {
}
