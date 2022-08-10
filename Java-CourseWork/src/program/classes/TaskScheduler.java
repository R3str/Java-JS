package program.classes;

import program.Configuration;
import program.Controller;
import program.Main;
import program.util.ITickable;
import program.util.State;

import java.util.ArrayList;
import java.util.Random;

public class TaskScheduler implements ITickable
{
    private CPU cpu;
    private MemoryScheduler memoryScheduler;

    private Queue processQueue;
    private ArrayList<Process> completedList;
    private ArrayList<Process> rejectsList;

    private Random random = new Random();

    private int lastId = 1;

    private int tasksFinished = 0;
    private int tasksRejected = 0;

    public TaskScheduler(CPU cpu, int systemMemory)
    {
        this.cpu = cpu;
        memoryScheduler = new MemoryScheduler(systemMemory);

        processQueue = new Queue();
        completedList = new ArrayList<>();
        rejectsList = new ArrayList<>();

        memoryScheduler.fillMemoryBlock(Configuration.OS_MEMORY_USAGE);
    }

    @Override
    public void tick(int currentTime)
    {
        Process nextProcess = processQueue.getHighestPriorityProcess();
        if(nextProcess != null)
        {
            if (cpu.hasFreeCore())
            {
                processQueue.removeProcess(nextProcess);
                cpu.runProcess(nextProcess);
            }
            else
            {
                Core lowestPriorityCore = cpu.getCore(cpu.getLowestPriorityIndex());
                if(nextProcess.getPriority() < lowestPriorityCore.getRunningProcess().getPriority())
                {
                    processQueue.removeProcess(nextProcess);
                    lowestPriorityCore.supplantProcess(nextProcess);
                }
            }
        }

        //random task scheduling
        if(Configuration.randomProcessGenerationEnabled())
        {
            if (random.nextInt(10) == 0)
            {
                scheduleRandom();
            }
        }
    }

    public void scheduleTask(String name)
    {
        Process task = new Process(name);
        scheduleTask(task);
    }

    public boolean scheduleTask(Process task)
    {
        //re-add the task from resource queue to cpu queue
        if(task.getState() == State.WAITING)
        {
            int additionalTime = task.getTimeRequired() - task.getBurstTime();
            additionalTime = Math.floorDiv(additionalTime, 100) * random.nextInt(16) + 5;
            task.increaseRequiredTime(additionalTime);
            task.setInterruptionReason("");

            processQueue.addProcess(task);
            task.setState(State.READY);
            task.setResource("");
            Main.guiController.updateCPUQueue();

            return true;
        }
        else if(task.getState() == State.READY)
        {
            if(task.getBurstTime() > 0)
            {
                processQueue.addProcess(task);
                Main.guiController.updateCPUQueue();

                return true;
            }
        }

        MemoryBlock memory = memoryScheduler.fillMemoryBlock(task.getMemoryUsage());
        if(memory == null)
        {
            addRejectProcess(task);
            return false;
        }

        task.setLocationInMemory(memory);
        processQueue.addProcess(task);
        task.setState(State.READY);

        return true;
    }

    public void scheduleRandom()
    {
        Process task = new Process();
        scheduleTask(task);
    }

    public void freeMemoryBlock(MemoryBlock block)
    {
        memoryScheduler.releaseMemoryBlock(block);
    }

    public void addProcessToCompleted(Process process)
    {
        completedList.add(process);
        tasksFinished++;
        Main.guiController.updateTable(Controller.Tables.FINISHED);
        Main.guiController.updateTasksFinished();
    }

    public void addRejectProcess(Process process)
    {
        rejectsList.add(process);
        tasksRejected++;
        Main.guiController.updateTable(Controller.Tables.REJECTED);
        Main.guiController.updateTasksRejected();
    }

    public void finishWork()
    {
        for (Resource r : Main.getSystemResources())
        {
            r.finishWork();
        }
        processQueue.clear();
        cpu.finishWork();
    }

    public ArrayList<Process> getCPUTaskList()
    {
        ArrayList<Process> result = new ArrayList<>();
        result.addAll(cpu.getCoresContent());
        result.addAll(processQueue.getList());

        int tick = Main.getSystemTime();
        int Ordering = Configuration.getOrderingCount();
        if(tick != 0 && tick % Ordering == 0)
            result.sort(Process.byEnd);

        return result;
    }

    public ArrayList<Process> getResourceTaskList(int resourceIndex)
    {
        return Main.getSystemResources().get(resourceIndex).getTaskList();
    }

    public ArrayList<Process> getRejectsList()
    {
        return rejectsList;
    }

    public ArrayList<Process> getCompletedList()
    {
        return completedList;
    }

    public ArrayList<Process> getResourcesContent()
    {
        ArrayList<Process> result = new ArrayList<>();
        result.addAll(cpu.getCoresContent());

        for (Resource r : Main.getSystemResources())
        {
            result.add(r.getCurrentTask());
        }

        return result;
    }

    public int getLastId()
    {
        return lastId;
    }

    public void incrementLastId()
    {
        lastId++;
        Main.guiController.updateTasksTotal();
    }

    /*----data for tab 'Settings and statistics'----*/
    public int getTasksFinished() { return tasksFinished; }
    public int getTasksRejected() { return tasksRejected; }
    public int getQueueLength() { return processQueue.getList().size(); }
    public int getCPUInactivity() { return cpu.getInactivityTicks(); }
}
