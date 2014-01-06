/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *残り工程数（残りオペレーション数とも）
 * @author 117
 */
public class FunctionOperationCount {

    protected Integer getOprationCount(DispatchJobWlapper job) {
        Integer sumOperationCount = 0;
        for (Integer i = job.nextOrder().get(); i <= job.getFinalOrderInteger(); i++) {
            sumOperationCount += 1;
        }
        return sumOperationCount;
    }
}
