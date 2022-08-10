package program.classes;

import program.util.State;

import java.util.ArrayList;

public class Queue
{
    private ArrayList<Process> queue;

    public Queue()
    {
        queue = new ArrayList<>();
    }

    public Process getFirstProcess()
    {
        if(queue.isEmpty()) return null;
        return queue.get(0);
    }

    public Process getHighestPriorityProcess()
    {
        if(queue.isEmpty()) return null;
        int highestPriorityIndex = 0;
        for (int i = 1; i < queue.size(); i++)
        {
            if(queue.get(i).getPriority() < queue.get(highestPriorityIndex).getPriority())
                highestPriorityIndex = i;
        }
        return queue.get(highestPriorityIndex);
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public void clear()
    {
        for (Process item : queue)
        {
            item.setState(State.TERMINATED);
        }
        queue = new ArrayList<>();
    }

    public void addProcess(Process process)
    {
        queue.add(process);
    }

    public boolean removeProcess(Process process)
    {
        return queue.remove(process);
    }

    public ArrayList<Process> getList()
    {
        return queue;
    }
}
