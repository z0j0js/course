package com.sise.server.dto;

import java.util.List;

public class CommentDto {

    /**
     * 用户名
     */
    private String name;

    /**
     * 评论主键id
     */
    private String id;

    /**
     * 头像
     */
    private String headimg;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 时间
     */
    private String time;

    /**
     * 回复数
     */
    private Integer commentnum;

    /**
     * 点赞数
     */
    private Integer like;

    /**
     * 课程id，用于区分不同课程的评论
     */
    private String courseid;

    private List reply;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public List getReply() {
        return reply;
    }

    public void setReply(List reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", id=").append(id);
        sb.append(", headimg=").append(headimg);
        sb.append(", comment=").append(comment);
        sb.append(", time=").append(time);
        sb.append(", commentnum=").append(commentnum);
        sb.append(", like=").append(like);
        sb.append(", courseid=").append(courseid);
        sb.append(", reply=").append(reply);
        sb.append("]");
        return sb.toString();
    }

}