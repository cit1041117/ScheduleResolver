/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel;

import dataArchive.JobsContainer;
import result.newpackage.I_AnswerReportContainer;
import schejulingModel.solutionPackage.Dispatch.Dispatch;
import schejulingModel.solutionPackage.Dispatch.Rules.A_OPN;
import schejulingModel.solutionPackage.Dispatch.Rules.CR;
import schejulingModel.solutionPackage.Dispatch.Rules.EDD;
import schejulingModel.solutionPackage.Dispatch.Rules.FCFS;
import schejulingModel.solutionPackage.Dispatch.Rules.LA_SLACK;
import schejulingModel.solutionPackage.Dispatch.Rules.LCFS;
import schejulingModel.solutionPackage.Dispatch.Rules.LPT;
import schejulingModel.solutionPackage.Dispatch.Rules.MOD;
import schejulingModel.solutionPackage.Dispatch.Rules.MWKR;
import schejulingModel.solutionPackage.Dispatch.Rules.RANDOM;
import schejulingModel.solutionPackage.Dispatch.Rules.SLACK;
import schejulingModel.solutionPackage.Dispatch.Rules.SPT;
import schejulingModel.solutionPackage.Dispatch.Rules.S_OPN;
import schejulingModel.solutionPackage.Dispatch.Rules.S_RPT;
import schejulingModel.solutionPackage.I_SchejuleAlgolismInterface;
import schejulingModel.solutionPackage.testSolution.TestSolution;

/**
 *
 * @author 117
 */
public class SchejulingModel implements I_SchejulingModel{
    

    
    

    @Override
    public Solutions[] getSolutionEnum() {
        return Solutions.values();
    }

    @Override
   synchronized public I_AnswerReportContainer Excecute(JobsContainer jobContainer, Solutions solutions) {
        I_SchejuleAlgolismInterface algolism;
        algolism = selectAlgolism(solutions);
        
        return algolism.Excecute(jobContainer);

    }
    
   synchronized private I_SchejuleAlgolismInterface selectAlgolism(Solutions solutions){
        I_SchejuleAlgolismInterface algolism;
        switch (solutions) {
           
            case Dispatch_FCFS:
                algolism = new Dispatch(new FCFS());
                break;
            case Dispatch_A_OPN:
                algolism = new Dispatch(new A_OPN());
                break;
            case Dispatch_CR:
                algolism = new Dispatch(new CR());
                break;
            case Dispatch_EDD:
                algolism = new Dispatch(new EDD());
                break;
            case Dispatch_LA_SLACK:
                algolism = new Dispatch(new LA_SLACK());
                break;
            case Dispatch_LCFS:
                algolism = new Dispatch(new LCFS());
                break;
            case Dispatch_LPT:
                algolism = new Dispatch(new LPT());
                break;
            case Dispatch_MOD:
                algolism = new Dispatch(new MOD());
                break;
            case Dispatch_MWKR:
                algolism = new Dispatch(new MWKR());
                break;
            case Dispatch_RANDOM:
                algolism = new Dispatch(new RANDOM());
                break;
            case Dispatch_SLACK:
                algolism = new Dispatch(new SLACK());
                break;
            case Dispatch_SPT:
                algolism = new Dispatch(new SPT());
                break;
            case Dispatch_S_OPN:
                algolism = new Dispatch(new S_OPN());
                break;
            case Dispatch_S_RPT:
                algolism = new Dispatch(new S_RPT());
                break;
                
            case Testsolution:
                algolism = new TestSolution();
                break;
            default:
                throw new AssertionError();
        }       
        return algolism;
    }

    @Override
  synchronized public boolean checkAbleSolution(Solutions solution, JobsContainer jobsContainer) {
        
        I_SchejuleAlgolismInterface algolism;
        algolism = selectAlgolism(solution);
        
        return algolism.checkAbleSolution(jobsContainer);
        
    }
    
    
}
