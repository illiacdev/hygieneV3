export class RecordingPaper {
    id?: number;
    observeDepartment?: string;
    observeOccupation?: string;
    observeName?: string;
    location?: string;
    action?: string;
    subAction?: string;
    glove?: boolean = false;
    actionStartTime?: string;
    actionEndTime?: string;
    actionType?: string;
    passFail?: boolean = false;
    observer?: string;
}
