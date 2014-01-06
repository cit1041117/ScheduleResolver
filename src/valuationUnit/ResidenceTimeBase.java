/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valuationUnit;

import result.newpackage.I_AnswerReportContainer;

/**
 *
 * @author 117
 */
public class ResidenceTimeBase {
    I_AnswerReportContainer reportContainer;

    public ResidenceTimeBase(I_AnswerReportContainer reportContainer) {
        this.reportContainer = reportContainer;
    }
    
    public Integer MaxResidenceTime(){
        return  reportContainer.getReadTime();
    }
    
    
    
}
