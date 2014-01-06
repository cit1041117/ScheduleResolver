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
 * @author 117
 */
public interface I_AnswerReportContainer extends JobsContainer {

    @Override
    Integer getCountJob();

    @Override
    Integer getCountMachine();

    @Override
    Job getJob(Integer anindex);

    JobsContainer getJobContainer();

    @Override
    List<Job> getJobList();

    Integer getJobReadtime(Job job);

    @Override
    Machine getMachine(Integer anindex);

    @Override
    List<Machine> getMachineList();

    @Override
    List<Order> getOrderList();

    String getSolutionMeans();
    
    int getReadTime();

    ProcessTime getStarttime(Job job, Order order);
    
    ProcessTime getStarttime(Job job, Machine machine);

    Integer getStarttimeInteger(Job job, Order order);
    
    Integer getStarttimeInteger(Job job, Machine machine);
    


    Map<Order, ProcessTime> getStarttimeMap(Job job);
    
    ProcessTime getEndtime(Job job, Order order);
    
    ProcessTime getEndtime(Job job, Machine machine);

    Integer getEndtimeInteger(Job job, Order order);
    
    Integer getEndtimeInteger(Job job, Machine machine);    
    
}
