package org.damsi.recruitmentpipelineapi.repository;

import org.damsi.recruitmentpipelineapi.model.ApplicationStage;
import org.damsi.recruitmentpipelineapi.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {
    List<Candidate> findByApplicationStage(ApplicationStage applicationStage);
}
