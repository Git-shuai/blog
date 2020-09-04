package com.tian.blog.bean;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ID
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.USERNAME
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.PASSWORD
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.GMT_CREAT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private Long gmtCreat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.GMT_MODIFIED
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private Long gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.PHONE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private Integer phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.EMAIL
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.QQ
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private Integer qq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.WECHAT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private String wechat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.DELETE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private Integer delete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.VERSION
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private Integer version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.URL
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ROLE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private String role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.SALT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    private transient String salt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ID
     *
     * @return the value of USER.ID
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ID
     *
     * @param id the value for USER.ID
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.USERNAME
     *
     * @return the value of USER.USERNAME
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.USERNAME
     *
     * @param username the value for USER.USERNAME
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.PASSWORD
     *
     * @return the value of USER.PASSWORD
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.PASSWORD
     *
     * @param password the value for USER.PASSWORD
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.GMT_CREAT
     *
     * @return the value of USER.GMT_CREAT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public Long getGmtCreat() {
        return gmtCreat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.GMT_CREAT
     *
     * @param gmtCreat the value for USER.GMT_CREAT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setGmtCreat(Long gmtCreat) {
        this.gmtCreat = gmtCreat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.GMT_MODIFIED
     *
     * @return the value of USER.GMT_MODIFIED
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.GMT_MODIFIED
     *
     * @param gmtModified the value for USER.GMT_MODIFIED
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.PHONE
     *
     * @return the value of USER.PHONE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.PHONE
     *
     * @param phone the value for USER.PHONE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.EMAIL
     *
     * @return the value of USER.EMAIL
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.EMAIL
     *
     * @param email the value for USER.EMAIL
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.QQ
     *
     * @return the value of USER.QQ
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public Integer getQq() {
        return qq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.QQ
     *
     * @param qq the value for USER.QQ
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setQq(Integer qq) {
        this.qq = qq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.WECHAT
     *
     * @return the value of USER.WECHAT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.WECHAT
     *
     * @param wechat the value for USER.WECHAT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.DELETE
     *
     * @return the value of USER.DELETE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public Integer getDelete() {
        return delete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.DELETE
     *
     * @param delete the value for USER.DELETE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.VERSION
     *
     * @return the value of USER.VERSION
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.VERSION
     *
     * @param version the value for USER.VERSION
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.URL
     *
     * @return the value of USER.URL
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.URL
     *
     * @param url the value for USER.URL
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ROLE
     *
     * @return the value of USER.ROLE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ROLE
     *
     * @param role the value for USER.ROLE
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.SALT
     *
     * @return the value of USER.SALT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.SALT
     *
     * @param salt the value for USER.SALT
     *
     * @mbggenerated Fri Aug 21 15:40:34 CST 2020
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}