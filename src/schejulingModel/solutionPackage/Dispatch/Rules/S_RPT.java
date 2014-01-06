/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *スラックを残り工程数で割った値が最小のジョブを優先するディスパッチングルールです。
 * @author 117
 */
public class S_RPT implements I_DispatchRuleInterface{

    @Override
    public String toString() {
        return "S_RPT{" + '}';
    }

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if (anJobList.isEmpty()) {
            assert false;
        }


        DispatchJobWlapper selectjob = null;
        float srptAnswerMIN = Float.MAX_VALUE;

        for (DispatchJobWlapper job : anJobList) {

            
            FunctionSlack functionSlack = new FunctionSlack();
            float slack = functionSlack.getSlack(job, time);
            
            FunctionOperationCount functionOperationCount = new FunctionOperationCount();
            Integer operations = functionOperationCount.getOprationCount(job);
            
            float srptAnswer = slack/operations ;

            if (srptAnswer < srptAnswerMIN) {
                srptAnswerMIN = srptAnswer;
                selectjob = job;
            }


        }

        return selectjob;

    }
    
}
