/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 * ジョブを後着順で渡すディスパッチングルールです。
 *
 * @author 117
 */
public class LCFS implements I_DispatchRuleInterface {

    @Override
    public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList, Integer time) {
        if (anJobList.isEmpty()) {
            assert false;
        }
        Integer size = anJobList.size();
        return anJobList.get(size - 1);
    }

    @Override
    public String toString() {
        return "LCFS{" + '}';
    }
}
