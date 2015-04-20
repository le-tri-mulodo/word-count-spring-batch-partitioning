package com.mulodo.slave.writer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;

import com.mulodo.slave.pojo.Word;

public class WordCountItemWriter implements ItemWriter<Word> {
	
	DataSource dataSource;

	public void write(List<? extends Word> items) throws Exception {
		Connection con = dataSource.getConnection();
		con.setAutoCommit(false);
		for(Word item : items){
			CallableStatement callstmt = con.prepareCall("{call update_insert_word(?, ?)}");
			callstmt.setString(1, item.getWord());
			callstmt.setInt(2, item.getCount());
			callstmt.execute();
		}
		con.commit();
	}

}
