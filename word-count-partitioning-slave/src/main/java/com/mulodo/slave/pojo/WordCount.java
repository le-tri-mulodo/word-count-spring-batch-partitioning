package com.mulodo.slave.pojo;

public class WordCount
{
    private String word;
    private int count;

    /**
     * @param word
     * @param count
     */
    public WordCount(String word, int count) {
        super();
        this.word = word;
        this.count = count;
    }

    /**
     * @return the word
     */
    public String getWord()
    {
        return word;
    }

    /**
     * @param word
     *            the word to set
     */
    public void setWord(String word)
    {
        this.word = word;
    }

    /**
     * @return the count
     */
    public int getCount()
    {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(int count)
    {
        this.count = count;
    }
}
