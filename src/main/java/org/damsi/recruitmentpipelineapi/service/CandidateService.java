package org.damsi.recruitmentpipelineapi.service;

import org.damsi.recruitmentpipelineapi.model.ApplicationStage;
import org.damsi.recruitmentpipelineapi.model.Candidate;
import org.damsi.recruitmentpipelineapi.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(String id) {
        return candidateRepository.findById(id);
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(String id, Candidate candidate) {
        candidate.setId(id);
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(String id) {
        candidateRepository.deleteById(id);
    }

    public List<Candidate> getCandidatesByStage(ApplicationStage stage) {
        return candidateRepository.findByApplicationStage(stage);
    }

}
