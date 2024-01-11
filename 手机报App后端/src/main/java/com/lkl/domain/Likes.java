package com.lkl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 点赞表
 * @TableName likes
 */
@TableName(value ="likes")
@Data
public class Likes implements Serializable {
    /**
     * 点赞ID（Like ID），唯一标识每个点赞
     */
    @TableId
    private Integer likeid;

    /**
     * 用户ID（User ID），外键
     */
    private Integer userid;

    /**
     * 新闻ID（News ID），外键
     */
    private Integer newsid;

    /**
     * 点赞时间（Like Time）
     */
    private Date liketime;

    /**
     * 点赞类型（Like Type），可以是新闻或评论
     */
    private String liketype;

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
        Likes other = (Likes) that;
        return (this.getLikeid() == null ? other.getLikeid() == null : this.getLikeid().equals(other.getLikeid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getNewsid() == null ? other.getNewsid() == null : this.getNewsid().equals(other.getNewsid()))
            && (this.getLiketime() == null ? other.getLiketime() == null : this.getLiketime().equals(other.getLiketime()))
            && (this.getLiketype() == null ? other.getLiketype() == null : this.getLiketype().equals(other.getLiketype()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLikeid() == null) ? 0 : getLikeid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getNewsid() == null) ? 0 : getNewsid().hashCode());
        result = prime * result + ((getLiketime() == null) ? 0 : getLiketime().hashCode());
        result = prime * result + ((getLiketype() == null) ? 0 : getLiketype().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", likeid=").append(likeid);
        sb.append(", userid=").append(userid);
        sb.append(", newsid=").append(newsid);
        sb.append(", liketime=").append(liketime);
        sb.append(", liketype=").append(liketype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}