/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valuationUnit;

import dataArchive.Job;
import java.util.List;
import result.newpackage.I_AnswerReportContainer;

/**
 *
 * @author 117
 */
public class DateValuation {
    I_AnswerReportContainer reportContainer;

    public DateValuation(I_AnswerReportContainer reportContainer) {
        this.reportContainer = reportContainer;
    }
    
    public Integer maxDeliveryLate(){
        List<Job> joblist = reportContainer.getJobList();
        
        Integer maxDeliveryLate = 0;
        for (Job job : joblist) {
           Integer DeliveryLate = 0;
           Integer readtime = reportContainer.getJobReadtime(job);
           Integer limit = job.getTheTimeOfDelivery();
           
           //終了時刻-納期がプラスの時、それは遅れている。
           DeliveryLate = readtime - limit;
           
           //終了時刻-納期がマイナスの時、遅れはゼロ。
           if(DeliveryLate < 0){
               DeliveryLate = 0;
           }
           
           if(maxDeliveryLate < DeliveryLate){
               maxDeliveryLate = DeliveryLate;
           }
        }
        return maxDeliveryLate;
    }
    
    public Integer maxDeliveryGap(){
        List<Job> joblist = reportContainer.getJobList();
        
        Integer maxDeliveryLate = 0;
        for (Job job : joblist) {
           Integer DeliveryLate = 0;
           Integer readtime = reportContainer.getJobReadtime(job);
           Integer limit = job.getTheTimeOfDelivery();
           
           //終了時刻-納期がプラスの時、それはズレている。
           DeliveryLate = readtime - limit;
           
           //終了時刻-納期がマイナスの時、プラスに直す。
           if(DeliveryLate < 0){
               DeliveryLate = -DeliveryLate;
           }
           
           if(maxDeliveryLate < DeliveryLate){
               maxDeliveryLate = DeliveryLate;
           }
        }
        return maxDeliveryLate;
    }
    
    public double averageDeliveryLate(){
        List<Job> joblist = reportContainer.getJobList();
        double averageDeliveryLate = 0;
        Integer total = joblist.size();
        
        
        for (Job job : joblist) {
           Integer DeliveryLate = 0;
           Integer readtime = reportContainer.getJobReadtime(job);
           Integer limit = job.getTheTimeOfDelivery();
           
           //終了時刻-納期がプラスの時、それは遅れている。
           DeliveryLate = readtime - limit;
           
           //終了時刻-納期がマイナスの時、遅れはゼロ。
           if(DeliveryLate < 0){
               DeliveryLate = 0;
           }
           
           averageDeliveryLate += DeliveryLate;
           
        }
        return averageDeliveryLate/total;
        
    }
    
    
    public double averageDeliveryGap(){
        List<Job> joblist = reportContainer.getJobList();
        double averageDeliveryLate = 0;
        Integer total = joblist.size();
        
        
        for (Job job : joblist) {
           Integer DeliveryLate = 0;
           Integer readtime = reportContainer.getJobReadtime(job);
           Integer limit = job.getTheTimeOfDelivery();
           
           //終了時刻-納期がプラスの時、それはズレている。
           DeliveryLate = readtime - limit;
           
           //終了時刻-納期がマイナスの時、プラスに直す。
           if(DeliveryLate < 0){
               DeliveryLate = -DeliveryLate;
           }
           
           averageDeliveryLate += DeliveryLate;
           
        }
        return averageDeliveryLate/total;
        
    }    
}
