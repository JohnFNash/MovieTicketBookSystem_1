<%@tag import="java.sql.Timestamp"%>
<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ attribute name="startTime" required="true" type="java.sql.Timestamp" description="场次开始时间" %>
<%@ attribute name="duration" required="true" type="java.lang.Integer" description="影片时长" %>

<%
	Timestamp end = new Timestamp(startTime.getTime() + duration*60*1000);
	String endStr = end.toString().substring(11, 16);
%>
<%=endStr %>