package com.mulodo.slave.pojo;

/**
 * <p>
 * Domain class representing a submitted pdf file's metadata.
 * </p>
 */
public class FileInfo
{
    private long id;
    private String filePath;

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * @return the filePath
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * @param filePath
     *            the filePath to set
     */
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
}
