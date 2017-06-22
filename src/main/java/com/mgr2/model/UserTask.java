package com.mgr2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "user_task")
@AssociationOverrides({ @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "pk.task", joinColumns = @JoinColumn(name = "task_id")) })
public class UserTask implements Serializable{
	private static final long serialVersionUID = 1L;

	private UserTaskId pk = new UserTaskId();
	private Date createdDate;
	private int done;

	public UserTask() {
		// ??????
	}

	@EmbeddedId
	public UserTaskId getPk() {
		return pk;
	}

	public void setPk(UserTaskId pk) {
		this.pk = pk;
	}

	@Transient
	public User getUser() {
		return pk.getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}

	@Transient
	public Task getTask() {
		return pk.getTask();
	}

	public void setTask(Task task) {
		getPk().setTask(task);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "done")
	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserTask that = (UserTask) o;

		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;

		return true;
	}

}
