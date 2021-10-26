package org.javaboy.batch_insert;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 批量插入
 *
 * @author 李志锐
 * @date 2021/10/26
 */
public class BatchInsert {

    /*
    CREATE TABLE `kknormal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `k1` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `k2` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `k3` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `k4` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `k5` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `k6` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

    * */

    /**
     * 正常插入
     */
    @Test
    @SneakyThrows
    public void normalInsert() {
        HashMap hashMap = new HashMap();
        hashMap.put("driver-class-name", "com.mysql.cj.jdbc.Driver");
        hashMap.put("username", "root");
        hashMap.put("password", "123");
        hashMap.put("url", "jdbc:mysql://localhost:3306/batch_insert?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(hashMap);
        try (Connection connection = dataSource.getConnection();
        ) {
            //预热下数据库
            connection.createStatement().execute("select 1");
        }

        long millis = System.currentTimeMillis();
        try (Connection connection = dataSource.getConnection();
        ) {
            for (int i = 0; i < 50000; i++) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO `kknormal`(`k1`, `k2`, `k3`, `k4`, `k5`, `k6`) VALUES (?, ?, ?, ?, ?, ?);");
                String value = String.valueOf(i);
                statement.setString(1, value);
                statement.setString(2, value);
                statement.setString(3, value);
                statement.setString(4, value);
                statement.setString(5, value);
                statement.setString(6, value);
                //模拟提交
                statement.execute();
                statement.close();
            }
        }finally {
            System.out.println("单条耗时："+(System.currentTimeMillis()-millis));
        }
    }

    /**
     * 批处理sql insert
     */
    @Test
    @SneakyThrows
    public void batchSqlInsert() {
        HashMap hashMap = new HashMap();
        hashMap.put("driver-class-name", "com.mysql.cj.jdbc.Driver");
        hashMap.put("username", "root");
        hashMap.put("password", "123");
        hashMap.put("url", "jdbc:mysql://localhost:3306/batch_insert?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(hashMap);
        try (Connection connection = dataSource.getConnection();
        ) {
            //预热下数据库
            connection.createStatement().execute("select 1");
        }

        long millis = System.currentTimeMillis();
        try (Connection connection = dataSource.getConnection();
        ) {
            int split = 500000;
            int mod = 500000 / split;
            for (int i = 0; i < mod; i++) {

                StringBuilder sqlBuilder = new StringBuilder("INSERT INTO `kknormal`(`k1`, `k2`, `k3`, `k4`, `k5`, `k6`) VALUES (?, ?, ?, ?, ?, ?)");
                for (int j = i*split; j < (i+1)*split; j++) {
                    if (j==0){
                        continue;
                    }
                    sqlBuilder.append(",(?, ?, ?, ?, ?, ?) ");
                }
                PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString());
                for (int j = i*split; j < (i+1)*split; j++) {
                    String value = String.valueOf(j);
                    int index = 6*j;
                    statement.setString(index+1, value);
                    statement.setString(index+2, value);
                    statement.setString(index+3, value);
                    statement.setString(index+4, value);
                    statement.setString(index+5, value);
                    statement.setString(index+6, value);
                }
                //模拟提交
                statement.execute();
                statement.close();
            }
        }finally {
            System.out.println("拼接SQL耗时："+(System.currentTimeMillis()-millis));
        }
    }

    /**
     * 批量插入
     */
    @Test
    @SneakyThrows
    public void batchInsert() {
        HashMap hashMap = new HashMap();
        hashMap.put("driver-class-name", "com.mysql.cj.jdbc.Driver");
        hashMap.put("username", "root");
        hashMap.put("password", "123");
        hashMap.put("url", "jdbc:mysql://localhost:3306/batch_insert?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&rewriteBatchedStatements=true");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(hashMap);
        try (Connection connection = dataSource.getConnection();
        ) {
            //预热下数据库
            connection.createStatement().execute("select 1");
        }

        long millis = System.currentTimeMillis();
        try (Connection connection = dataSource.getConnection();
        ) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO kknormal(`k1`, `k2`, `k3`, `k4`, `k5`, `k6`) VALUES (?, ?, ?, ?, ?, ?)");

            for (int i = 0; i < 50000; i++) {
                String value = String.valueOf(i);
                statement.setString(1, value);
                statement.setString(2, value);
                statement.setString(3, value);
                statement.setString(4, value);
                statement.setString(5, value);
                statement.setString(6, value);
                //模拟提交
                statement.addBatch();
            }
            //模拟批量提交
            statement.executeBatch();
            connection.commit();

            statement.clearBatch();
            statement.close();
        }finally {
            System.out.println("复用耗时："+(System.currentTimeMillis()-millis));
        }
    }
}
