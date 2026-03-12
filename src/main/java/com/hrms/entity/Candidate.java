package com.hrms.entity;

import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
	private String phone;
    @PrePersist
    @PreUpdate
    private void normalizeLinkedInUrl() {
        if (this.linkedinurl != null && !this.linkedinurl.isBlank()) {
            this.linkedinurl = this.linkedinurl.trim();
            if (!this.linkedinurl.startsWith("http://") &&
                !this.linkedinurl.startsWith("https://")) {
                this.linkedinurl = "https://" + this.linkedinurl;
            }
        }
    }
    @Column(nullable = false)
    private String linkedinurl;
    private String experience;
    @Column(nullable = false)
    private String resumePath;
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
   

    @OneToMany(mappedBy = "candidate")
    @JsonIgnore
    private List<Interview> interviews;
    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Offer offer;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CandidateStatus currentStatus;
    public Candidate() {}
    public Candidate(String name,
                     String email,
                     String phone,
                     String linkedinurl,
                     String experience,
                     String resumePath,
                     Job job) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.linkedinurl = linkedinurl;
        this.experience = experience;
        this.resumePath = resumePath;
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    public String getLinkedinurl() {
        return linkedinurl;
    }

    public void setLinkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public CandidateStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CandidateStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

	public void setStage(String string) {
		
	}

	
}