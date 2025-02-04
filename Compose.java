package com.hr.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name="COMPOSE")
@Entity
public class Compose {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 
	 	private String empName ;
	 	
		private String subject ;
		
		@Column(name="TEXT",length = 2000)
		private String text;
		
		private Integer parentUkid;
		
		private String status;
		
		private String addedDate;
		
		@Transient
		private String position;
		
		@CreatedDate
	    @Column(name = "created_at", nullable = false, updatable = false)
	    private Date createdAt;
	    
	    @LastModifiedDate
	    @Column(name="updated_at")
	    private LocalDateTime updatedAt;

		public Compose() {
			super();
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Integer getParentUkid() {
			return parentUkid;
		}

		public void setParentUkid(Integer parentUkid) {
			this.parentUkid = parentUkid;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getAddedDate() {
			return addedDate;
		}

		public void setAddedDate(String addedDate) {
			this.addedDate = addedDate;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
		

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		@Override
		public String toString() {
			return "Compose [id=" + id + ", empName=" + empName + ", subject=" + subject + ", text=" + text
					+ ", parentUkid=" + parentUkid + ", status=" + status + ", addedDate=" + addedDate + ", createdAt="
					+ createdAt + ", updatedAt=" + updatedAt + "]";
		}
	    
	    
}
