package queue.task;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueTaskSheduler {
	Queue<Task> pendingTasks;
    Queue<Task> completedTasks;
    public QueueTaskSheduler() {
        this.pendingTasks = new PriorityQueue<>();
        this.completedTasks = new LinkedList<>();
    }
    public void addTask(Task task) {
        pendingTasks.add(task);
    }

    public void completeTask(Task task) {
        if (pendingTasks.remove(task)) {
            completedTasks.add(task);
        }
    }
    public List<Task> viewPendingTasks() {
        List<Task> tasks = new ArrayList<>(pendingTasks);
        tasks.sort(Comparator.naturalOrder());
        return tasks;
    }

    public List<Task> findTasksInRange(int minPriority, int maxPriority) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : pendingTasks) {
            if (task.priority >= minPriority && task.priority <= maxPriority) {
                tasks.add(task);
            }
        }
        tasks.sort(Comparator.naturalOrder());
        return tasks;
    }

	public static void main(String[] args) {
		QueueTaskSheduler q = new QueueTaskSheduler();

        Task task1 = new Task(1, "Task 1", 4);
        Task task2 = new Task(2, "Task 2", 2);
        Task task3 = new Task(3, "Task 3", 1);
        Task task4 = new Task(4, "Task 4", 3);
        Task task5 = new Task(5, "Task 5", 1);

        q.addTask(task1);
        q.addTask(task2);
        q.addTask(task3);
        q.addTask(task4);
        q.addTask(task5);

        System.out.println("Pending tasks:");
        for (Task task : q.viewPendingTasks()) {
            System.out.println(task);
        }

        q.completeTask(task2);

        System.out.println("Completed tasks:");
        for (Task task : q.completedTasks) {
            System.out.println(task);
        }

        System.out.println("Tasks in range [1, 3]:");
        for (Task task : q.findTasksInRange(1, 3)) {
            System.out.println(task);
        }

        // Performance analysis
      
        Queue<Task> linkedList = new LinkedList<>();
        Queue<Task> priorityQueue = new PriorityQueue<>();
        Queue<Task> arrayDeque = new ArrayDeque<>();

        long startTime, endTime;

        // Enqueue
        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            linkedList.add(new Task(i, "Task " + i, i % 10));
            try {
                Thread.sleep(300); // pause for 300 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Enqueue time for LinkedList: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            priorityQueue.add(new Task(i, "Task " + i, i % 10));
            try {
                Thread.sleep(300); // pause for 300 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Enqueue time for PriorityQueue: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            arrayDeque.add(new Task(i, "Task " + i, i % 10));
            try {
                Thread.sleep(300); // pause for 300 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Enqueue time for ArrayDeque: " + (endTime - startTime) + " ns");

        System.out.println("====================================================");
        
        // Dequeue
        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            linkedList.poll();
            try {
                Thread.sleep(100); // pause for 100 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Dequeue time for LinkedList: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            priorityQueue.poll();
            try {
                Thread.sleep(100); // pause for 100 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Dequeue time for PriorityQueue: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            arrayDeque.poll();
            try {
                Thread.sleep(100); // pause for 100 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Dequeuetime for ArrayDeque: " + (endTime - startTime) + " ns");

        System.out.println("=====================================================");
        
        // Peek
        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            linkedList.peek();
            try {
                Thread.sleep(200); // pause for 200 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Peek time for LinkedList: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            priorityQueue.peek();
            try {
                Thread.sleep(200); // pause for 200 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Peek time for PriorityQueue: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            arrayDeque.peek();
            try {
                Thread.sleep(200); // pause for 200 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Peek time for ArrayDeque: " + (endTime - startTime) + " ns");
    }

	}


