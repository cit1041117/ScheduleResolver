/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch;

import dataArchive.Job;
import dataArchive.Machine;
import dataArchive.Order;
import dataArchive.ProcessTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ディスパッチング解法でスケジュールを組むために必要なステータスを
 * ジョブに提供するラッパです。 ジョブとラッパは１対１対応です。
 *
 * @author 117
 */
public class DispatchJobWlapper {

    private Job job;
    private boolean isGoalJob;
    private boolean isFree;
    private Integer processSleep;
    private Order nextOrder;
    private Map<Order, ProcessTime> startProcessTimeList;
    private MachineStateBoard stateBoard;

    public DispatchJobWlapper(Job anjob,MachineStateBoard anstateBoard) {
        this.job = anjob;
        isGoalJob = false;
        isFree = true;
        processSleep = 0;
        nextOrder = new Order(1);
        startProcessTimeList = new HashMap<>();
        this.stateBoard = anstateBoard;
    }

    public Job getJob() {
        return job;
    }
    

    public Integer getLastProcessSleepTime() {
        return processSleep;
    }

    public Order nextOrder() {
        return nextOrder;
    }

    public Map<Order, ProcessTime> getStartProcessTimeList() {
        return startProcessTimeList;
    }

    public boolean isIsGoalJob() {
        return isGoalJob;
    }

    public boolean isIsFree() {
        return isFree;
    }
    
    public ProcessTime OrderToTime(Order order){
        return job.OrderToTime(order);
    }
    
    public Machine OrderToMachine(Order order){
        return  job.getMachine(order);
    }
    
    public Integer nextOrderTime(){
        ProcessTime time = job.OrderToTime(nextOrder);
        return time.get();
    } 
    
    public Machine nextOrderMachine(){
        return  job.getMachine(nextOrder);
    }
    
        public Integer getTheTimeOfDelivery() {
        return job.getTheTimeOfDelivery();
    }
        
    public Order getFinalOrder(){
        //Orderは１からスタート
        Integer finalOrder = job.getOrderMachineMap().size();
        Order order = new Order(finalOrder);
        
        assert job.getOrderMachineMap().containsKey(order):"OrderMachineMapの範囲外です。";
        
        return order;
    }
    
    public Integer getFinalOrderInteger(){
         //Orderは１からスタート
        Integer finalOrder = job.getOrderMachineMap().size();
        Order order = new Order(finalOrder);
        
        assert job.getOrderMachineMap().containsKey(order):"OrderMachineMapの範囲外です。";
        
        return order.get();
    }
    
    public boolean isNextMachineFree(){
        if(stateBoard.getMachineState(nextOrderMachine()).equals("Free")){
            return true;
        }
        
        return false;
    }
    

    /**
     * マシン起動。現在時間を開始時間リストにマシンと共に記述 マシンの終了時間を設定
     *
     * @param nowtime
     */
    public void Start(Integer nowtime) {
        isFree = false;

        
        startProcessTimeList.put(nextOrder, new ProcessTime(nowtime));

        ProcessTime sleeptime = job.OrderToTime(nextOrder);
        processSleep = nowtime + sleeptime.get();
        stateBoard.StateOn(nextOrderMachine());
    }

    public void Stop() {
        isFree = true;
        
        stateBoard.StateOff(nextOrderMachine());

        Integer current = nextOrder.get();
        nextOrder = new Order(current + 1);

        if (job.getOrderMachineMap().containsKey(nextOrder)) {
        } else {
            isGoalJob = true;
        }

    }
    
}
