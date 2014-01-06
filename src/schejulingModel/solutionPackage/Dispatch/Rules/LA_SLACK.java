/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *加工時間とスラックの和が最小となるジョブを優先するディスパッチングルールです。
 * @author 117
 */
public class LA_SLACK implements I_DispatchRuleInterface{

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if (anJobList.isEmpty()) {
            assert false;
        }
        


        DispatchJobWlapper selectjob = null;
        Integer laSlackMIN = Integer.MAX_VALUE;

        for (DispatchJobWlapper job : anJobList) {

            FunctionSlack functionSlack = new FunctionSlack();
            Integer slack =functionSlack.getSlack(job, time);
            Integer workTime = job.nextOrderTime();
            
            Integer laSlack;
            laSlack = slack+workTime;


            if (laSlack < laSlackMIN) {
                laSlackMIN = laSlack;
                selectjob = job;
            }


        }

        return selectjob;

    }

    @Override
    public String toString() {
        return "LA_SLACK{" + '}';
    }
    
}
