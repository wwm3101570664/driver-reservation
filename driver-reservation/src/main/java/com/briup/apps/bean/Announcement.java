package com.briup.apps.bean;

public class Announcement {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announcement.id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announcement.content
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announcement.time
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    private String time;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announcement.id
     *
     * @return the value of announcement.id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announcement.id
     *
     * @param id the value for announcement.id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announcement.content
     *
     * @return the value of announcement.content
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announcement.content
     *
     * @param content the value for announcement.content
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announcement.time
     *
     * @return the value of announcement.time
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public String getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announcement.time
     *
     * @param time the value for announcement.time
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}