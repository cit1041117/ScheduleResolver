/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchiveCreate;

import dataArchive.JobsContainer;

/**
 *
 * @author 117
 */
public interface I_convertionToJobData {
    
    
    /**
     *このインタフェースを実装したクラスは入力情報からdataArchive.JobContainerを作ります。
     * 入力情報が正しいかどうかのチェックメソッドを実装します。
     * @return
     */
    public JobsContainer getJobContainer();
        
    public boolean checkMemberRight();
    
    public String getReasonCheckfalse();
    
    
    
}
