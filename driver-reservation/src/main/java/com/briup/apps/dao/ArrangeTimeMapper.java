package com.briup.apps.dao;

import com.briup.apps.bean.ArrangeTime;
import com.briup.apps.bean.ArrangeTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArrangeTimeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    long countByExample(ArrangeTimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int deleteByExample(ArrangeTimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int insert(ArrangeTime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int insertSelective(ArrangeTime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    List<ArrangeTime> selectByExample(ArrangeTimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    ArrangeTime selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int updateByExampleSelective(@Param("record") ArrangeTime record, @Param("example") ArrangeTimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int updateByExample(@Param("record") ArrangeTime record, @Param("example") ArrangeTimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int updateByPrimaryKeySelective(ArrangeTime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table arrangetime
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int updateByPrimaryKey(ArrangeTime record);
}