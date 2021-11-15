package com.yxtl.business.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author px
 * @date 2021/9/22 15:59
 */
@Service
public class ExcelUtil {

    public List getExcelAll(File file) {
        List list = new ArrayList<>();
        try {
            Workbook wb = null;
            String fileName = file.getName();
            if (fileName.toUpperCase().endsWith("XLS")) {
                wb = new HSSFWorkbook(new FileInputStream(file));
            }
            if (fileName.toUpperCase().endsWith("XLSX")) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            }
            // 把一张xls的数据表读到wb里
            // 读取第一页,一般一个excel文件会有三个工作表，这里获取第一个工作表来进行操作 HSSFSheet sheet =
            // wb.getSheetAt(0);
            Sheet sheet = wb.getSheetAt(0);

            DecimalFormat df = new DecimalFormat("0");
            // 循环遍历表sheet.getLastRowNum()是获取一个表最后一条记录的记录号，
            int maxNum = sheet.getLastRowNum();
            //每一列数据长度应该写死，否则后面遍历取最后列的值会报数组越界异常
            Row row1=sheet.getRow(0);
            int maxRow1 =row1.getLastCellNum();

            // 如果总共有3条记录，那获取到的最后记录号就为2，因为是从0开始的
            for (int j = 0; j < maxNum + 1; j++) {
                // 创建一个行对象
                Row row = sheet.getRow(j);
                // 把一行里的每一个字段遍历出来
                if (row == null) {
                    continue;
                } else {
                    int maxRow = row.getLastCellNum();
                    //跳过纯空数据
                    if (maxRow < 1) {
                        continue;
                    }
                    String[] str2 = new String[maxRow1];
                    for (int i = 0; i < maxRow; i++) {
                        // 创建一个行里的一个字段的对象，也就是获取到的一个单元格中的值
                        Cell cell = row.getCell(i);
                        // if (cell != null) {
                        // System.out.println("类型:" + cell.getCellType());
                        // }

                        // 在这里我们就可以做很多自己想做的操作了，比如往数据库中添加数据等
                        // System.out.println("第" + (j + 1) + "行的第" + i + "列的值："
                        // + cell);
                        if (cell != null) {
                            if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) { //类型为数值
                                str2[i] = df.format(cell.getNumericCellValue()); //防止数字变为科学计数
                            } else {
                                str2[i] = cell + "";
                            }
                        }
                    }
                    //String[] 转 String
                    list.add(Arrays.toString(str2).replaceAll("\\[","").replaceAll("\\]",""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
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
