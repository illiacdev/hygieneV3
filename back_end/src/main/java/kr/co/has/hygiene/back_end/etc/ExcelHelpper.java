package kr.co.has.hygiene.back_end.etc;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelHelpper {
    @Data
    @Builder
    public static class Observer {
        public String 성명;
        public String 직종;
        public Integer 직종코드;
        public String 부서1;
        public String 부서2;
        public String 부서3;
    }

    public static List<Map<Object, Object>> readExcel(String fileName, InputStream is) {
        List<Map<Object, Object>> list = new ArrayList<>();
        try {
//            is = new FileInputStream(excel);
            Workbook workbook = null;
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(is);
            }

            if (workbook != null) {
                int sheets = workbook.getNumberOfSheets();
                getSheet(workbook, sheets, list);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    public static List<Map<Object, Object>> readExcel(String path, String fileName) {
        List<Map<Object, Object>> list = new ArrayList<>();
        if (path == null || fileName == null) {
            return list;
        }

//        FileInputStream is = null;
        File excel = new File(path + fileName);
        try {
//            FileInputStream is = new FileInputStream(excel);
            return readExcel(fileName, new FileInputStream(excel));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static Workbook readExcelWorkbook(String path, String fileName) {
        List<Map<Object, Object>> list = new ArrayList<>();
        if (path == null || fileName == null) {
            return null;
        }

        FileInputStream is = null;
        File excel = new File(path + fileName);
        try {
            is = new FileInputStream(excel);
            Workbook workbook = null;
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(is);
            }


            return workbook;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    public static Workbook readExcelWorkbook(String fileName,InputStream is) {
        try {
//            is = new FileInputStream(excel);
            Workbook workbook = null;
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(is);
            }

            return workbook;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    public static void getSheet(Workbook workbook, int sheets, List<Map<Object, Object>> list) {
        for (int z = 0; z < sheets; z++) {
            Sheet sheet = workbook.getSheetAt(z);
            int rows = sheet.getLastRowNum();
            getRow(sheet, rows, list);
        }
    }

    public static void getRow(Sheet sheet, int rows, List<Map<Object, Object>> list) {
        for (int i = 0; i <= rows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int cells = row.getPhysicalNumberOfCells();
                list.add(getCell(row, cells));
            }
        }
    }

    public static Map<Object, Object> getCell(Row row, int cells) {
        String[] columns = {"column1", "column2", "column3", "column4", "column5", "column6", "column7"};
        Map<Object, Object> map = new HashMap<>();
        for (int j = 0; j < cells; j++) {
            if (j >= columns.length) {
                break;
            }

            Cell cell = row.getCell(j);
            if (cell != null) {
                switch (cell.getCellType()) {
                    case BLANK:
                        map.put(columns[j], "");
                        break;
                    case STRING:
                        map.put(columns[j], cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            map.put(columns[j], cell.getDateCellValue());
                        } else {
                            map.put(columns[j], cell.getNumericCellValue());
                        }
                        break;
                    case ERROR:
                        map.put(columns[j], cell.getErrorCellValue());
                        break;
                    default:
                        map.put(columns[j], "");
                        break;
                }
            }
        }

        return map;
    }
}
