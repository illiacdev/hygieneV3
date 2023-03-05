package kr.co.has.hygiene.back_end.rest;

import com.google.gson.Gson;
import kr.co.has.hygiene.back_end.domain.Entity조직도;
import kr.co.has.hygiene.back_end.domain.Entity조직도Repository;
import kr.co.has.hygiene.back_end.etc.ExcelHelpper;
import kr.co.has.hygiene.back_end.etc.ExcelHelpper.Observer;
import kr.co.has.hygiene.back_end.types.Node;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class RestService {


    final Entity조직도Repository entity조직도Repository;


    public Node makeTree(String filename, InputStream inputStream) {
        List<Observer> observers = new ArrayList<>();

        Workbook sheets = ExcelHelpper.readExcelWorkbook(filename, inputStream);
        Sheet sheetAt = sheets.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        new IntegerSequence.Range(1, lastRowNum, 1).forEach(integer -> {
            Row row = sheetAt.getRow(integer);
            int physicalNumberOfCells = row.getPhysicalNumberOfCells();
            String cell = row.getCell(1).getStringCellValue();
            String cell1 = row.getCell(2).getStringCellValue();
            int cell2 = (int) row.getCell(3).getNumericCellValue();
            String cell3 = row.getCell(4).getStringCellValue();
            String cell4 = row.getCell(5).getStringCellValue();
//            if(row.getCell(6).getCellType() == CellType.BLANK)

            Observer build = Observer.builder()
                    .성명(cell)
                    .직종(cell1)
                    .직종코드(cell2)
                    .부서1(cell3)
                    .부서2(cell4)
                    .부서3(Optional.ofNullable(row.getCell(6)).map(cell5 -> cell5.getStringCellValue()).orElse(null))
                    .build();

            observers.add(build);
        });

        Node root = Node.builder().name("root").build();

        List<Observer> collect = observers.stream().map(observer -> {
            if (observer.부서3 == null)
                observer.부서3 = "none";
            return observer;
        }).collect(Collectors.toList());
        collect.stream().collect(Collectors.groupingBy(observer -> observer.부서1)).forEach((s, observers1) -> {
            Node add = root.add(Node.builder().name(s).build());
            observers1.stream().collect(Collectors.groupingBy(observer -> observer.부서2)).forEach((s1, observers2) -> {
                Node add1 = add.add(Node.builder().name(s1).build());
                observers2.stream().collect(Collectors.groupingBy(observer -> observer.부서3)).forEach((s2, observers3) -> {

                    Node build = Node.builder().name(s2).build();
                    Node add2 = s2.equals("none") ? build : add1.add(build);
                    observers3.stream().collect(Collectors.groupingBy(observer -> observer.직종)).forEach((s3, observers4) -> {
                        Node add3 = (add2.name.equals("none")) ? add1.add(Node.builder().name(s3).build()) : add2.add(Node.builder().name(s3).build());

                        observers4.forEach(observer -> {

                            add3.add(Node.builder().name(observer.성명).build());
                            if (add2.name.equals("none")) {
                                System.out.println(String.format("%s-%s : %s", s, s1, observer.get성명()));
                            } else {
                                System.out.println(String.format("%s-%s-%s : %s", s, s1, s2, observer.get성명()));
                            }
                        });
                    });

                });
            });
        });

        entity조직도Repository.deleteAll();
        String s = new Gson().toJson(root).toString();
        Entity조직도 build = Entity조직도.builder()
                .fileName(filename)
                .stringfyNodeJson(s).build();
        entity조직도Repository.save(build);
        return root;
    }
}
