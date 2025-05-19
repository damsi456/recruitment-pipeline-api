package org.damsi.recruitmentpipelineapi.controller;

import org.damsi.recruitmentpipelineapi.model.ApplicationStage;
import org.damsi.recruitmentpipelineapi.model.Candidate;
import org.damsi.recruitmentpipelineapi.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable String id) {
        return candidateService.getCandidateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        return new ResponseEntity<>(candidateService.saveCandidate(candidate), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable String id, @RequestBody Candidate candidate) {
        return candidateService.getCandidateById(id)
                .map(existingCandidate -> {
                    Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
                    return ResponseEntity.ok(updatedCandidate);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable String id) {
        return candidateService.getCandidateById(id)
                .map(candidate -> {
                    candidateService.deleteCandidate(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stage/{stage}")
    public ResponseEntity<List<Candidate>> getCandidatesByStage(@PathVariable ApplicationStage stage) {
        return ResponseEntity.ok(candidateService.getCandidatesByStage(stage));
    }

}
