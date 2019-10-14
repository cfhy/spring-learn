package com.yyb.service;

import com.yyb.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 千万级别大数据测试
 */
@Service
public class BigDataService {
    @Autowired
    StudentMapper studentMapper;
    /**
     * 插入千万条数据
     */
    public void insertTenMillionData() {
        for (int i = 1; i <= 100; i++) {
            Thread t=new Thread(new InsertOper(i,studentMapper));
            t.start();
        }
    }
}
class InsertOper implements Runnable {
    StudentMapper studentMapper;
    private int num;
    InsertOper(int num,StudentMapper studentMapper){
       this.num=num;
       this.studentMapper=studentMapper;
    }
    public void run() {
        //创建百万个班级，每个班级10个学生
        //num=1 1-10000 1-100000
        //num=2  10001-20000 100001-200000
        //num=3  20001-30000 200001-300000
        StringBuilder stringBuilder=new StringBuilder();
      for (int i=10000*(num-1)+1;i<=10000*num;i++){
          stringBuilder.append(String.format("insert into tclass value(%d,%s);",i,"'大一"+i+"班'"));
          for (int j = 1; j <= 10; j++) {
              stringBuilder.append(String.format("insert into student value(%d,%s,%d);",(i-1)*10+j,"'大一"+i+"班第"+j+"个同学'",i));
          }
          String sql = stringBuilder.toString();
          studentMapper.insertSql(sql);
          stringBuilder=new StringBuilder();
      }
    }
}
