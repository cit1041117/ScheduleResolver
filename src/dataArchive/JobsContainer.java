/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchive;

import java.util.List;

/**
 *
 * @author 117
 */
public interface JobsContainer {

    Integer getCountJob();

    Integer getCountMachine();

    Job getJob(Integer anindex);

    Machine getMachine(Integer anindex);

    List<Job> getJobList();

    List<Machine> getMachineList();

    List<Order> getOrderList();

    Order getOrder(Job job, Machine machine);

    ProcessTime getOrderProcessTime(Job job, Order order);

    ProcessTime getMachineProcessTime(Job job, Machine machine);
}
