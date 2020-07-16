package com.aaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_test")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Test implements Serializable {
    @Column(name = "FIELD1")
    private String field1;

    @Column(name = "FIELD2")
    private Integer field2;

    @Column(name = "FIELD3")
    private String field3;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * @return FIELD1
     */
    public String getField1() {
        return field1;
    }

    /**
     * @param field1
     */
    public void setField1(String field1) {
        this.field1 = field1 == null ? null : field1.trim();
    }

    /**
     * @return FIELD2
     */
    public Integer getField2() {
        return field2;
    }

    /**
     * @param field2
     */
    public void setField2(Integer field2) {
        this.field2 = field2;
    }

    /**
     * @return FIELD3
     */
    public String getField3() {
        return field3;
    }

    /**
     * @param field3
     */
    public void setField3(String field3) {
        this.field3 = field3 == null ? null : field3.trim();
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}