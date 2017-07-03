package com.mgr2.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class UserTaskId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private User user;
	@JsonIgnore
	private Task task;
	
	@JsonIgnore
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonIgnore
	@ManyToOne
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
		UserTaskId other = (UserTaskId) obj;
//		if (task == null) {
//			if (other.task != null)
//				return false;
//		} else if (!task.equals(other.task))
//			return false;
//		if (user == null) {
//			if (other.user != null)
//				return false;
//		} else if (!user.equals(other.user))
//			return false;
		
		if(task.getId() == other.getTask().getId() && user.getId() == other.getUser().getId()){
			return true;
		}
		else{
			return false;
		}
	}
	
//	@Override
//	public int hashCode() {
//		int result;
//        result = (user != null ? user.hashCode() : 0);
//        result = 31 * result + (task != null ? task.hashCode() : 0);
//        return result;
//	}
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        UserTaskId that = (UserTaskId) o;
//
//        if (user != null ? !user.equals(that.user) : that.user != null) return false;
//        if (task != null ? !task.equals(that.task) : that.task != null)
//            return false;
//
//        return true;
//	}
	
	
}
