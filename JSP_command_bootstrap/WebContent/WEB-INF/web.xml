<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:web="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
  <servlet>
    <servlet-name>initServlet</servlet-name>
    <servlet-class>com.jsp.controller.InitApplicationContextServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:com/jsp/context/application-context.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  
  
	<servlet>
		<servlet-name>DispatcherServlet</servlet-name>
		<servlet-class>com.jsp.controller.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>url.properties</param-name>
			<param-value>com/jsp/properties/url</param-value>
		</init-param>
		<load-on-startup>2</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>DispatcherServlet</servlet-name>
		<url-pattern>*.do</url-pattern>
	</servlet-mapping>


</web-app>












