/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package result.newpackage;

import dataArchive.Job;
import dataArchive.JobsContainer;
import dataArchive.Machine;
import dataArchive.Order;
import dataArchive.ProcessTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 117 [データ構造]最終的な出力情報の集約を目指すクラスです。
 *
 */
public class AnswerReportContainer implements I_AnswerReportContainer {

    private JobsContainer jobs;
    private Map<Job, Map<Order, ProcessTime>> orderStartTimeMap;
    private Map<Job, Integer> jobReadTimeMap;
    private int readTime;
    private Integer jobTotal;
    private Integer machineTotal;
    private String solutionMeans;

    //いづれもコンストラクタで初期化される見通し
    public AnswerReportContainer(JobsContainer jobs, Map<Job, Map<Order, ProcessTime>> orderStartTimeMap, Map<Job, Integer> jobReadTimeMap, int readTime,String asolutionMeans) {
        this.jobs = jobs;
        this.orderStartTimeMap = orderStartTimeMap;
        this.jobReadTimeMap = jobReadTimeMap;
        this.readTime = readTime;
        this.solutionMeans = asolutionMeans;

        this.jobTotal = jobs.getCountJob();
        this.machineTotal = jobs.getCountMachine();
        
        

    }

    @Override
    public JobsContainer getJobContainer() {
        return jobs;
    }

    @Override
    public int getReadTime() {
        return readTime;
    }

    @Override
    public Map<Order, ProcessTime> getStarttimeMap(Job job) {
        return orderStartTimeMap.get(job);
    }

    @Override
    public ProcessTime getStarttime(Job job, Order order) {
        Map<Order, ProcessTime> temptime = orderStartTimeMap.get(job);
        return temptime.get(order);
    }
    
    
            

    @Override
    public Integer getStarttimeInteger(Job job, Order order) {
        ProcessTime time = getStarttime(job, order);

        return time.get();
    }
    
    
    
    @Override
    public Integer getJobReadtime(Job job) {
        return jobReadTimeMap.get(job);
    }

    @Override
    public Integer getCountJob() {
        return jobTotal;
    }

    @Override
    public Integer getCountMachine() {
        return machineTotal;
    }

    @Override
    public Job getJob(Integer anindex) {
        return jobs.getJob(anindex);
    }

    @Override
    public List<Job> getJobList() {
        return jobs.getJobList();
    }

    @Override
    public List<Machine> getMachineList() {
        return jobs.getMachineList();
    }

    @Override
    public List<Order> getOrderList() {
        return jobs.getOrderList();
    }

    @Override
    public Machine getMachine(Integer anindex) {
        return jobs.getMachine(anindex);
    }

    @Override
    public ProcessTime getStarttime(Job job, Machine machine) {
            Map<Order, ProcessTime> temptime = orderStartTimeMap.get(job);
        Order searchedOrder = null;
        
        for (int i = 0; i < jobs.getOrderList().size(); i++) {
            if (machine.equals(job.getMachine(jobs.getOrderList().get(i)))) {
                searchedOrder = jobs.getOrderList().get(i);
            }
            
        }
        
        
        return temptime.get(searchedOrder);

    }

    @Override
    public Integer getStarttimeInteger(Job job, Machine machine) {
        
        ProcessTime time = getStarttime(job, machine);
        
        
        
        
        return time.get();
    }

 
    @Override
    public ProcessTime getEndtime(Job job, Order order) {
        ProcessTime starttime = getStarttime(job, order);
        ProcessTime worktime = job.OrderToTime(order);
        
        ProcessTime endtime = new ProcessTime(starttime.get()+worktime.get());
        return endtime;
    }

    @Override
    public ProcessTime getEndtime(Job job, Machine machine) {
        ProcessTime starttime = getStarttime(job, machine);
        
                
       Order searchedOrder = null;
        for (int i = 0; i < jobs.getOrderList().size(); i++) {
            if (machine.equals(job.getMachine(jobs.getOrderList().get(i)))) {
                searchedOrder = jobs.getOrderList().get(i);
            }
            
        }
        ProcessTime worktime = job.OrderToTime(searchedOrder);
        ProcessTime endtime = new ProcessTime(starttime.get()+worktime.get());
        return endtime;
        
    }

    @Override
    public Integer getEndtimeInteger(Job job, Order order) {
        return getEndtime(job, order).get();
    }

    @Override
    public Integer getEndtimeInteger(Job job, Machine machine) {
        return getEndtime(job, machine).get();
    }

    @Override
    public String getSolutionMeans() {
        return solutionMeans;
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
