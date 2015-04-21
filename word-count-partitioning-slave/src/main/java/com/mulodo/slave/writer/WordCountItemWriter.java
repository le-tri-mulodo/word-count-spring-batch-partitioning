package com.mulodo.slave.writer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;

import com.mulodo.slave.pojo.WordCount;

public class WordCountItemWriter implements ItemWriter<List<WordCount>>
{

    private DataSource dataSource;

    public void write(List<? extends List<WordCount>> items) throws Exception
    {
        Connection con = dataSource.getConnection();
        con.setAutoCommit(false);
        for (List<WordCount> item : items) {

            for (WordCount wc : item) {
                CallableStatement callstmt = con.prepareCall("{call update_insert_word(?, ?)}");
                callstmt.setString(1, wc.getWord());
                callstmt.setInt(2, wc.getCount());
                callstmt.execute();
            }
            con.commit();
        }
    }

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

}
