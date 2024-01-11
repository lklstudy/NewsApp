package com.lkl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.ExcelBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.lkl.common.file.domain.FillData;
import com.lkl.common.file.domain.Student;
import com.lkl.common.file.listenner.StudentListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class ExceldemoApplicationTests {

    public static Integer hang = 0;

    @Test
    public void contextLoads() {

    }

    @Test
    public void test01(){
//        获取工作簿对象
        ExcelReaderBuilder readWorkBook = EasyExcel.read("src/main/java/com/lkl/common/excel/杭州黑马在线202003班学员信息表.xlsx", Student.class, new StudentListener());
//        获取工作表对象
        ExcelReaderSheetBuilder sheet = readWorkBook.sheet(0);
        //这里是大坑，表示头在第几行
        sheet.headRowNumber(3);
//        读取表中的内容
        sheet.doRead();
    }

    @Test
    public void test02(){
        //写入工作溥
        ExcelWriterBuilder writerBuilder = EasyExcel.write("src/main/java/com/lkl/common/excel/杭州黑马在线202003班学员信息表.xlsx", Student.class);
        ExcelWriterSheetBuilder sheet = writerBuilder.sheet(0);
        sheet.doWrite(initData());

    }

    //    数据生成
    private static List<Student> initData(){
        List<Student> students=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student data=new Student();
            data.setName("测试"+i);
            data.setGender("男");
            data.setBirthday(new Date());
            students.add(data);
        }
        System.out.println(students);
        return students;
    }
    @Test
    public void test03(){
        String template="src/ExcelPattern/fill_data_template1.xlsx";
        /*
        * pathName 是写出路径
        * */
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("Excel填充单组数据.xlsx", FillData.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
        FillData fillData = new FillData();
        fillData.setName("abc");
        fillData.setAge(10);
        sheet.doFill(fillData);

    }

//    test04数据准备
        private static List<FillData> initFillData() {
            ArrayList<FillData> fillDatas = new ArrayList<FillData>();
            for (int i = 0; i < 10; i++) {
                FillData fillData = new FillData();
                fillData.setName("杭州黑马0" + i);
                fillData.setAge(10 + i);
                fillDatas.add(fillData);
            }
            return fillDatas;
        }
    @Test
    public void test04(){
        List<FillData> fillDatas=initFillData();

        String template="src/ExcelPattern/fill_data_template2.xlsx";
        /*
         * pathName 是写出路径
         * */
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("Excel填充多组数据.xlsx", FillData.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
        sheet.doFill(fillDatas);

    }
// 组合填充
    @Test
    public void test05(){
        List<FillData> fillDatas=initFillData();

        String template="src/ExcelPattern/fill_data_template3.xlsx";
        /*
         * pathName 是写出路径
         * */
        ExcelWriter workBook = EasyExcel.write("Excel填充组合数据.xlsx", FillData.class).withTemplate(template).build();
        WriteSheet sheet = EasyExcel.writerSheet().build();
//        换行-防止覆盖
        FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();

        HashMap<String, String> otherData = new HashMap<>();
        otherData.put("date", "2020-03-14");
        otherData.put("total", "100");

        workBook.fill(fillDatas,fillConfig,sheet);
        workBook.fill(otherData,sheet);

        workBook.finish();


    }

    @Test
    public void test06(){
        List<FillData> fillDatas=initFillData();

        String template="src/ExcelPattern/fill_data_template4.xlsx";
        /*
         * pathName 是写出路径
         * */
        ExcelWriter workBook = EasyExcel.write("Excel填充水平数据.xlsx", FillData.class).withTemplate(template).build();
        WriteSheet sheet = EasyExcel.writerSheet().build();
//        水平填充
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        workBook.fill(fillDatas,fillConfig,sheet);
        workBook.finish();
    }

    @Test
    public void test07(){


        String template="src/ExcelPattern/report_template.xlsx";
        /*
         * pathName 是写出路径
         * */
        ExcelWriter workBook = EasyExcel.write("Excel报表数据填充.xlsx", FillData.class).withTemplate(template).build();
        WriteSheet sheet = EasyExcel.writerSheet().build();
        // ****** 准备数据 *******
        // 日期
        HashMap<String, String> dateMap = new HashMap<String, String>();
        dateMap.put("date", "2020-03-16");

        // 总会员数
        HashMap<String, String> totalCountMap = new HashMap<String, String>();
        dateMap.put("totalCount", "1000");

        // 新增员数
        HashMap<String, String> increaseCountMap = new HashMap<String, String>();
        dateMap.put("increaseCount", "100");

        // 本周新增会员数
        HashMap<String, String> increaseCountWeekMap = new HashMap<String, String>();
        dateMap.put("increaseCountWeek", "50");

        // 本月新增会员数
        HashMap<String, String> increaseCountMonthMap = new HashMap<String, String>();
        dateMap.put("increaseCountMonth", "100");
        // 新增会员数据
        List<Student> students = initData();
        // **** 准备数据结束****

        // 写入统计数据
        workBook.fill(dateMap, sheet);
        workBook.fill(totalCountMap, sheet);
        workBook.fill(increaseCountMap, sheet);
        workBook.fill(increaseCountWeekMap, sheet);
        workBook.fill(increaseCountMonthMap, sheet);
        // 写入新增会员

        workBook.fill(students,sheet);
        workBook.finish();
    }





}
