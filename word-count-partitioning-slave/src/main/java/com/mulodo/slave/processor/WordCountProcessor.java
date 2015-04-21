package com.mulodo.slave.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.batch.item.ItemProcessor;

import com.mulodo.slave.pojo.WordCount;

public class WordCountProcessor implements ItemProcessor<String, List<WordCount>>
{

    String delimiter = " !.,?:;\t={}[]#/()<>@'*\"+-";

    @Override
    public List<WordCount> process(String content) throws Exception
    {
        System.out.println("|" + delimiter + "|");

        StringTokenizer st = new StringTokenizer(content, delimiter);
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        // process each word and write the map
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            if (word.length() < 128) {

                word = word.toLowerCase();

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
                System.out.println("---|" + word + "|---");
            }
        }

        List<WordCount> list = new ArrayList<WordCount>(map.size());
        WordCount wc = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            wc = new WordCount(entry.getKey(), entry.getValue());

            list.add(wc);
        }

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
