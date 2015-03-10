<%@tag import="com.team3.mbts.entity.MovieType"%>
<%@ tag language="java" import="java.util.*" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ attribute required="true" rtexprvalue="true" name="movieTypeList" type="java.util.List" description="影片类型列表" %>

<%		
	StringBuffer result = new StringBuffer();
	List<MovieType> movieTypesList = (List<MovieType>)movieTypeList;
	for(MovieType type: movieTypesList) {
		result.append(type.getTypeName() + " ");
	}
			
	//设置标签的主体内容
	if(result.length() > 0) {
		out.println(result.toString().substring(0, result.length()-1));
	}
%>