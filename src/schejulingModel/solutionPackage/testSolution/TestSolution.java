/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.testSolution;

import dataArchive.JobsContainer;
import result.newpackage.I_AnswerReportContainer;
import schejulingModel.solutionPackage.I_SchejuleAlgolismInterface;

/**
 *
 * @author shirai-Lab
 */
public class TestSolution implements I_SchejuleAlgolismInterface{

    @Override
    public I_AnswerReportContainer Excecute(JobsContainer jobContainer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkAbleSolution(JobsContainer jobsContainer) {
        return false;
    }
    
}
