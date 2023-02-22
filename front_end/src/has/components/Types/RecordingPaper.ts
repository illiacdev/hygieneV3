import dayjs, {Dayjs} from "dayjs";

export enum E_chronometer{
    Ready,
    Run,
    Stop
}
export class RecordingPaper {
    id?: string;
    observeDepartment?: string;
    observeOccupation?: string;
    observeName?: string;
    location?: string;
    action?: string;
    subAction?: string;
    glove?: boolean = false;
    actionStartTime?: Dayjs|string;
    // actionEndTime?: Dayjs|string;
    actionDuration :number = 0;
    actionType?: string;
    passFail?: boolean = false;
    observer?: string;

    key?: string;
    chronometer_state?: E_chronometer = E_chronometer.Ready;

    public _현재가능액션():string{
        if(this.chronometer_state == E_chronometer.Ready){
            return "시작";

        }

        if(this.chronometer_state == E_chronometer.Run){
            return "중지";
        }
        return "완료";
    }
    public obtain수행시간():number{
        if(this.chronometer_state == E_chronometer.Ready)
            return 0;

        if(this.chronometer_state == E_chronometer.Run) {
            this.actionDuration = Math.trunc(dayjs().diff(this.actionStartTime) / 1000);
            return this.actionDuration;
        }

        // E_chronometer.Stop
        return this.actionDuration;
    }
    public nextStateClickChronometer(){
        if(this.chronometer_state == E_chronometer.Ready){
            this.actionStartTime = dayjs();
            this.chronometer_state = E_chronometer.Run;
            return;
        }

        if(this.chronometer_state == E_chronometer.Run){
            this.actionDuration = Math.trunc(dayjs().diff(this.actionStartTime) / 1000);
            this.chronometer_state = E_chronometer.Stop;
            return;
        }

        return;
    }
}
