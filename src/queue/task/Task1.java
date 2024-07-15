package queue.task;

class Task1 implements Comparable<Task> {
    int id;
    String description;
    int priority;

    public Task1(int id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.priority, o.priority);
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", priority=" + priority + "]";
    }
}


