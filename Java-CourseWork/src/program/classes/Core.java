package program.classes;

import program.Configuration;
import program.Controller;
import program.Main;
import program.util.ITickable;
import program.util.State;

import java.util.Random;

public class Core implements ITickable
{
    private CPU parent;

    private boolean busy = false;
    private Process currentProcess;

    private Random random = new Random();

    public Core(CPU parent)
    {
        this.parent = parent;
    }

    public void runProcess(Process process)
    {
        currentProcess = process;
        currentProcess.setResource("CPU");
        currentProcess.setState(State.RUNNING);
        busy = true;
        Main.guiController.updateTable(Controller.Tables.RESOURCES);
    }

    private void finishProcess()
    {
        if(currentProcess.getBurstTime() < currentProcess.getTimeRequired())
        {
            currentProcess.setState(State.TERMINATED);
        }
        else
            currentProcess.setState(State.FINISHED);

        this.currentProcess = null;
        busy = false;
    }

    public void finishProcess(String reason)
    {
        currentProcess.setInterruptionReason(reason);
        currentProcess.setResource("");
        finishProcess();
    }

    public void supplantProcess(Process newProcess)
    {
        currentProcess.setState(State.READY);
        Main.getTaskScheduler().scheduleTask(currentProcess);

        runProcess(newProcess);
    }

    public Process getRunningProcess()
    {
        return currentProcess;
    }

    @Override
    public void tick(int currentTime)
    {
        if(currentProcess == null) return;
        if(busy)
        {
            currentProcess.increaseBurstTime(1);

            //sending process to resource
            int percent = Math.round(currentProcess.getTimeRequired() * 0.01f);
            if(currentProcess.getBurstTime() > percent*10 + 5)
            {
                if (random.nextInt(currentProcess.getBurstTime()) < percent*4)
                {
                    Resource r = Main.getSystemResources().get(random.nextInt(Configuration.getResourcesCount()));

                    r.addProcess(currentProcess);
                    this.currentProcess = null;
                    busy = false;
                    return;
                }
            }

            //error simulation
            if(Configuration.runtimeErrorsEnabled() && random.nextInt(Configuration.getProcessTerminationChance()) == 0)
            {
                simulateException();
                return;
            }

            //change process status on 'Completed' if it is completed
            if(currentProcess.getTimeRequired() <= currentProcess.getBurstTime())
            {
                finishProcess("Completed.");
            }

            Main.guiController.updateTable(Controller.Tables.RESOURCES);
        }
    }

    public void simulateException()
    {
        finishProcess("Runtime Error (CPU)");
    }

    public boolean isBusy()
    {
        return busy;
    }
}
