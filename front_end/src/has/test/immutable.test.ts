import {List} from 'immutable'

it('should ', function () {

    let array = [1, 2, 3, 4, 5];
    let list = List(array);
    let list1 = list.delete(2);
    console.log(list);
    console.log(list1);
    let numbers = list1.toArray();
    console.log(numbers);
});
