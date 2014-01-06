/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel;


import dataArchive.JobsContainer;
import result.newpackage.I_AnswerReportContainer;

/**
 *スケジューリングを行うモジュールの窓口です。
 * 解法とジョブコンテナを渡すと、スケジュール情報を含んだ
 * AnswerReportContainerのインスタンスを返します。ｌ
 * 
 * 処理メソッドなのでコンストラクタは要求しません
 * @author 117
 */
public interface I_SchejulingModel {
    
    
    /**
     *解法のEnumリストを返します。
     * @return
     */
    public Solutions[] getSolutionEnum();
    
    /**
     *実際に問題を解いて返します。
     * 
     * @param solutions
     * @return
     */
    public I_AnswerReportContainer Excecute(JobsContainer jobContainer , Solutions solutions);
    
    
    /**
     *
     * 解法が適応できるかどうかチェックします。ジョブの数とマシンの数が有効ならtrueを返します
     * この実装はこの実装ではなく、解法実装インタフェースに委譲されます。
     * 
     * @param solution
     * @param jobsContainer
     * @return
     */
    public boolean checkAbleSolution(Solutions solution,JobsContainer jobsContainer);
    
    

}
