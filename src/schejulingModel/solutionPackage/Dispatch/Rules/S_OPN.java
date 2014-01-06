/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *スラックを残り総加工時間で割った値が最小のジョブを優先するディスパッチングルールです。
 * @author 117
 */
public class S_OPN implements I_DispatchRuleInterface{

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if (anJobList.isEmpty()) {
            assert false;
        }


        DispatchJobWlapper selectjob = null;
        float sopnAnswerMIN = Float.MAX_VALUE;

        for (DispatchJobWlapper job : anJobList) {
            
            
            
            
            FunctionSlack functionSlack = new FunctionSlack();
            
            float slack = (float)functionSlack.getSlack(job, time);
            
            FunctionSumWorkTime functionSumWorkTime = new FunctionSumWorkTime();
            Integer sumWorkTime = functionSumWorkTime.getSumWorTime(job);
            
            
            float sopnAnswer = slack/sumWorkTime;


            if (sopnAnswer < sopnAnswerMIN) {
                sopnAnswerMIN = sopnAnswer;
                selectjob = job;
            }


        }

        return selectjob;


    }

    @Override
    public String toString() {
        return "S_OPN{" + '}';
    }
    
}
