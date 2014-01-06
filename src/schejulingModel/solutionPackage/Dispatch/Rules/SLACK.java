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
 *納期に対する余裕（スラック）が最小のジョブを優先するディスパッチングルールです。
 * スラックに関してはFunctionSlackクラスを参照してください。
 * @author 117
 */
public class SLACK implements I_DispatchRuleInterface {

    @Override
    public String toString() {
        return "SLACK{" + '}';
    }

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if (anJobList.isEmpty()) {
            assert false;
        }


        DispatchJobWlapper selectjob = null;
        Integer SlackMIN = Integer.MAX_VALUE;

        for (DispatchJobWlapper job : anJobList) {

            FunctionSlack functionSlack = new FunctionSlack();
            Integer slack = functionSlack.getSlack(job, time);


            if (slack < SlackMIN) {
                SlackMIN = slack;
                selectjob = job;
            }


        }

        return selectjob;

    }
}
