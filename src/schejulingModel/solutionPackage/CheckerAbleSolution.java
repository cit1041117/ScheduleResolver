/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage;

import dataArchive.Job;
import dataArchive.JobsContainer;
import dataArchive.Machine;
import dataArchive.Order;
import java.util.Map;

/**
 * 入力された
 *
 * @author 117
 */
public class CheckerAbleSolution {

    //ジョブはこの数以下に収まる。
    int limitJobCount;
    //マシンはこの数以下に収まる。
    int limitMachineCount;
    //フローショップスケジューリングか否か。どちらでもいい場合はfalse
    boolean isFlow;

    public CheckerAbleSolution(int limitJobCount, int limitMachineCount, boolean isFlow) {
        this.limitJobCount = limitJobCount;
        this.limitMachineCount = limitMachineCount;
        this.isFlow = isFlow;
    }

    public CheckerAbleSolution(boolean isFlow) {
        this.limitJobCount = Integer.MAX_VALUE;
        this.limitMachineCount = Integer.MAX_VALUE;
    }

    public CheckerAbleSolution(int limitJobCount, int limitMachineCount) {
        this.limitJobCount = limitJobCount;
        this.limitMachineCount = limitMachineCount;
        isFlow = false;
    }

    /**
     * ジョブとマシンの数が規定に沿っているかどうか、フローショップスケジューリングを要求する場合
     * 全てのジョブの処理順番が等しいか判別します。
     * @param jobsContainer
     * @return 
     */
    public boolean checkAbleSolution(JobsContainer jobsContainer) {
        if (rightCount(jobsContainer)
                && rightShop(jobsContainer)) {
            return true;
        }

        return false;

    }

    private boolean rightCount(JobsContainer jobsContainer) {
        int jobCount = jobsContainer.getCountJob();
        int machineCount = jobsContainer.getCountMachine();

        if (jobCount > limitJobCount) {
            return false;
        }
        if (machineCount > limitMachineCount) {
            return false;
        }
        return true;
    }

    private boolean rightShop(JobsContainer jobsContainer) {
        
        //ジョブショップスケジューリングか否かを精査する

        if (isFlow) {
            Job firstJob;
            Map<Order, Machine> targetMap;

            firstJob = jobsContainer.getJob(0);
            targetMap = firstJob.getOrderMachineMap();
            
            

            for (Job job : jobsContainer.getJobList()) {
                Boolean isMapSame = jadgeEqual(targetMap, job.getOrderMachineMap());

                if (isMapSame == false) {
                    return false;
                }
            }


        }
        return true;
    }

    private boolean jadgeEqual(Map<Order, Machine> targetMap, Map<Order, Machine> selectMap) {
        return targetMap.equals(selectMap);

    }
}
