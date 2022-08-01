package com.jsp.controller;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jsp.context.ApplicationContext;

// 요청해서 처리하는 것이 아니라 doget dopost가 필요없다.
// 인스턴스가 먼저 만들고(Map에 넣어둠 그 다음 꺼내서)조립하는 것    
// 리플렉션형태로 바뀐다.
public class InitApplicationContextServlet extends HttpServlet {
// init을 사용하는 이유가 우리가 배운것이 서블릿 밖에 없어서..... 서블릿을 사용할려면 기본적으로 req(요청)이 필요한데 요청없이 
	// 
	@Override
	public void init(ServletConfig config) throws ServletException {
//   경로를 가져오기 -----------------------------------------------------------------------------
		String beanConfigXml = config.getInitParameter("contextConfigLocation");
		//System.out.println(beanConfigXml);++++++++++++++++++++++++++++++++++++++++++++++
		
		if (beanConfigXml == null) return;
		
		ServletContext ctx = config.getServletContext();
		// ServletContext 어플리케이션 전체에 대한 객체
		beanConfigXml = ctx.getRealPath("/")
				+ beanConfigXml.replace("classpath:", "WEB-INF/classes/")
				.replace("/", File.separator);
//		System.out.println(beanConfigXml);
// 경로 읽기 -----------------------------------------------------------------------------------
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(beanConfigXml);  // documentBuilder물리적 경로를 받아서 앞에도 적어줌. WEB-INF/classes/ 가 들어간 이유
			
			Element root = document.getDocumentElement();
			
//			System.out.println(root.getTagName());     beans 출력
			
			if(root == null || !root.getTagName().contentEquals("beans")) return;
			
			NodeList beans = root.getElementsByTagName("bean");
// ---------------???? ---------------------------------------------------------------------
			Map<String, Object> applicationContext = 
					ApplicationContext.getApplicationContext();
			for (int i =0; i< beans.getLength(); i++) {
				Node bean = beans.item(i);
				if(bean.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) bean;
					String id = ele.getAttribute("id");
					String classType = ele.getAttribute("class");
					//System.out.printf("id : %s, class=%s\n",id,classType);
					
					// map instance put
					Class<?> cls = Class.forName(classType);
					Object targetObj = cls.newInstance(); //single tone  하나만 넣고 더 안만듦 
					//어플리케이션에서 싱글톤을 인스턴스 하나만쓰면 싱글톤     
					applicationContext.put(id, targetObj);
					
				//	System.out.println("id : "+id+", class : "+targetObj);
					
				}
			}
			// 의존 주입----------------------------------------------------------------------
			for(int i = 0; i< beans.getLength(); i++) {
				Node bean = beans.item(i);
				if(bean.getNodeType() == Node.ELEMENT_NODE) {
					Element eleBean = (Element)bean;
					
					NodeList properties = bean.getChildNodes();
					for(int j = 0; j < properties.getLength(); j++) {
						Node property = properties.item(j);
						if(!property.getNodeName().contentEquals("property")) continue;
						
						if(property.getNodeType() == Node.ELEMENT_NODE) {
							Element ele = (Element) property;
							String name = ele.getAttribute("name");  // set 메소드에 대한 정보를 담고 있는 "name"
							String ref = ele.getAttribute("ref-value"); // 맵에다 저장한 key 값을 꺼내서 파라미터로 dao에 넣어주는 역할 = "ref-value"
							
//							System.out.printf("name = %s, ref-value=%s\n",name,ref);
							
							String setMethodName = "set" + name.substring(0,1).toUpperCase() 
									+ name.substring(1);
							String className = eleBean.getAttribute("class");
							Class<?> classType = Class.forName(className);
							
							Method[] methods = classType.getMethods();
							if(methods != null) for(Method method : methods) {
								// 의존성 여부 확인
								if (method.getName().contentEquals(setMethodName)) {
									
									method.invoke(applicationContext.get(eleBean.getAttribute("id")),
												applicationContext.get(ref));
									
									System.out.println("[invoke]"
											+applicationContext.get(eleBean.getAttribute("id"))
											+":"+applicationContext.get(ref));
								}
								
								
							}
						}
					}
				}
			}
			
			
			
			
			

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// 집합체 for문을 못 쓴다.  List<>, set<>, Map<> 으로 되어있지 않으면 사용할 수 없음
		
		
	}
}
