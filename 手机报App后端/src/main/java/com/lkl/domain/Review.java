package com.lkl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 审核表
 * @TableName review
 */
@TableName(value ="review")
@Data
public class Review implements Serializable {
    /**
     * 审核ID
     */
    @TableId
    private Integer reviewid;

    /**
     * 新闻ID（review ID）
     */
    private Integer newsid;

    /**
     * 审核员ID（Reviewer ID）
     */
    private Integer reviewerid;

    /**
     * 审核时间（Review Time）
     */
    private Date reviewtime;

    /**
     * 审核状态（Review Status）
     */
    private String reviewstatus;

    /**
     * 审核意见（Review Comment）
     */
    private String reviewcomment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Review other = (Review) that;
        return (this.getReviewid() == null ? other.getReviewid() == null : this.getReviewid().equals(other.getReviewid()))
            && (this.getNewsid() == null ? other.getNewsid() == null : this.getNewsid().equals(other.getNewsid()))
            && (this.getReviewerid() == null ? other.getReviewerid() == null : this.getReviewerid().equals(other.getReviewerid()))
            && (this.getReviewtime() == null ? other.getReviewtime() == null : this.getReviewtime().equals(other.getReviewtime()))
            && (this.getReviewstatus() == null ? other.getReviewstatus() == null : this.getReviewstatus().equals(other.getReviewstatus()))
            && (this.getReviewcomment() == null ? other.getReviewcomment() == null : this.getReviewcomment().equals(other.getReviewcomment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReviewid() == null) ? 0 : getReviewid().hashCode());
        result = prime * result + ((getNewsid() == null) ? 0 : getNewsid().hashCode());
        result = prime * result + ((getReviewerid() == null) ? 0 : getReviewerid().hashCode());
        result = prime * result + ((getReviewtime() == null) ? 0 : getReviewtime().hashCode());
        result = prime * result + ((getReviewstatus() == null) ? 0 : getReviewstatus().hashCode());
        result = prime * result + ((getReviewcomment() == null) ? 0 : getReviewcomment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reviewid=").append(reviewid);
        sb.append(", newsid=").append(newsid);
        sb.append(", reviewerid=").append(reviewerid);
        sb.append(", reviewtime=").append(reviewtime);
        sb.append(", reviewstatus=").append(reviewstatus);
        sb.append(", reviewcomment=").append(reviewcomment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}