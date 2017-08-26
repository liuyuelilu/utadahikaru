package com.heroku.entity;

import java.util.Date;

public class UUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.u_user.id
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.u_user.nickname
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.u_user.email
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.u_user.pswd
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    private String pswd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.u_user.create_time
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.u_user.last_login_time
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.u_user.status
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    private Short status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.u_user.id
     *
     * @return the value of public.u_user.id
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.u_user.id
     *
     * @param id the value for public.u_user.id
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.u_user.nickname
     *
     * @return the value of public.u_user.nickname
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.u_user.nickname
     *
     * @param nickname the value for public.u_user.nickname
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.u_user.email
     *
     * @return the value of public.u_user.email
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.u_user.email
     *
     * @param email the value for public.u_user.email
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.u_user.pswd
     *
     * @return the value of public.u_user.pswd
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public String getPswd() {
        return pswd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.u_user.pswd
     *
     * @param pswd the value for public.u_user.pswd
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public void setPswd(String pswd) {
        this.pswd = pswd == null ? null : pswd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.u_user.create_time
     *
     * @return the value of public.u_user.create_time
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.u_user.create_time
     *
     * @param createTime the value for public.u_user.create_time
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.u_user.last_login_time
     *
     * @return the value of public.u_user.last_login_time
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.u_user.last_login_time
     *
     * @param lastLoginTime the value for public.u_user.last_login_time
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.u_user.status
     *
     * @return the value of public.u_user.status
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.u_user.status
     *
     * @param status the value for public.u_user.status
     *
     * @mbggenerated Sat Aug 26 12:41:35 CST 2017
     */
    public void setStatus(Short status) {
        this.status = status;
    }
}