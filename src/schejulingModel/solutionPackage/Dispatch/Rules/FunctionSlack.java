/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import dataArchive.Order;
import dataArchive.ProcessTime;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *スラック = 納期 - 現在時刻 - 残り総加工時間
 * @author 117
 */
public class FunctionSlack {

    protected Integer getSlack(DispatchJobWlapper job, Integer time) {
        
        FunctionSumWorkTime functionSumWorkTime = new FunctionSumWorkTime();
        Integer sumWorkTime = functionSumWorkTime.getSumWorTime(job);

        Integer limit = job.getTheTimeOfDelivery();

        Integer slack = limit - time - sumWorkTime;
        
        return slack;
    }
    
    
}
