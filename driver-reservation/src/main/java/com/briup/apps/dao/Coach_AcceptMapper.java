package com.briup.apps.dao;

import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.Coach_AcceptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Coach_AcceptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    long countByExample(Coach_AcceptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int deleteByExample(Coach_AcceptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int insert(Coach_Accept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int insertSelective(Coach_Accept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    List<Coach_Accept> selectByExample(Coach_AcceptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    Coach_Accept selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int updateByExampleSelective(@Param("record") Coach_Accept record, @Param("example") Coach_AcceptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int updateByExample(@Param("record") Coach_Accept record, @Param("example") Coach_AcceptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int updateByPrimaryKeySelective(Coach_Accept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coach_accept
     *
     * @mbg.generated Sat Mar 28 22:46:04 CST 2020
     */
    int updateByPrimaryKey(Coach_Accept record);
}