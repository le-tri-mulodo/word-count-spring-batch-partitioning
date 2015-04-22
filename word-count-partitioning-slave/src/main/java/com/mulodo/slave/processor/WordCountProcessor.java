package com.mulodo.slave.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import com.mulodo.slave.pojo.WordCount;

public class WordCountProcessor implements ItemProcessor<String, List<WordCount>>
{

    private static final Log LOG = LogFactory.getLog(WordCountProcessor.class);

    String delimiter = " !.,?:;\t={}[]#/()<>@'*\"+-";

    @Override
    public List<WordCount> process(String content) throws Exception
    {
        long startTime = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer(content, delimiter);
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        // process each word and write the map
        while (st.hasMoreTokens()) {
            String word = st.nextToken().trim().toLowerCase();
            // Check word if size > 128 and contain NULL charactor then not add
            // to map
            if (word.length() < 128 && !word.contains("\u0000")) {
                if (map.get(word) != null) {
                    // another occurrence of an existing word
                    int count = map.get(word);
                    count++;
                    map.put(word, count);
                } else {
                    // first occurrence of this word
                    map.put(word, 1);
                }
            } else {
                // Skip word with size > 128
                LOG.debug("Skip word:" + word);
            }
        }

        // Convert map to list
        List<WordCount> list = new ArrayList<WordCount>(map.size());
        WordCount wc = null;
        // Loop all items in map
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            wc = new WordCount(entry.getKey(), entry.getValue());
            // Add word into list
            list.add(wc);
        }

        LOG.info("Word count success. Number word: " + map.size() + ". Duration: "
                + (System.currentTimeMillis() - startTime));

        return list;
    }

    /**
     * @param delimiter
     *            the delimiter to set
     */
    public void setDelimiter(String delimiter)
    {
        this.delimiter = delimiter;
    }

}
