/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.JohnsonTactics;

import dataArchive.JobsContainer;
import result.newpackage.I_AnswerReportContainer;
import schejulingModel.solutionPackage.CheckerAbleSolution;
import schejulingModel.solutionPackage.I_SchejuleAlgolismInterface;

/**
 *
 * @author 117
 */
public class Johnson implements I_SchejuleAlgolismInterface{

    @Override
    public I_AnswerReportContainer Excecute(JobsContainer jobContainer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkAbleSolution(JobsContainer jobsContainer) {
        CheckerAbleSolution checker = new CheckerAbleSolution(Integer.MAX_VALUE, 2, true);
        return checker.checkAbleSolution(jobsContainer);
    }
    
}
