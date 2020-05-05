/**
 * Project Name:poll3
 * File Name:ExcelUtils.java
 * Package Name:com.briup.apps.poll.util
 * Date:2018年7月3日上午8:57:12
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ClassName:ExcelUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年7月3日 上午8:57:12 <br/>
 * 
 * @author lichunyu
 * @version
 * @since JDK 1.6
 * @see
 */
@SuppressWarnings("deprecation")
public class ExcelUtils {

	/**
	 * 导出excel
	 * 
	 * @param response
	 *          	响应对象
	 * @param excelName
	 * 			  excel标题
	 * @param headList
	 *            excel的标题备注名称
	 * @param fieldList
	 *            excel的标题字段（与数据中map中键值对应）
	 * @param dataList
	 *            excel数据
	 * @throws Exception
	 */
	public static void createExcel(HttpServletResponse response, String excelName,String[] headList, String[] fieldList,
								   List<Map<String, Object>> dataList) throws Exception {
		try {
			// 创建新的Excel 工作簿
			XSSFWorkbook workbook = new XSSFWorkbook();
			// 告诉浏览器用什么软件可以打开此文件
			response.setHeader("Content-Type", "application/vnd.ms-excel");
			// 下载文件的默认名称
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(excelName, "GBK"));
			OutputStream os = response.getOutputStream();
			try{

				// 在Excel工作簿中建一工作表，其名为缺省值
				XSSFSheet sheet = workbook.createSheet();
				// 在索引0的位置创建行（最顶端的行）
				XSSFRow row = sheet.createRow(0);
				// 设置excel头（第一行）的头名称
				for (int i = 0; i < headList.length; i++) {

					// 在索引0的位置创建单元格（左上端）
					XSSFCell cell = row.createCell(i);
					// 定义单元格为字符串类型
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
					// 在单元格中输入一些内容
					cell.setCellValue(headList[i]);
				}
				// ===============================================================
				// 添加数据
				for (int n = 0; n < dataList.size(); n++) {
					// 在索引1的位置创建行（最顶端的行）
					XSSFRow row_value = sheet.createRow(n + 1);
					Map<String, Object> dataMap = dataList.get(n);
					// ===============================================================
					for (int i = 0; i < fieldList.length; i++) {

						// 在索引0的位置创建单元格（左上端）
						XSSFCell cell = row_value.createCell(i);
						// 定义单元格为字符串类型
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						// 在单元格中输入一些内容
						cell.setCellValue((dataMap.get(fieldList[i]))!=null?(dataMap.get(fieldList[i])).toString():"");
					}
					// ===============================================================
				}
				// 新建一输出文件流
//				FileOutputStream fos = new FileOutputStream(excelName);
				// 把相应的Excel 工作簿存盘
				workbook.write(os);

			}finally {
				os.flush();
				// 操作结束，关闭文件
				os.close();
				// 关闭workbook
				workbook.close();
			}
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
