package program.classes;

import program.Configuration;
import program.Controller;
import program.Main;
import program.util.NameGenerator;
import program.util.State;

import java.util.Comparator;
import java.util.Random;

public class Process
{
    private final int id;
    private final String name;
    private final int priority;
    private final int memoryUsage;
    private final int startTime;

    private int requiredTime;
    private int burstTime;
    private State state;
    private String interruptionReason = "";

    private MemoryBlock memoryBlock;

    private String resource = "";

    private Random random = new Random();

    public Process(String name)
    {
        this.name = name;
        this.id = Main.getTaskScheduler().getLastId();
        Main.getTaskScheduler().incrementLastId();
        this.startTime = Main.getSystemTime();

        requiredTime = random.nextInt(90) + 10;
        memoryUsage = random.nextInt(Configuration.PROCESS_MAX_MEMORY_USAGE - Configuration.PROCESS_MIN_MEMORY_USAGE) + Configuration.PROCESS_MIN_MEMORY_USAGE;
        priority = random.nextInt(Configuration.PROCESS_MAX_PRIORITY - 1) + 1;

        burstTime = 0;
        state = State.READY;
    }

    public Process() { this(NameGenerator.generate()); }

    public void increaseBurstTime(int ticks)
    {
        burstTime += ticks;
    }

    public void increaseRequiredTime(int ticks)
    {
        this.requiredTime += ticks;
    }


    /*----SETTERS----*/

    public void setState(State state)
    {
        if(state == State.FINISHED || state == State.TERMINATED)
        {
            Main.getTaskScheduler().freeMemoryBlock(memoryBlock);
            Main.getTaskScheduler().addProcessToCompleted(this);
        }
        else if(state == State.RUNNING || state == State.READY)
        {
            Main.guiController.updateTable(Controller.Tables.RUNNING);
        }

        this.state = state;
    }

    public void setLocationInMemory(MemoryBlock memoryBlock) { this.memoryBlock = memoryBlock; }

    public void setInterruptionReason(String value)
    {
        this.interruptionReason = value;
    }

    public void setResource(String resourceName) { resource = resourceName; }


    /*----GETTERS----*/

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getPriority()
    {
        return priority;
    }

    public int getMemoryUsage() { return memoryUsage; }

    public int getTimeRequired()
    {
        return requiredTime;
    }

    public int getBurstTime() { return burstTime; }

    public State getState()
    {
        return state;
    }

    public String getResource() { return resource; }

    public static Comparator<Process> byEnd = Comparator.comparing(o -> o.id);
}
