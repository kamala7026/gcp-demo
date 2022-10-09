package com.kkb.gcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkb.gcp.model.JobState;
import com.kkb.gcp.model.Params;

public interface ParamsRepository extends JpaRepository<Params, Long> {
}
