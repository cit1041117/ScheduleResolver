/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.sql.Time;
import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *
 * @author shirai-Lab
 * ジョブを先着順で渡すディスパッチングルールです。
 */
public class FCFS implements I_DispatchRuleInterface{

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList,Integer time) {
        if(anJobList.isEmpty()){
            assert false;
        }
          
        return anJobList.get(0);
        
        
    }

    @Override
    public String toString() {
        return "FCFS{" + '}';
    }
    
}
