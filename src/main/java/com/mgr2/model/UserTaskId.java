package com.mgr2.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserTaskId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Task task;
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	
	@Override
	public int hashCode() {
		int result;
        result = (user != null ? user.hashCode() : 0);
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTaskId that = (UserTaskId) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (task != null ? !task.equals(that.task) : that.task != null)
            return false;

        return true;
	}
	
}
