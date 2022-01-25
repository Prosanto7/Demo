
import java.util.ArrayList;
import java.util.Scanner;


class Process
{
    private String processName;
    private int priority,burst,arrival,turnaroundTime,waitingTime;

    
    /**
     * This method sets Process Name
     * @param processName Process Name from user input
     */
    public void setProcessName(String processName)
    {
        this.processName = processName;
    }

    /**
     *  This method returns Process Name
     *  @return processName
     */
    public String getProcessName() 
    {
        return processName;
    }

    /**
     * This method sets Process Priority
     * @param priority Process Priority from user input
     */
    public void setPriority(int priority) 
    {
        this.priority = priority;
    }

    /**
     *  This method returns Process Priority
     *  @return priority
     */
    public int getPriority() 
    {
        return priority;
    }

    /**
     * This method sets Process Burst Time
     * @param burst Process Burst Time from user input
     */
    public void setBurst(int burst) 
    {
        this.burst = burst;
    }

    /**
     *  This method returns Process Burst Time
     *  @return burst
     */
    public int getBurst() 
    {
        return burst;
    }

    /**
     * This method sets Process Arrival Time
     * @param arrival Process Arrival Time from user input
     */
    public void setArrival(int arrival)
    {
        this.arrival = arrival;
    }

    /**
     *  This method returns Process Arrival Time
     *  @return arrival
     */
    public int getArrival()
    {
        return arrival;
    }
    
    /**
     * This method sets Process Turnaround Time
     * @param turnaroundTime Process Turnaround Time
     */
    public void setTurnaroundTime(int turnaroundTime) 
    {
        this.turnaroundTime = turnaroundTime;
    }

    /**
     *  This method returns Process Turnaround Time
     *  @return turnaroundTime
     */    
    public int getTurnaroundTime() 
    {
        return turnaroundTime;
    }

    /**
     * This method sets Process Waiting Time
     * @param waitingTime Process Waiting Time
     */ 
    public void setWaitingTime(int waitingTime) 
    {
        this.waitingTime = waitingTime;
    }
    
    /**
     *  This method returns Process Waiting Time
     *  @return waitingTime
     */ 
    public int getWaitingTime() 
    {
        return waitingTime;
    }


}

class SchedulingAlgorithm
{
    public void showDetails(ArrayList<Process> processArrayList)
    {
        int time = 0;
        
        System.out.print("\n0");

        for(int i=0; i<processArrayList.size();i++)
        {
            for(int j=0;j<processArrayList.get(i).getBurst();j++)
            {
                System.out.print("-"); 
                time++;
            }

            processArrayList.get(i).setTurnaroundTime(time);
            processArrayList.get(i).setWaitingTime(time-processArrayList.get(i).getBurst());

            System.out.print(time+"("+processArrayList.get(i).getProcessName()+")");
        }

        System.out.println("\n");

        int turnAroundTimeSum = 0,waitingTimeSum = 0;

        for(int i=0; i<processArrayList.size();i++)
        {
            System.out.println(processArrayList.get(i).getProcessName()+" Turnaround Time "+processArrayList.get(i).getTurnaroundTime()+", Waiting Time "+processArrayList.get(i).getWaitingTime());
            turnAroundTimeSum = turnAroundTimeSum + processArrayList.get(i).getTurnaroundTime();
            waitingTimeSum = waitingTimeSum + processArrayList.get(i).getWaitingTime();
        }
        
        System.out.println("Avarage Turnaround Time "+turnAroundTimeSum/processArrayList.size());
        System.out.println("Avarage Waiting Time "+waitingTimeSum/processArrayList.size()); 
    }

    public void usingFCFS(ArrayList<Process> processArrayList)
    {
        // Bubble Sort Regarding Arrival Time
        for(int i = 0; i < processArrayList.size()-1;i++)
        {
            for(int j = 0; j < processArrayList.size()-1;j++)
            {
                if(processArrayList.get(j).getArrival()>processArrayList.get(j+1).getArrival())
                {
                    Process tempProcess = processArrayList.get(j);
                    processArrayList.set(j, processArrayList.get(j+1));
                    processArrayList.set(j+1, tempProcess);
                }
            }
        }
    
        showDetails(processArrayList);
    }

    public void usingSJF(ArrayList<Process> processArrayList)
    {
        // Bubble Sort Regarding Burst Time
        for(int i = 0; i < processArrayList.size()-1;i++)
        {
            for(int j = 0; j < processArrayList.size()-1;j++)
            {
                if(processArrayList.get(j).getBurst()>processArrayList.get(j+1).getBurst())
                {
                    Process tempProcess = processArrayList.get(j);
                    processArrayList.set(j, processArrayList.get(j+1));
                    processArrayList.set(j+1, tempProcess);
               }
            }
        }

        showDetails(processArrayList);
    }

