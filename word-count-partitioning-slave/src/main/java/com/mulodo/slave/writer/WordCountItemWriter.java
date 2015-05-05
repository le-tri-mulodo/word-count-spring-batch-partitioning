package com.mulodo.slave.writer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

import com.mulodo.slave.pojo.WordCount;

public class WordCountItemWriter implements ItemWriter<List<WordCount>> {
	private static final Log LOG = LogFactory.getLog(WordCountItemWriter.class);

	private DataSource dataSource;

	public void write(List<? extends List<WordCount>> items) throws Exception {
		long startTime = System.currentTimeMillis();
		int func_call = 0;
		LOG.info("Begin update DB");
		try (Connection con = dataSource.getConnection();) {
			// con.setAutoCommit(false);
			for (List<WordCount> item : items) {

				for (WordCount wc : item) {
					CallableStatement callstmt = con
							.prepareCall("{call update_insert_word(?, ?)}");
					callstmt.setString(1, wc.getWord());
					callstmt.setInt(2, wc.getCount());
					callstmt.execute();
					// con.commit();
				}
				func_call += item.size();
			}
		}
		LOG.info("Update DB success. Duration: "
				+ (System.currentTimeMillis() - startTime)
				+ ", function call count: " + func_call);

	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
