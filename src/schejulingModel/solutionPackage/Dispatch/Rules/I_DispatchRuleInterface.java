/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import dataArchive.Job;


import java.util.List;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *ディスパッチング解法のルール処理を分離したインタフェースです。
 *
 * @author 117
 */
public  interface I_DispatchRuleInterface {

   

    
    /**
     *
     * @return
     */
    abstract public DispatchJobWlapper solutionJob(List<DispatchJobWlapper> anJobList,Integer time);
}
