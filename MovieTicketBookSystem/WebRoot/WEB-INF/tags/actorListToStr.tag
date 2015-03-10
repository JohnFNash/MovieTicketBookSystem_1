<%@ tag language="java" import="java.util.*, com.team3.mbts.entity.Actor" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ attribute required="true" rtexprvalue="true" name="actorList" type="java.util.List" description="影片主演列表" %>

<%		
	StringBuffer result = new StringBuffer();
	List<Actor> actorsList = (List<Actor>)actorList;
	for(Actor actor: actorsList) {
		result.append(actor.getActorName() + " ");
	}
			
	//设置标签的主体内容
	if(result.length() > 0) {
		out.println(result.toString().substring(0, result.length()-1));
	}
%>