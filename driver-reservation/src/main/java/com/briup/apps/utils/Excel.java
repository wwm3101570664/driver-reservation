package com.briup.apps.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.springframework.util.ResourceUtils;

import com.briup.apps.bean.extend.ArrangeTimeExtend;

public class Excel {
	//设置文件保存路径
	public static  String  url ="C:\\Users\\ACER\\Desktop\\data.xls";
	public static void createExcel(List<Map<String, Object>> dataList, String[] fieldList,String[] headLis) {
	    // 第一步，创建一个webbook，对应一个Excel文件
	    HSSFWorkbook wb = new HSSFWorkbook();
	    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
	    HSSFSheet sheet = wb.createSheet("sheet1");
	    sheet.setDefaultColumnWidth(20);// 默认列宽
	    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
	    HSSFRow row = sheet.createRow((int) 0);
	   

	    // 添加excel title
	    HSSFCell cell = null;
	    for (int i = 0; i < headLis.length; i++) {
	        cell = row.createCell((short) i);
	        cell.setCellValue(headLis[i]);
	    }

	    // 第五步，写入实体数据 实际应用中这些数据从数据库得到,list中字符串的顺序必须和数组strArray中的顺序一致
	    for (int n = 0; n < dataList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 1);
			Map<String, Object> dataMap = dataList.get(n);
			// ===============================================================
			for (int i = 0; i < fieldList.length; i++) {

				// 在索引0的位置创建单元格（左上端）
				cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue((dataMap.get(fieldList[i]))!=null?(dataMap.get(fieldList[i])).toString():"");
			}

	        // 第六步，将文件存到指定位置
	        try {
	            FileOutputStream fout = new FileOutputStream(url);
	            wb.write(fout);
	            fout.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	 /**
	 * 下载excel，常规的文件读写操作，只需要在服务器调用时传入文件名（fname）就可以了
	 * @param
	 * @throws UnsupportedEncodingException 
	 */
	// 注：

	// 获取项目下文件或者文件流

	// File file = new File(this.getClass().getResource("/xls/adminImportUserTemplate.xls").toURI());

	// in = new BufferedInputStream(this.getClass().getResourceAsStream("/xls/adminImportUserTemplate.xls"));

	    
	public static void downloadFile(HttpServletResponse response){
		String fileName = "data.xls";
		OutputStream os = null;
        InputStream is= null;
      
        //复制
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=utf-8");
            //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，文件以filename的值命名，
            // 保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
            //把文件名按UTF-8取出，并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"),"ISO8859-1"));
            //读取流
            File f = new File(url);
            
            	is = new FileInputStream(f);
				IOUtils.copy(is, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
    }
}
