package core.daylife.jsp;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class SimpleJspAddGenerator extends AbstractJspGenerator {

	@Override
	public List<String> getCompilationUnits() {
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getServiceImplementationType());
		String className = introspectedTable.getFullyQualifiedTable().getDomainObjectName().toLowerCase();
		super.fileName = "edit" + className;
		super.dirName = className;
		List<String> list = new ArrayList<>();
		list.add("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
		list.add("<%@include file=\"/common/taglibs.jsp\"%>");
		list.add("<%@include file=\"/common/common.jsp\"%>");
		list.add("<%");
		list.add("String path = request.getContextPath();");
		list.add(
				"String basePath = request.getScheme() + \"://\" + request.getServerName() + \":\" + request.getServerPort()");
		list.add("+ path + \"/\";");
		list.add("%>");

		list.add("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		list.add("<html>");
		list.add("<head>");
		list.add("<base href=\"<%=basePath%>\">");

		list.add("<title>修改</title>");

		list.add("<meta http-equiv=\"pragma\" content=\"no-cache\">");
		list.add("<meta http-equiv=\"cache-control\" content=\"no-cache\">");
		list.add("<meta http-equiv=\"expires\" content=\"0\">");
		list.add("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">");
		list.add("<meta http-equiv=\"description\" content=\"This is my page\">");

		list.add("</head>");

		list.add("<body class=\"frameContent\">");
		list.add("<form action=\"${ctx }/" + className.toLowerCase() + "/save\" id=\"mgFrom\" name=\"mgFrom\"");
		list.add(" method=\"post\">");
		list.add("<input type=\"hidden\" id=\"id\" name=\"id\" value=\"${item.id }\" />");
		list.add("<table class=\"table table-hover table-bordered table-no-bordered\"");
		list.add("style=\"margin-top: 20px;\">");
		list.add("<tr>");
		list.add("<th style=\"text-align: right; width: 120px;\">名称：</th>");
		list.add("<td colspan=\"3\"><input type=\"text\" id=\"templateName\"");
		list.add("						name=\"templateName\" style=\"width: 200px;\" class=\"form-control\"");
		list.add("						maxlength=\"10\" value=\"${item.templateName }\" /></td>");
		list.add("</tr>");
		list.add("<tr>");

		list.add("</table>");
		list.add("</form>");
		list.add("<center>");
		list.add("<button type=\"button\" class=\"btn btn-primary\" onclick=\"save();\">保存</button>");
		list.add("<button type=\"button\" class=\"btn btn-default\" onclick=\"cpp_close();\">关闭</button>");
		list.add("</center>");
		list.add("<br />");
		list.add("</body>");
		list.add("<script type=\"text/javascript\">");
		list.add("		//操作完成后的信息提示");
		list.add("$(document).ready(function() {");
		list.add("if ('${msg}' != '') {");
		list.add("cpp_alert_pop_reload('${msg}');");
		list.add("}");
		list.add("});");
		list.add("		function save() {");
		list.add("$(\"#mgFrom\").submit();");
		list.add("}");
		list.add("</script>");
		list.add("</html>");

		return list;
	}

}
