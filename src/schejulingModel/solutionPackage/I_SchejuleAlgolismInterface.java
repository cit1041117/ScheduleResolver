/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage;

import dataArchive.JobsContainer;
import result.newpackage.I_AnswerReportContainer;


/**
 *
 * @author 117
 */
public interface I_SchejuleAlgolismInterface {
    /**
     *実際のアルゴリズム部分です。このメソッドを引き金に解を出します。
     * 
     * @param jobContainer
     * @return
     */
    public I_AnswerReportContainer Excecute(JobsContainer jobContainer);
    
    /**
     *
     * この解法を利用できるかどうか判別します。
     * 実装するクラスは、以下の条件
     * フローショップスケジュールの時
     * ジョブ、マシンの数に制限がある時
     * CheckerAbleSolutionを呼び出して判定してください。
     * 
     * @param jobCount
     * @param machineCount
     * @return
     */
    public boolean checkAbleSolution(JobsContainer jobsContainer);
    
    
    
}
