package com.lkl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收藏表
 * @TableName favorite
 */
@TableName(value ="favorite")
@Data
public class Favorite implements Serializable {
    /**
     * 收藏ID（Favorite ID），唯一标识每个收藏
     */
    @TableId
    private Integer favoriteid;

    /**
     * 用户ID（User ID），关联到用户表中的用户ID字段
     */
    private Integer userid;

    /**
     * 新闻ID（News ID），关联到新闻表中的新闻ID字段
     */
    private Integer newsid;

    /**
     * 收藏时间（Favorite Time）
     */
    private Date favoritetime;

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
        Favorite other = (Favorite) that;
        return (this.getFavoriteid() == null ? other.getFavoriteid() == null : this.getFavoriteid().equals(other.getFavoriteid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getNewsid() == null ? other.getNewsid() == null : this.getNewsid().equals(other.getNewsid()))
            && (this.getFavoritetime() == null ? other.getFavoritetime() == null : this.getFavoritetime().equals(other.getFavoritetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFavoriteid() == null) ? 0 : getFavoriteid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getNewsid() == null) ? 0 : getNewsid().hashCode());
        result = prime * result + ((getFavoritetime() == null) ? 0 : getFavoritetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", favoriteid=").append(favoriteid);
        sb.append(", userid=").append(userid);
        sb.append(", newsid=").append(newsid);
        sb.append(", favoritetime=").append(favoritetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}