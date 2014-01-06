/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *
 * 
 * @author 117
 */
public class MOD implements I_DispatchRuleInterface {

    @Override
    public String toString() {
        return "MOD{" + '}';
    }

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if (anJobList.isEmpty()) {
            assert false;
        }
        
        
        DispatchJobWlapper selectjob = null;
        Integer DeliveryOrProcessTimeMax = Integer.MIN_VALUE;

        for (DispatchJobWlapper job : anJobList) {
            Integer DeliveryOrProcessTimeBig;
            
            
            //納期と、ジョブの最後に処理した時間、大きい方を選択します。
            if(job.getTheTimeOfDelivery() >= job.getLastProcessSleepTime()){
                DeliveryOrProcessTimeBig = job.getTheTimeOfDelivery();
            }else{
                DeliveryOrProcessTimeBig = job.getLastProcessSleepTime();
            }
            
            
            
            if (DeliveryOrProcessTimeMax < DeliveryOrProcessTimeBig) {
                DeliveryOrProcessTimeMax = DeliveryOrProcessTimeBig;
                selectjob = job;
            }

        }

        return selectjob;
    }
        
        
        
        
    }

