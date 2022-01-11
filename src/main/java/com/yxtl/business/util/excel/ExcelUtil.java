package com.yxtl.business.util.excel;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author px
 * @date 2021/9/22 15:59
 */
@Service
public class ExcelUtil {

    public Map<String, List<String>> getExcelAll(File file) {
        Map<String, List<String>> company = new HashMap<>();
        List<String> companyNameList = new ArrayList<>();
        try {
            Workbook wb;
            String fileName = file.getName();
            if (fileName.toUpperCase().endsWith("XLS")) {
                wb = new HSSFWorkbook(new FileInputStream(file));
            } else if (fileName.toUpperCase().endsWith("XLSX")) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } else {
                return null;
            }
            // 把一张xls的数据表读到Workbook里
            int sheets = wb.getNumberOfSheets();
            Sheet sheet;
            for (int i = 0; i < sheets; i++) {
                sheet = wb.getSheetAt(i);
                if (null == sheet) {
                    continue;
                }
                // 循环遍历表sheet.getLastRowNum()是获取一个表最后一条记录的记录号，
                int maxRow = sheet.getLastRowNum();
                // 如果总共有3条记录，那获取到的最后记录号就为2，因为是从0开始的
                for (int j = 0; j <= maxRow; j++) {
                    // 创建一个行对象
                    Row row = sheet.getRow(j);
                    // 把一行里的每一个字段遍历出来
                    if (row != null) {
                        //表头总共的列数
                        int maxCell = row.getLastCellNum();
                        //跳过纯空数据
                        if (maxCell < 1) {
                            continue;
                        }
                        // 表的第一行为公司名
                        if (j > 0) {
                            for (int k = 0; k < maxCell; k++) {
                                // 创建一个行里的一个单元格的对象
                                Cell cell = row.getCell(k);
                                // 在这里我们就可以做很多自己想做的操作了，比如往数据库中添加数据等
                                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                                    company.get(companyNameList.get(k)).add(cell.toString());
                                }
                            }
                        } else {
                            for (int k = 0; k < maxCell; k++) {
                                List<String> nodeNumList = new ArrayList<>();
                                Cell cell = row.getCell(k);
                                if (cell != null) {
                                    company.put(cell.toString(), nodeNumList);
                                    companyNameList.add(cell.toString());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;
    }

//    public File transferToFile(MultipartFile multipartFile) {
//        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法
//        File file = null;
//        try {
//            String originalFilename = multipartFile.getOriginalFilename();
//            System.out.println("原始文件名: " + originalFilename);
//            String[] filename = originalFilename.split("\\.");
//            filename[0] = "BD" + filename[0];
//            //createTempFile(prefix, suffix)方法中prefix(前缀)长度至少为3，否则报错
//            file=File.createTempFile(filename[0], filename[1]);
//            multipartFile.transferTo(file);
//            file.deleteOnExit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }

}