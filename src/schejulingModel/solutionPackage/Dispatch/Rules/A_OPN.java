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
 *作業余裕を工程数で割った値が最小のジョブを優先するディスパッチングルールです。
 * @author 117
 */
public class A_OPN implements I_DispatchRuleInterface{

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
       if(anJobList.isEmpty()){
            assert false;
        }
       
       
       
        DispatchJobWlapper selectjob = null;
        float aopnAnswerMIN = Float.MAX_VALUE;
        
        for (DispatchJobWlapper job : anJobList) {
            
            FunctionAllowance functionAllowance = new FunctionAllowance();
            float DueDate = (float)functionAllowance.getAllowance(job, time);
            
            FunctionOperationCount functionOperationCount = new FunctionOperationCount();
            Integer sumOperationCount = functionOperationCount.getOprationCount(job);
            
            
            float aopnAnswer = DueDate / sumOperationCount;
            
            if(aopnAnswer < aopnAnswerMIN){
                aopnAnswerMIN = aopnAnswer;
                selectjob = job;
            }
            
            
        }
        
        return selectjob;
       
    }

    @Override
    public String toString() {
        return "A_OPN{" + '}';
    }
    
}
