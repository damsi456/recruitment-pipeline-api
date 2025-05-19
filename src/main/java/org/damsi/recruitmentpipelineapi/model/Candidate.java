package org.damsi.recruitmentpipelineapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "candidates")
public class Candidate {
    @Id
    private String id;
    private String name;
    private ApplicationStage applicationStage;
    private LocalDate applicationDate;
    private int overallScore;
    private boolean referralStatus;
    private boolean assessmentStatus;

    public Candidate() {
    }

    public Candidate(String name, ApplicationStage applicationStage, LocalDate applicationDate,
                     Integer overallScore, boolean referralStatus, boolean assessmentStatus) {
        this.name = name;
        this.applicationStage = applicationStage;
        this.applicationDate = applicationDate;
        this.overallScore = overallScore;
        this.referralStatus = referralStatus;
        this.assessmentStatus = assessmentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationStage getApplicationStage() {
        return applicationStage;
    }

    public void setApplicationStage(ApplicationStage applicationStage) {
        this.applicationStage = applicationStage;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public boolean isReferralStatus() {
        return referralStatus;
    }

    public void setReferralStatus(boolean referralStatus) {
        this.referralStatus = referralStatus;
    }

    public boolean isAssessmentStatus() {
        return assessmentStatus;
    }

    public void setAssessmentStatus(boolean assessmentStatus) {
        this.assessmentStatus = assessmentStatus;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", applicationStage=" + applicationStage +
                ", applicationDate=" + applicationDate +
                ", overallScore=" + overallScore +
                ", referralStatus=" + referralStatus +
                ", assessmentStatus=" + assessmentStatus +
                '}';
    }
}

