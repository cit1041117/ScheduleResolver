/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchiveCreate;

import dataArchive.Job;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 117
 * 役目はジョブを作りリストにつめ渡す事。
 */
public class JobCreater {
    Integer jobCount;
    List<Job> joblist;
    

    public JobCreater() {
        this.joblist = new ArrayList<>();
        jobCount = 0;
    }
    
    private String jobNameCreate(){
        jobCount += 1;
        String name = "Job" + Integer.toString(jobCount);
        
        
        return name;
    }
    
    
    protected void JobCreate(Integer  theTimeOfDelivery,String viewName){
        
         String jobName = jobNameCreate();
        
        //String viewName = ViewName;
        Job job = new Job(jobCount,jobName, viewName, theTimeOfDelivery);
        joblist.add(job);
    }
    
    protected List<Job> returnJobList(){
        return joblist;
    }
    
}
