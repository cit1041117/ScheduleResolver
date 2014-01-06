/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *加工時間最小のジョブを優先するディスパッチングルールです。
 * @author 117
 */
public class SPT implements I_DispatchRuleInterface {

    @Override
    public String toString() {
        return "SPT{" + '}';
    }

    

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if (anJobList.isEmpty()) {
            assert false;
        }

        DispatchJobWlapper selectjob = null;
        Integer processTimeMin = Integer.MAX_VALUE;
        for (DispatchJobWlapper job : anJobList) {
            if (processTimeMin > job.nextOrderTime()) {
                processTimeMin = job.nextOrderTime();
                selectjob = job;
            }
        }

        return selectjob;
    }
}
