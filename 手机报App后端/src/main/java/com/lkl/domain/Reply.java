package com.lkl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 回复表
 * @TableName reply
 */
@TableName(value ="reply")
@Data
public class Reply implements Serializable {
    /**
     * 回复ID（Reply ID），唯一标识每个回复
     */
    @TableId
    private Integer replyid;

    /**
     * 新闻ID（News ID），外键
     */
    private Integer newsid;

    /**
     * 回复人ID（User ID），外键
     */
    private Integer userid;

    /**
     * 被回复人ID（Replied User ID），外键
     */
    private Integer replieduserid;

    /**
     * 评论ID（Comment ID），外键
     */
    private Integer commentid;

    /**
     * 回复内容（Content）
     */
    private String content;

    /**
     * 回复时间（Reply Time）
     */
    private Date replytime;

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
        Reply other = (Reply) that;
        return (this.getReplyid() == null ? other.getReplyid() == null : this.getReplyid().equals(other.getReplyid()))
            && (this.getNewsid() == null ? other.getNewsid() == null : this.getNewsid().equals(other.getNewsid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getReplieduserid() == null ? other.getReplieduserid() == null : this.getReplieduserid().equals(other.getReplieduserid()))
            && (this.getCommentid() == null ? other.getCommentid() == null : this.getCommentid().equals(other.getCommentid()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReplytime() == null ? other.getReplytime() == null : this.getReplytime().equals(other.getReplytime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReplyid() == null) ? 0 : getReplyid().hashCode());
        result = prime * result + ((getNewsid() == null) ? 0 : getNewsid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getReplieduserid() == null) ? 0 : getReplieduserid().hashCode());
        result = prime * result + ((getCommentid() == null) ? 0 : getCommentid().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReplytime() == null) ? 0 : getReplytime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", replyid=").append(replyid);
        sb.append(", newsid=").append(newsid);
        sb.append(", userid=").append(userid);
        sb.append(", replieduserid=").append(replieduserid);
        sb.append(", commentid=").append(commentid);
        sb.append(", content=").append(content);
        sb.append(", replytime=").append(replytime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}