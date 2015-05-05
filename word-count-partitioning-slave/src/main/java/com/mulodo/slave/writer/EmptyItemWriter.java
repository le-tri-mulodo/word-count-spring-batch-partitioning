/**
 * 
 */
package com.mulodo.slave.writer;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

import com.mulodo.slave.pojo.WordCount;

/**
 * @author TriLe
 *
 */
public class EmptyItemWriter implements ItemWriter<List<WordCount>> {

	private static final Log LOG = LogFactory.getLog(EmptyItemWriter.class);

	@Override
	public void write(List<? extends List<WordCount>> items) throws Exception {
		LOG.info("EmptyItemWriter run");
	}

}
