/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch;

import dataArchive.Job;
import schejulingModel.solutionPackage.Dispatch.Rules.I_DispatchRuleInterface;
import dataArchive.JobsContainer;
import dataArchive.Machine;
import dataArchive.Order;
import dataArchive.ProcessTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import result.newpackage.AnswerReportContainer;
import result.newpackage.I_AnswerReportContainer;
import schejulingModel.solutionPackage.I_SchejuleAlgolismInterface;

/**
 * ディスパッチングルールでスケジューリング問題を解く実装になります。
 *
 * @author 117
 *
 */
public class Dispatch implements I_SchejuleAlgolismInterface {

    private I_DispatchRuleInterface rule;
    Time timer;
    private List<DispatchJobWlapper> joblist;
    

    public Dispatch(I_DispatchRuleInterface anrule) {

        this.rule = anrule;
        timer = new Time();




    }

    @Override
    public I_AnswerReportContainer Excecute(JobsContainer jobContainer) {

        

        ArrayList<DispatchJobWlapper> preparationList;
        ArrayList<DispatchJobWlapper> doingJobList;

        //JobのwrapperListをこのメソッドで作る。
        joblist = createJobList(jobContainer);
        boolean existEndYet;

        do {
            preparationList = new ArrayList<>();
            doingJobList = new ArrayList<>();

            existEndYet = false;
            for (DispatchJobWlapper job : joblist) {
                if (job.isIsGoalJob()) {
                } else {
                    existEndYet = true;
                    if (job.isIsFree()) {
                        preparationList.add(job);
                    } else {
                        doingJobList.add(job);
                    }
                }

            }


            if (preparationList.isEmpty()) {
            } else {

                ArrayList<DispatchJobWlapper> rePreparationList = dublinerRemove(preparationList);


                //jobContainer.getJob(0).getOrderMachineMap().values() もうひとつの入力情報。非推奨メソッド使うときに。



                for (DispatchJobWlapper job : rePreparationList) {

                    if (job.isNextMachineFree()) {
                        job.Start(timer.getTime());
                        doingJobList.add(job);
                    }




                }
            }



            Integer shortestTime = Integer.MAX_VALUE;
            //一番完了時間が近いジョブを探す。shortestTimeは最短終了時間がはいる。
            for (DispatchJobWlapper job : doingJobList) {
                if (shortestTime > job.getLastProcessSleepTime()) {
                    shortestTime = job.getLastProcessSleepTime();
                }
            }

            //最短完了時間と同じジョブを終了。（複数のジョブが選択される可能性）
            for (DispatchJobWlapper job : doingJobList) {
                if (job.getLastProcessSleepTime() == shortestTime) {

                    job.Stop();
                    timer.setTime(shortestTime);
                }
            }




        } while (existEndYet);

        I_AnswerReportContainer result = resultAnswerContainer(timer, joblist, jobContainer);
        return result;




    }

    private I_AnswerReportContainer resultAnswerContainer(Time time, List<DispatchJobWlapper> joblist, JobsContainer jobsContainer) {
        //ジョブの一覧表をよこす
        JobsContainer jobContainer = jobsContainer;


        //仕事全体のリードタイムの設定
        int workReadtime = time.getTime();

        //ジョブをキーに、対応したジョブ毎の終了時間を出力するHashmapを生成する。
        HashMap<Job, Integer> jobReadTimeMap = new HashMap<>();
        for (DispatchJobWlapper dispatchJob : joblist) {
            jobReadTimeMap.put(dispatchJob.getJob(), dispatchJob.getLastProcessSleepTime());
        }

        //各ジョブ毎の開始時間をまとめたマップMap<order,Processtime>
        //をジョブの数だけまとめたマップMap<Job,Map<↑>を作成

        Map<Job, Map<Order, ProcessTime>> jobStartedTimeMap = new HashMap<>();

        for (DispatchJobWlapper dispatchJob : joblist) {
            Job keyJob = dispatchJob.getJob();
            Map<Order, ProcessTime> valueMap = dispatchJob.getStartProcessTimeList();

            jobStartedTimeMap.put(keyJob, valueMap);
        }

        String means = "Dispach_" + rule.toString();
        
        return new AnswerReportContainer(jobContainer, jobStartedTimeMap, jobReadTimeMap, workReadtime,means);
    }

    private List<DispatchJobWlapper> createJobList(JobsContainer jobsContainer) {
        List<DispatchJobWlapper> templist = new ArrayList<>();
        MachineStateBoard stateBoard = new MachineStateBoard(jobsContainer.getMachineList());

        for (Job job : jobsContainer.getJobList()) {
            DispatchJobWlapper temp = new DispatchJobWlapper(job,stateBoard);
            templist.add(temp);
        }
        return templist;
    }

    @SuppressWarnings("unchecked")//223行目 ジェネリック付き配列は作れないようなのでunchecked
    private ArrayList<DispatchJobWlapper> dublinerRemove(List<DispatchJobWlapper> candidateList) {
        ArrayList<DispatchJobWlapper> compliteCandidateList = new ArrayList<>();


        Set<Machine> checkedSet = new HashSet<>();
        Map<Machine, ArrayList<DispatchJobWlapper>> tableMap = new HashMap<>();

        for (DispatchJobWlapper job : candidateList) {
            Machine machine = job.nextOrderMachine();
            checkedSet.add(machine);
        }
        Integer tableSize = checkedSet.size();

        ArrayList<DispatchJobWlapper>[] jobTable = new ArrayList[tableSize];

        Integer countMachine = 0;
        for (Machine selectMachine : checkedSet) {
            jobTable[countMachine] = new ArrayList<>();
            tableMap.put(selectMachine, jobTable[countMachine]);
            countMachine += 1;
        }
        //ここまでがダブったジョブを入れておくテーブル作り(tableMap)
        //マシン個のArrayListがある。


        //テーブルに全てのジョブを加えて分類。
        for (DispatchJobWlapper job : candidateList) {
            List<DispatchJobWlapper> stockjob = tableMap.get(
                    job.nextOrderMachine());
            stockjob.add(job);
        }

        for (Machine machine : checkedSet) {
            List<DispatchJobWlapper> stocked = tableMap.get(machine);

            
            
            //tableの中で要素数が一つなら最終的な戻りリストに入れる。
            if (stocked.size() == 1) {
                compliteCandidateList.add(stocked.get(0));
            }

            //tableの中で要素数が２つ以上なら、ディスパッチングルールに回して、解かせる。
            if (stocked.size() > 1) {
                
                
                for(DispatchJobWlapper selectjob : stocked){
                    
                    //もしタイムに０があったらルールにかけない。
                     ProcessTime time = selectjob.OrderToTime(selectjob.nextOrder());
                     if(time.get() == 0){
                         compliteCandidateList.add(selectjob);
                         break;
                     }
                     
                    
                     DispatchJobWlapper job = rule.solutionJob(stocked, timer.getTime());
                     compliteCandidateList.add(job);
                }
                
                
            }

        }

        return compliteCandidateList;







    }

    @Override
    public boolean checkAbleSolution(JobsContainer jobsContainer) {
        //特に必要条件なしl
        return true;



    }

    class Time {

        private Integer time;

        protected Integer getTime() {
            return time;
        }

        protected void setTime(Integer time) {
            this.time = time;
        }

        public Time() {
            time = 0;
        }
    }
}
