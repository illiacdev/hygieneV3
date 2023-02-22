import dayjs, {Dayjs} from "dayjs";
import Optional from "optional-js";

it('should ', function () {
    let dayjs1 = dayjs("2023-01-01T00:00:00");

    // let dayjs2 = dayjs("2023-01-01T00:00:05");
    let dayjs2 : Dayjs|null = null;

    // console.log(dayjs1.diff(dayjs2));
    // console.log(dayjs2.diff(dayjs1));

    Optional.ofNullable(dayjs1)



});
