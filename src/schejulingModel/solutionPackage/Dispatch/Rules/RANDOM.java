/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;
import java.util.Random;

/**
 *乱数を用いてランダムにジョブを選ぶディスパッチングルールです。
 * 
 * @author 117
 */
public class RANDOM implements I_DispatchRuleInterface{

    @Override
    public String toString() {
        return "RANDOM{" + '}';
    }

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if(anJobList.isEmpty()){
            assert false;
        }
        Random rnd = new Random();
        int randomIndex = rnd.nextInt(anJobList.size());
        
        return anJobList.get(randomIndex);
    }
    
}
