/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *
 * @author 117
 */

public class EDD implements I_DispatchRuleInterface{

    @Override
    public String toString() {
        return "EDD{" + '}';
    }
    /**
     * 納期が最も迫っているジョブを優先するディスパッチングルールです。
     * もし納期を現在時間が過ぎていた場合は、最も納期を過ぎているジョブを優先します。
     * 
     * @param anJobList
     * @param time
     * @return 
     */
    
    
    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList,Integer time) {
        if(anJobList.isEmpty()){
            assert false;
        }
        
        
        Integer RecentlyJust = Integer.MAX_VALUE;
        DispatchJobWlapper selectJob = null;
        for (DispatchJobWlapper job : anJobList) {
            FunctionAllowance functionAllowance = new FunctionAllowance();
            Integer DueDate = functionAllowance.getAllowance(job, time);
            
            if(DueDate < RecentlyJust){
                RecentlyJust = DueDate;
                selectJob = job;
            }
        }
        return selectJob;
        
    }
    
}
