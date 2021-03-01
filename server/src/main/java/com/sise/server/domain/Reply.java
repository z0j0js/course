package com.sise.server.domain;

public class Reply {
    private String id;

    private String from;

    private String fromheadimg;

    private String to;

    private String pid;

    private String comment;

    private String time;

    private Integer commentnum;

    private Integer like;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromheadimg() {
        return fromheadimg;
    }

    public void setFromheadimg(String fromheadimg) {
        this.fromheadimg = fromheadimg;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", from=").append(from);
        sb.append(", fromheadimg=").append(fromheadimg);
        sb.append(", to=").append(to);
        sb.append(", pid=").append(pid);
        sb.append(", comment=").append(comment);
        sb.append(", time=").append(time);
        sb.append(", commentnum=").append(commentnum);
        sb.append(", like=").append(like);
        sb.append("]");
        return sb.toString();
    }
}