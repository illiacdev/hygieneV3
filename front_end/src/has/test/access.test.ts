// @ts-ignore
class A {
    name: string = "illiac";
    age: number = 50;

}

function updateProp<TObj, K extends keyof TObj>(obj: TObj, key: K, value: TObj[K]) {
    obj[key] = value;
}

function getProp<TObj, K extends keyof TObj>(obj: TObj, key: K) {
    return obj[key] ;
}



function updateProp2<TObj, K extends keyof TObj>(obj: TObj, key: string, value: TObj[K]) {
    obj[key as K] = value;
}

function getProp2<TObj>(obj: TObj, key: string) {
    type T_Key = keyof typeof obj;
    return obj[key as T_Key] ;
}


it('should ', function () {
    let obj = new A();
    let fname_name = "name";
    let fname_age = "age";


    type ObjectKey = keyof typeof obj;

    const myVar = fname_name as ObjectKey
    // const myVar = fname_age as ObjectKey

    console.log(getProp(obj,myVar));
    updateProp2(obj, "name", "changed_namew");
    console.log(obj[myVar]);


    console.log(getProp2(obj, "name"));


    // obj[myVar] = "test";

});
