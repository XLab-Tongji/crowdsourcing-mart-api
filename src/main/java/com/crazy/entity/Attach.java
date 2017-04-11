package com.crazy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**附件功能
 * Created by SHIKUN on 2016/9/29.
 */
@Entity
@Table(name="Attach")
public class Attach {
    @Id
    @GeneratedValue
    private Long attach_id;

    private String attach_url;

    private String attach_name;

    private Integer size;

    private String attach_type;

    private Date create_time;

    private Date update_time;

   // private Map<String,String> meta_data;
    private String meta_data;
    private String username;

    private Boolean is_del;

    public Long getAttach_id() {
        return attach_id;
    }

    public void setAttach_id(Long attach_id) {
        this.attach_id = attach_id;
    }

    public String getAttach_url() {
        return attach_url;
    }

    public void setAttach_url(String attach_url) {
        this.attach_url = attach_url;
    }

    public String getAttach_name() {
        return attach_name;
    }

    public void setAttach_name(String attach_name) {
        this.attach_name = attach_name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getAttach_type() {
        return attach_type;
    }

    public void setAttach_type(String attach_type) {
        this.attach_type = attach_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getMeta_data() {
        return meta_data;
    }

    public void setMeta_data(String meta_data) {
        this.meta_data = meta_data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIs_del() {
        return is_del;
    }

    public void setIs_del(Boolean is_del) {
        this.is_del = is_del;
    }

    @Override
    public String toString() {
        return "Attach{" +
                "attach_id=" + attach_id +
                ", attach_url='" + attach_url + '\'' +
                ", attach_name='" + attach_name + '\'' +
                ", size=" + size +
                ", attach_type='" + attach_type + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", meta_data='" + meta_data + '\'' +
                ", username=" + username +
                ", is_del=" + is_del +
                '}';
    }
}
