package com.briup.apps.bean;

public class Privilege {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column privilege.id
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column privilege.path
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    private String path;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column privilege.description
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column privilege.type
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column privilege.role_id
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    private Integer roleId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column privilege.id
     *
     * @return the value of privilege.id
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column privilege.id
     *
     * @param id the value for privilege.id
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column privilege.path
     *
     * @return the value of privilege.path
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column privilege.path
     *
     * @param path the value for privilege.path
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column privilege.description
     *
     * @return the value of privilege.description
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column privilege.description
     *
     * @param description the value for privilege.description
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column privilege.type
     *
     * @return the value of privilege.type
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column privilege.type
     *
     * @param type the value for privilege.type
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column privilege.role_id
     *
     * @return the value of privilege.role_id
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column privilege.role_id
     *
     * @param roleId the value for privilege.role_id
     *
     * @mbg.generated Wed Apr 01 15:32:10 CST 2020
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}