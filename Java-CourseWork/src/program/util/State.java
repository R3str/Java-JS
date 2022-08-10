package program.util;

public enum State {
    READY,      //waiting for CPU
    WAITING,    //waiting for resource
    RUNNING,    //processing
    FINISHED,   //completed
    TERMINATED  //aborted
}
