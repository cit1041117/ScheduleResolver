/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import dataArchive.Order;
import dataArchive.ProcessTime;
import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *残り加工時間の総和が最大のジョブを優先するディスパッチングルールです。
 * @author 117
 */
public class MWKR implements I_DispatchRuleInterface {

    @Override
    public String toString() {
        return "MWKR{" + '}';
    }

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if (anJobList.isEmpty()) {
            assert false;
        }

        DispatchJobWlapper selectjob = null;
        Integer sumWorkTimeMAX = Integer.MIN_VALUE;
        
        for (DispatchJobWlapper job : anJobList) {
            
            FunctionSumWorkTime functionSumWorkTime = new FunctionSumWorkTime();
            Integer sumWorkTime = functionSumWorkTime.getSumWorTime(job);
            
            if(sumWorkTime > sumWorkTimeMAX){
                sumWorkTimeMAX = sumWorkTime;
                selectjob = job;
            }
            
            
        }
        
        return selectjob;
    }
}
