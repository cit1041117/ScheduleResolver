/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 * 加工時間最大のジョブを優先するディスパッチングルールです。
 *
 * @author 117
 */
public class LPT implements I_DispatchRuleInterface {

    @Override
    public String toString() {
        return "LPT{" + '}';
    }

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {

        if (anJobList.isEmpty()) {
            assert false;
        }
        
        DispatchJobWlapper selectjob = null;
        Integer processTimeMax = Integer.MIN_VALUE;

        for (DispatchJobWlapper job : anJobList) {

            if (processTimeMax < job.nextOrderTime()) {
                processTimeMax = job.nextOrderTime();
                selectjob = job;
            }

        }

        return selectjob;
    }
}