    public void usingPriority(ArrayList<Process> processArrayList)
    {
        // Bubble Sort Regarding Priority
        for(int i = 0; i < processArrayList.size()-1;i++)
        {
            for(int j = 0; j < processArrayList.size()-1;j++)
            {
                if(processArrayList.get(j).getPriority()>processArrayList.get(j+1).getPriority())
                {
                    Process tempProcess = processArrayList.get(j);
                    processArrayList.set(j, processArrayList.get(j+1));
                    processArrayList.set(j+1, tempProcess);
                }
            }
        }
    
        showDetails(processArrayList);
    }

    public void usingRR(ArrayList<Process> processArrayList, int quantum)
    {
        int burstTime = 0;

        // Bubble Sort Regarding Arrival Time
        for(int i = 0; i < processArrayList.size();i++)
        {
            for(int j = 0; j < processArrayList.size()-1;j++)
            {
                if(processArrayList.get(j).getArrival()>processArrayList.get(j+1).getArrival())
                {
                    Process tempProcess = processArrayList.get(j);
                    processArrayList.set(j, processArrayList.get(j+1));
                    processArrayList.set(j+1, tempProcess);
                }
            }
  
            burstTime = burstTime + processArrayList.get(i).getBurst();  // Getting Total Burst Time  
        }
    
        int time = 0;
        
        System.out.print("\n0");

        while(time<burstTime)
        {
            for(int i = 0; i < processArrayList.size();i++)
            {
                for(int j = 0; j < quantum;j++)
                {
                    System.out.print("-");
                    time++;
                }
                System.out.print(time+"("+processArrayList.get(i).getProcessName()+")");
            }
        }

        System.out.println("\n");

        for(int i=0; i<processArrayList.size();i++)
        {
            System.out.println(processArrayList.get(i).getProcessName()+" Turnaround Time "+processArrayList.get(i).getTurnaroundTime()+", Waiting Time "+processArrayList.get(i).getWaitingTime());
        }
    }
}

public class LABPRACTICE 
{
    /**
     * This method shows error message if any invalid input is provided and give valid input format
     *     
     */
    public void showError()
    {
        System.out.printf("\n\t\t***INVALID INPUT FORMAT***");
        System.out.println("VALID FORMAT : Process<space>Priority<space>Burst<space>Arrival");
    }

    /**
     * This is the main function and we will pass values using command line 
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner inputScanner = new Scanner(System.in);
        
        ArrayList<Process> processArrayList = new ArrayList<>();         

        while(inputScanner.hasNext())
        {
            Process process = new Process();

            process.setProcessName(inputScanner.next());
            process.setPriority(Integer.parseInt(inputScanner.next()));
            process.setBurst(Integer.parseInt(inputScanner.next()));
            process.setArrival(Integer.parseInt(inputScanner.next()));
            processArrayList.add(process);
        } 

        inputScanner.close();

        System.out.println("Process Priority Burst Arrival");
        for(int i=0;i<processArrayList.size();i++)
        {
            System.out.println(processArrayList.get(i).getProcessName()+" "+processArrayList.get(i).getPriority()+" "+processArrayList.get(i).getBurst()+" "+processArrayList.get(i).getArrival());
        }    

        SchedulingAlgorithm algorithms = new SchedulingAlgorithm();
        
        System.out.println("\n\tUsing First Come First Serve");
        algorithms.usingFCFS(processArrayList);
        System.out.println("\n\tUsing Shortest Job First");
        algorithms.usingSJF(processArrayList);
        System.out.println("\n\tUsing Priority");
        algorithms.usingPriority(processArrayList);
        System.out.println("\n\tUsing Round Robin");
        algorithms.usingRR(processArrayList, 1);
        
    }
}



/*
|||||||       |||||||              ||||||             |||||||||             ||             ||            ||  ||||||||||||||||||||||       ||||||      
||     ||     ||     ||         ||||    ||||        ||                     || ||           ||||          ||            ||              ||||    ||||
||      ||    ||      ||       |||        |||      ||                    ||     ||         || ||         ||            ||             |||        |||
||       ||   ||       ||     ||            ||    ||                    ||       ||        ||  ||        ||            ||            ||            ||
||       ||   ||       ||    ||              ||   ||                   ||         ||       ||   ||       ||            ||           ||              ||
||      ||    ||      ||    ||                ||    |||               ||           ||      ||    ||      ||            ||          ||                ||
||     ||     ||     ||     ||                ||       |||          ||               ||    ||     ||     ||            ||          ||                ||
|||||||       ||||||        ||                ||          |||      |||||||||||||||||||||   ||      ||    ||            ||          ||                || 
||            ||   ||       ||                ||             ||    ||                 ||   ||       ||   ||            ||          ||                || 
||            ||    ||       ||              ||               ||   ||                 ||   ||        ||  ||            ||           ||              || 
||            ||     ||        |||        |||                 ||   ||                 ||   ||         || ||            ||             |||        |||  
||            ||      ||        ||||    ||||                 ||    ||                 ||   ||          ||||            ||              ||||    |||| 
||            ||       ||          ||||||           |||||||||      ||                 ||   ||            ||            ||                ||||||      
*/