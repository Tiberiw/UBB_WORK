package map;


import map.domain.MessageTask;
import map.domain.SortingTask;
import map.domain.Task;
import map.factory.Strategy;
import map.runner.DelayTaskRunner;
import map.runner.PrinterTaskRunner;
import map.runner.StrategyTaskRunner;
import map.runner.TaskRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void test1() {
        ArrayList<Task> list = new ArrayList<>();

        MessageTask t1 = new MessageTask("1", "Ceva1", "Mesaj1", "Ioana", "Andrei", LocalDateTime.now());
        MessageTask t2 = new MessageTask("2", "Ceva2", "Mesaj2", "Andrei", "Ioana", LocalDateTime.now());
        MessageTask t3 = new MessageTask("3", "Ceva3", "Mesaj3", "Gigel", "Floricel", LocalDateTime.now());
        MessageTask t4 = new MessageTask("4", "Ceva4", "Mesaj4", "Floricel", "Gigel", LocalDateTime.now());
        MessageTask t5 = new MessageTask("5", "Ceva5", "Mesaj5", "Me", "Me", LocalDateTime.now());

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        for( Task t : list) {
            System.out.println(t.toString());
        }
    }
    public static void test2(Strategy strategy) {

        MessageTask t1 = new MessageTask("1", "Ceva1", "Mesaj1", "Ioana", "Andrei", LocalDateTime.now());
        MessageTask t2 = new MessageTask("2", "Ceva2", "Mesaj2", "Andrei", "Ioana", LocalDateTime.now());
        MessageTask t3 = new MessageTask("3", "Ceva3", "Mesaj3", "Gigel", "Floricel", LocalDateTime.now());
        MessageTask t4 = new MessageTask("4", "Ceva4", "Mesaj4", "Floricel", "Gigel", LocalDateTime.now());
        MessageTask t5 = new MessageTask("5", "Ceva5", "Mesaj5", "Me", "Me", LocalDateTime.now());

        TaskRunner runner = new StrategyTaskRunner(strategy);

        runner.addTask(t1);
        runner.addTask(t2);
        runner.addTask(t3);
        runner.addTask(t4);
        runner.addTask(t5);

        runner.executeAll();
    }

    public static void test3(Strategy strategy) {

        MessageTask t1 = new MessageTask("1", "Ceva1", "Mesaj1", "Ioana", "Andrei", LocalDateTime.now());
        MessageTask t2 = new MessageTask("2", "Ceva2", "Mesaj2", "Andrei", "Ioana", LocalDateTime.now());
        MessageTask t3 = new MessageTask("3", "Ceva3", "Mesaj3", "Gigel", "Floricel", LocalDateTime.now());
        MessageTask t4 = new MessageTask("4", "Ceva4", "Mesaj4", "Floricel", "Gigel", LocalDateTime.now());
        MessageTask t5 = new MessageTask("5", "Ceva5", "Mesaj5", "Me", "Me", LocalDateTime.now());

        TaskRunner runner = new PrinterTaskRunner( new StrategyTaskRunner(strategy));

        runner.addTask(t1);
        runner.addTask(t2);
        runner.addTask(t3);
        runner.addTask(t4);
        runner.addTask(t5);

        //runner.executeOneTask();
        runner.executeAll();

    }

    public static void test4(Strategy strategy) {

        MessageTask t1 = new MessageTask("1", "Ceva1", "Mesaj1", "Ioana", "Andrei", LocalDateTime.now());
        MessageTask t2 = new MessageTask("2", "Ceva2", "Mesaj2", "Andrei", "Ioana", LocalDateTime.now());
        MessageTask t3 = new MessageTask("3", "Ceva3", "Mesaj3", "Gigel", "Floricel", LocalDateTime.now());
        MessageTask t4 = new MessageTask("4", "Ceva4", "Mesaj4", "Floricel", "Gigel", LocalDateTime.now());
        MessageTask t5 = new MessageTask("5", "Ceva5", "Mesaj5", "Me", "Me", LocalDateTime.now());

        TaskRunner runner = new PrinterTaskRunner(new DelayTaskRunner( new StrategyTaskRunner(strategy)));

        runner.addTask(t1);
        runner.addTask(t2);
        runner.addTask(t3);
        runner.addTask(t4);
        runner.addTask(t5);

        //runner.executeOneTask();
        runner.executeAll();

    }
    public static void main(String[] args) {


//        System.out.println("Test1");
//        test1();
//        System.out.println("Test2");
//        test2(Strategy.valueOf(args[0]));
//        System.out.println("Test3");
//        test3(Strategy.valueOf(args[0]));
        System.out.println("Test4");
        test4(Strategy.valueOf(args[0]));
    }
}