package queue.task;

 class Task implements Comparable<Task>{
	 int id;
	    String description;
	    int priority;
	    
	    
	    public Task(int id, String description, int priority) {
	        this.id = id;
	        this.description = description;
	        this.priority = priority;
	    }

	@Override
	public int compareTo(Task o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.priority, o.priority);
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", priority=" + priority + "]";
	}
 }