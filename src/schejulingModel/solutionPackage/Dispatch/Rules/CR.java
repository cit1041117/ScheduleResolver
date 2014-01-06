/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *現在時刻と納期との差（作業余裕）を加工時間で割った値（クリティカル値）が
 * 最小のジョブを優先するディスパッチングルールです。
 * @author 117
 */
public class CR implements I_DispatchRuleInterface{

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if(anJobList.isEmpty()){
            assert false;
        }
        
        float MINCriticalRatio = Float.MAX_VALUE;
        DispatchJobWlapper selectJob = null;
        for (DispatchJobWlapper job : anJobList) {
            
            FunctionAllowance functionAllowance = new FunctionAllowance();
            float allowance;
            allowance = (float)functionAllowance.getAllowance(job, time);
            
            float criticalRatio = allowance/job.nextOrderTime();
            
            
            
            if(criticalRatio < MINCriticalRatio){
                MINCriticalRatio = criticalRatio;
                selectJob = job;
            }
        }
        return selectJob;
    }

    @Override
    public String toString() {
        return "CR{" + '}';
    }
    
}
