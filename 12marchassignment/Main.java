package Comparable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1: Объединение списков");
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(8);

        System.out.println("Список 1: " + list1);
        System.out.println("Список 2: " + list2);
        System.out.println("Объединенный список: " + combineAlternating(list1, list2));

        System.out.println("\nЗадание 2: Вывод ключей и значений Map");
        Map<String, Double> prices = new HashMap<>();
        prices.put("Яблоки", 120.50);
        prices.put("Апельсины", 150.75);
        prices.put("Бананы", 80.00);

        printMap(prices);

        System.out.println("\nЗадание 3: Планировщик задач");

        TaskScheduler scheduler = new TaskScheduler();

        Task task1 = new Task("Code Review", 3, 20);
        Task task2 = new Task("System Update", 5, 45);
        Task task3 = new Task("Database Backup", 2, 30);
        Task task4 = new Task("Deploy New Feature", 5, 50);
        Task task5 = new Task("Bug Fixing", 4, 25);

        scheduler.addTask(task1);
        scheduler.addTask(task2);
        scheduler.addTask(task3);
        scheduler.addTask(task4);
        scheduler.addTask(task5);

        System.out.println("\nTasks Added:");
        System.out.println(task1);
        System.out.println(task2);
        System.out.println(task3);
        System.out.println(task4);
        System.out.println(task5);

        System.out.println("\nScheduled Tasks (sorted by priority): ");
        scheduler.displayAllTasks();

        Task processedTask = scheduler.processNextTask();
        System.out.println("\nProcessing Task: " + processedTask);

        scheduler.delayTask("Code Review");
        System.out.println("Delaying Task: Code Review");

        System.out.println("\nScheduled Tasks (sorted by priority): ");
        scheduler.displayAllTasks();

        scheduler.delayTask("Database Backup");
        System.out.println("\nDelaying Task: Database Backup");

        System.out.println("\nScheduled Tasks (sorted by priority): ");
        scheduler.displayAllTasks();

        processedTask = scheduler.processNextTask();
        System.out.println("\nProcessing Task: " + processedTask);

        System.out.println("\nScheduled Tasks (sorted by priority): ");
        scheduler.displayAllTasks();

        processedTask = scheduler.processNextTask();
        System.out.println("\nProcessing Task: " + processedTask);

        System.out.println("\nScheduled Tasks (sorted by priority): ");
        scheduler.displayAllTasks();

        processedTask = scheduler.processNextTask();
        System.out.println("\nProcessing Task: " + processedTask);

        System.out.println("\nScheduled Tasks (sorted by priority): ");
        scheduler.displayAllTasks();

        processedTask = scheduler.processNextTask();
        System.out.println("\nProcessing Task: " + processedTask);

        System.out.println("\nScheduled Tasks (sorted by priority): ");
        scheduler.displayAllTasks();
    }

    public static <T> List<T> combineAlternating(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>();

        int maxSize = Math.max(list1.size(), list2.size());

        for (int i = 0; i < maxSize; i++) {
            if (i < list1.size()) {
                result.add(list1.get(i));
            }

            if (i < list2.size()) {
                result.add(list2.get(i));
            }
        }

        return result;
    }

    public static <K, V> void printMap(Map<K, V> map) {
        if (map.size() == 0) {
            System.out.println("Карта пустая");
            return;
        }

        List<K> keys = new ArrayList<>(map.keySet());
        for (int i = 0; i < keys.size(); i++) {
            K key = keys.get(i);
            V value = map.get(key);
            System.out.println("Ключ: " + key + ", Значение: " + value);
        }
    }
}

class Task implements Comparable<Task> {
    private String taskName;
    private int priority; // Заменяем Integer на int
    private int duration; // Заменяем Integer на int

    public Task(String taskName, int priority, int duration) {
        this.taskName = taskName;
        this.priority = priority;
        this.duration = duration;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getPriority() {
        return priority;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Task other) {
        // Упрощаем сравнение без использования compareTo для примитивов
        if (other.priority > this.priority) {
            return 1;
        } else if (other.priority < this.priority) {
            return -1;
        } else {
            if (this.duration > other.duration) {
                return 1;
            } else if (this.duration < other.duration) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return "[Priority " + priority + "] " + taskName + " (Duration: " + duration + " mins)";
    }
}

class TaskScheduler {
    private PriorityQueue<Task> scheduledTasks;
    private Queue<Task> pendingTasks;

    public TaskScheduler() {
        scheduledTasks = new PriorityQueue<>();
        pendingTasks = new LinkedList<>();
    }

    public void addTask(Task task) {
        scheduledTasks.add(task);
    }

    public Task processNextTask() {
        if (scheduledTasks.size() != 0) {
            return scheduledTasks.poll();
        } else if (pendingTasks.size() != 0) {
            return pendingTasks.poll();
        }

        return null;
    }

    public void delayTask(String taskName) {
        List<Task> tempList = new ArrayList<>();
        Task foundTask = null;

        while (!scheduledTasks.isEmpty()) {
            Task task = scheduledTasks.poll();
            if (task.getTaskName().equals(taskName)) {
                foundTask = task;
            } else {
                tempList.add(task);
            }
        }

        for (Task task : tempList) {
            scheduledTasks.add(task);
        }

        if (foundTask != null) {
            pendingTasks.add(foundTask);
        }
    }

    public void displayAllTasks() {
        List<Task> tempScheduled = new ArrayList<>();
        List<Task> tempPending = new ArrayList<>();

        int count = 1;
        if (scheduledTasks.size() == 0) {
            System.out.println("(No priority tasks)");
        } else {
            while (scheduledTasks.size() != 0) {
                Task task = scheduledTasks.poll();
                System.out.println(count + ". " + task);
                tempScheduled.add(task);
                count++;
            }
        }

        System.out.println("Pending Tasks (FIFO Order): ");
        if (pendingTasks.size() == 0) {
            System.out.println("(No pending tasks)");
        } else {
            count = 1;
            while (!pendingTasks.isEmpty()) {
                Task task = pendingTasks.poll();
                System.out.println(count + ". " + task.getTaskName() +
                        " (Priority: " + task.getPriority() +
                        ", Duration: " + task.getDuration() + " mins)");
                tempPending.add(task);
                count++;
            }
        }

        for (Task task : tempScheduled) {
            scheduledTasks.add(task);
        }

        for (Task task : tempPending) {
            pendingTasks.add(task);
        }
    }
}
