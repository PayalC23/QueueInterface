package queue.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sheduler {
	Queue<Task> pendingTasks;
    Queue<Task> completedTasks;

    public Sheduler() {
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
		Sheduler s = new Sheduler();

	        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Flynaut Training\\Test\\src\\com\\test\\task.txt"))) {
	            String line;
	            while ((line = br.readLine())!= null) {
	                String[] parts = line.split(",");
	                int id = Integer.parseInt(parts[0]);
	                String description = parts[1];
	                int priority = Integer.parseInt(parts[2]);
	                Task task = new Task(id, description, priority);
	                s.addTask(task);
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading file: " + e.getMessage());
	        }

	        System.out.println("Pending tasks:");
	        for (Task task : s.viewPendingTasks()) {
	            System.out.println(task);
	        }

	        s.completeTask(s.viewPendingTasks().get(0));

	        System.out.println("Completed tasks:");
	        for (Task task : s.completedTasks) {
	            System.out.println(task);
	        }

	        System.out.println("Tasks in range [1, 3]:");
	        for (Task task : s.findTasksInRange(1, 3)) {
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
	        for (int i= 0; i < 10; i++) {
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
	        System.out.println("Dequeue time for ArrayDeque: " + (endTime - startTime) + " ns");

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


