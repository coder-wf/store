package com.itheima.web.servlet;

import com.itheima.bean.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.utils.UUIDUtils;
import com.itheima.utils.UploadUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddProductServlet extends HttpServlet {
	//把表单里面提交过来的数据封装成product对象,最终调用业务,保存到数据库里(图片封装的是图片的路径)
	//把图片保存到服务器里面(tomcat)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			//0 创建Product
			Product product = new Product();
			Map<String, String> map = new HashMap<String, String>();
			
			//1. 创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2. 创建文件上传核心对象
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			//3. 解析request 获得文件项List
			List<FileItem> list = fileUpload.parseRequest(request);
			//4. 遍历
			for (FileItem fileItem : list) {
				//获得key(就是表单里面name的属性值)
				String key = fileItem.getFieldName();
				if(fileItem.isFormField()){
					// 不是文件, 我们只需要把value封装到product对象里面
					// b 获得value
					String value = fileItem.getString("utf-8");
					
					map.put(key, value);
				}else{
					// 是文件, 需要把文件的路径封装到product对象里面, 保存文件的内容到服务器
					//*******需要把文件的路径封装到product对象里面******
					//eg: products/B/B/5C69850297954DB6879CB972E4C49116.jpg
					//a. 获得文件的名字  a.jpg
					String fileName = fileItem.getName();
					//b. 获得uuid的文件名
					String uuidName = UploadUtils.getUUIDName(fileName);
					//c. 获得两层目录 /B/B
					String dir = UploadUtils.getDir();
					map.put(key, "products"+dir+"/"+uuidName);
					
					//*******保存文件的内容到服务器******
					//a获得products的tomcat里面的绝对路径  E:\worksoft\tomcat\apache-tomcat-7.0.52-38\webapps\store_38\products
					String realPath = getServletContext().getRealPath("products");
					//b. 创建两层目录  E:\worksoft\tomcat\apache-tomcat-7.0.52-38\webapps\store_38\products\B\B
					File fileDir = new File(realPath, dir);
					if(!fileDir.exists()){
						fileDir.mkdirs();
					}
					//c.获得文件的内容(输入流)
					InputStream is = fileItem.getInputStream();
					//d. 根据两层目录和文件名字创建输出流E:\worksoft\tomcat\apache-tomcat-7.0.52-38\webapps\store_38\products\B\B/UUID文件.jpg
					OutputStream os =  new FileOutputStream(new File(fileDir, uuidName));
					//e. 流的拷贝
					IOUtils.copy(is, os);
					
					fileItem.delete();
					IOUtils.closeQuietly(os);
					IOUtils.closeQuietly(is);
				}
			}
			
			//5. 把map里面的数据 封装到product里面
			BeanUtils.populate(product, map);
			//5.1 封装pid, pdate, pflag
			product.setPid(UUIDUtils.getId());

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = sf.format(new Date());
            Date parse = sf.parse(format);

			product.setPdate(parse);
			product.setPflag(0);
			
			//6. 调用业务, 进行保存
			ProductService productService = new ProductServiceImpl();
			productService.save(product);
			
			//7.在查询所有展示
			response.sendRedirect(request.getContextPath()+"/adminProductServlet?method=findAll");
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
