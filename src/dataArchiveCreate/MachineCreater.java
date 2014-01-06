/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchiveCreate;

import dataArchive.Job;
import dataArchive.Machine;
import dataArchive.ProcessTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 117
 */
public class MachineCreater {
    
    private List<Job> joblist;
    private Integer jobTotal;
    private Integer machineTotal;
    private List<List<Integer>> jobMachineProcessTimeList;
    private List<String> machineNamesList;
    
    protected List<Machine> returnMachineList(){
        
        
        
        Integer machineCount = 1;
        List<Machine> machineList = new ArrayList<>();
        
        
        //以下の動作を全マシン分行う。最終的にマシンが全部入ったmachineListができる。
        
        for (int machineIndex = 0; machineIndex < machineTotal; machineIndex++) {
            
            /*ジョブ/ProcessTimeのハッシュマップを作る。マシン一つ分のsetTimeMap.
             * 全ジョブ分登録してハッシュマップの情報を完全に。
             * マシンを作る。マシンリストに入れる。
             */
            
            
            Map<Integer,ProcessTime> setTimeMap;
            setTimeMap = new HashMap<>();
            
            
            for (int jobIndex = 0; jobIndex < jobTotal; jobIndex++) {
                List<Integer> machineTimeList;
                machineTimeList = jobMachineProcessTimeList.get(jobIndex);
                
                Integer Time;
                Time = machineTimeList.get(machineIndex);
                
                ProcessTime processTime;
                processTime = new ProcessTime(Time);
                
                Job job = joblist.get(jobIndex);
                Integer jobID = job.getID();
                
                setTimeMap.put(jobID, processTime);
            }
            Machine machine = new Machine(setTimeMap,"Machine"+machineCount);
            
            machine.setViewName(machineNamesList.get(machineIndex));
            machineCount +=1;
            
            machineList.add(machine);
            
        }
        
        return machineList;
    }

    public MachineCreater(List<Job> joblist, Integer jobTotal, Integer machineTotal, List<List<Integer>> jobMachineProcessTimeList,List<String>machineNamesList) {
        this.joblist = joblist;
        this.jobTotal = jobTotal;
        this.machineTotal = machineTotal;
        this.jobMachineProcessTimeList = jobMachineProcessTimeList;
        this.machineNamesList = machineNamesList;
    }

    
    
}
