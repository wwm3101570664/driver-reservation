package com.briup.apps.bean;

public class Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.content
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_time
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    private String commentTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.user_id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.coach_id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    private Integer coachId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.id
     *
     * @return the value of comment.id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.id
     *
     * @param id the value for comment.id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.content
     *
     * @return the value of comment.content
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.content
     *
     * @param content the value for comment.content
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_time
     *
     * @return the value of comment.comment_time
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public String getCommentTime() {
        return commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_time
     *
     * @param commentTime the value for comment.comment_time
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime == null ? null : commentTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.user_id
     *
     * @return the value of comment.user_id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.user_id
     *
     * @param userId the value for comment.user_id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.coach_id
     *
     * @return the value of comment.coach_id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public Integer getCoachId() {
        return coachId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.coach_id
     *
     * @param coachId the value for comment.coach_id
     *
     * @mbg.generated Tue May 05 10:38:35 CST 2020
     */
    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }
}