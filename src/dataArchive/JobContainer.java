/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchive;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 117
 * [データ構造]ジョブの一覧を提供します。取得インデックスは0からです。
 */
public class JobContainer implements JobsContainer {
    private List<Job> jobList;
    private List<Machine> machineList;

    @Override
    public List<Machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(List<Machine> machineList) {
        this.machineList = machineList;
    }
     

    
    public JobContainer(List<Job> anjobList,List<Machine> anmachineList) {
        this.jobList = anjobList;
        this.machineList = anmachineList;
    }
    
    
    @Override
    public List<Job> getJobList() {
        return jobList;
    }
    
    @Override
    public Job getJob(Integer anindex){
        try {
            jobList.get(anindex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ジョブのインデクス範囲外呼び出し:getJob");
        }
        return jobList.get(anindex);
    }
    
    @Override
    public Integer getCountJob(){
        return jobList.size();
    }
    
    @Override
    public Integer getCountMachine(){
        return machineList.size();
    }

    @Override
    public List<Order> getOrderList() {
        List<Order> orderList = new ArrayList<>();
        for (int i = 1; i <= machineList.size(); i++) {
            Order order = new Order(i);
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Machine getMachine(Integer anindex) {
        try {
            machineList.get(anindex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("マシンのインデクス範囲外呼び出し:getMachine");
        }
        return machineList.get(anindex);
    }
    
    
    @Override
    public Order getOrder(Job job,Machine machine){
        Map<Order,Machine> tempMap = job.getOrderMachineMap();
        
        for (Order order : getOrderList()) {
            Machine surveyMachine = tempMap.get(order);
            
            if (surveyMachine.equals(machine)) {
                return order;
            }
        }
        
        assert false:"オーダーの割り当てられていないマシンがあります";
        return null;
    }

    @Override
   public  ProcessTime getOrderProcessTime(Job job,Order order){
       ProcessTime time = job.OrderToTime(order);
       return time;
   }
   
    @Override
   public ProcessTime getMachineProcessTime(Job job,Machine machine){
       Order order = getOrder(job, machine);
       ProcessTime time = getOrderProcessTime(job, order);
       return time;
   }
    
    
    
    
    
}
