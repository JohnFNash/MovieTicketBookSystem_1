<%@ tag language="java" import="java.util.*, com.team3.mbts.entity.Director" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ attribute required="true" rtexprvalue="true" name="directorList" type="java.util.List" description="影片导演列表" %>

<%		
	StringBuffer result = new StringBuffer();
	List<Director> directorsList = (List<Director>)directorList;
	for(Director director: directorsList) {
		result.append(director.getDirectorName() + " ");
	}
			
	//设置标签的主体内容
	if(result.length() > 0) {
		out.println(result.toString().substring(0, result.length()-1));
	}
%>