/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *作業余裕 納期-現在時間
 * @author 117
 */
public class FunctionAllowance {
    protected Integer getAllowance(DispatchJobWlapper job,Integer time){
        Integer limit = job.getTheTimeOfDelivery();
        Integer DueDate = limit - time;
        
        return DueDate;
    }
    
}
