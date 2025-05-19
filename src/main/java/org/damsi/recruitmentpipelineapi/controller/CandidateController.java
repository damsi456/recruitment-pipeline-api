package org.damsi.recruitmentpipelineapi.controller;

import org.damsi.recruitmentpipelineapi.model.ApplicationStage;
import org.damsi.recruitmentpipelineapi.model.Candidate;
import org.damsi.recruitmentpipelineapi.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Page<Candidate>> getAllCandidates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "applicationDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        // check for the sorting direction
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // get page by specifying no and the size
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Candidate> candidatePage = candidateService.getAllCandidates(pageable);

        return ResponseEntity.ok(candidatePage);
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

    // to update the stage for drag & drop functionality in front-end
    @PatchMapping("/{id}/stage")
    public ResponseEntity<Candidate> updateStage(@PathVariable String id, @RequestParam ApplicationStage newStage) {
        return candidateService.updateCandidateStage(id, newStage)
                .map(ResponseEntity::ok)
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
    public ResponseEntity<Page<Candidate>> getCandidatesByStage(
            @PathVariable ApplicationStage stage,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "applicationDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        // check for the sorting direction
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // get page by specifying no and the size
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Candidate> candidatePage = candidateService.getCandidatesByStage(stage, pageable);

        return ResponseEntity.ok(candidatePage);
    }
}
