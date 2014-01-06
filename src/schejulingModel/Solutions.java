/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel;

/**
 *
 * スケジュール問題を解くためのアルゴリズム一覧です。
 * 追加する場合はこの部分とI_SchejulingModel内での対応パターンを増やします。
 * @author 117
 */
public enum Solutions {
    Dispatch_FCFS("ジョブを先着順で渡すディスパッチングルールです。"),
    Dispatch_A_OPN("作業余裕を工程数で割った値が最小のジョブを優先するディスパッチングルールです。"),
    Dispatch_CR("現在時刻と納期との差（作業余裕）を加工時間で割った値（クリティカル値）が最小のジョブを優先するディスパッチングルールです。"),
    Dispatch_EDD("納期が最も迫っているジョブを優先するディスパッチングルールです。"),
    Dispatch_LA_SLACK("加工時間とスラックの和が最小となるジョブを優先するディスパッチングルールです。"),
    Dispatch_LCFS("ジョブを後着順で渡すディスパッチングルールです。"),
    Dispatch_LPT("加工時間最大のジョブを優先するディスパッチングルールです。"),
    Dispatch_MOD("納期とここまで終わっているジョブの終了時間の大きい方の値が最大のジョブを優先するディスパッチングルールです。"),
    Dispatch_MWKR("残り加工時間の総和が最大のジョブを優先するディスパッチングルールです。"),
    Dispatch_RANDOM("乱数を用いてランダムにジョブを選ぶディスパッチングルールです。"),
    Dispatch_SLACK("納期に対する余裕（スラック）が最小のジョブを優先するディスパッチングルールです。"),
    Dispatch_SPT("加工時間最小のジョブを優先するディスパッチングルールです。"),
    Dispatch_S_OPN("スラックを残り総加工時間で割った値が最小のジョブを優先するディスパッチングルールです。"),
    Dispatch_S_RPT("スラックを残り工程数で割った値が最小のジョブを優先するディスパッチングルールです。"),
    Testsolution("実験用");
    
    private String explain ;

    private Solutions(String anexplain) {
        this.explain = anexplain;
    }

    public String getExplain() {
        return explain;
    }
    
    
    
    
}
