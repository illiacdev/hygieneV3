export class Util {

    static updateProp2<TObj, K extends keyof TObj>(obj: TObj, key: string, value: TObj[K]) {
        obj[key as K] = value;
    }

    static getProp2<TObj>(obj: TObj, key: string) {
        type T_Key = keyof typeof obj;
        return obj[key as T_Key] ;
    }
}