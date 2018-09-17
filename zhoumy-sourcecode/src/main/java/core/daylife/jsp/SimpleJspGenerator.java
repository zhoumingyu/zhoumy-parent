package core.daylife.jsp;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class SimpleJspGenerator extends AbstractJspGenerator {

	@Override
	public List<String> getCompilationUnits() {
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getServiceImplementationType());
		String className = introspectedTable.getFullyQualifiedTable().getDomainObjectName().toLowerCase();
		super.fileName = className + "list";
		super.dirName = className;
		List<String> list = new ArrayList<>();
		list.add("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
		list.add("<%@include file=\"/common/taglibs.jsp\"%>");
		list.add("<%@include file=\"/common/common.jsp\"%>");
		list.add("<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>");
		list.add("<!DOCTYPE html>");
		list.add("<html>");
		list.add("<head>");
		list.add("<title>列表</title>");
		list.add("</head>");
		list.add("<body class=\"scrollY frameContent\">");
		list.add("<div class=\"col-infos\">");
		list.add("<h2><i class=\"fa fa-bar-chart-o\"></i>查询条件</h2>");
		list.add("</div>");
		list.add("<div class=\"row search\"><!-- 多行查询 -->");
		list.add("<div class=\"col-md-12\">");
		list.add("<form id=\"qeuryFrom\" action=\"${ctx }/" + className
				+ "/list\" class=\"form-inline\" method=\"post\">");
		list.add("<div class=\"form-group\">");
		list.add(
				"	<label for=\"\" class=\"col-md-3 control-label\" style=\"text-align: right; width: 35%; margin-left: -15px;\">");
		list.add("类型名称 </label>");
		list.add("<div class=\"col-md-6\">");
		list.add("<input type=\"text\" id=\"templateTypeName\" name=\"templateTypeName\"");
		list.add("class=\"form-control\" value=\"${templateTypeName}\"");
		list.add("style=\"width: 150px;\">");
		list.add("</div>");
		list.add("</div>");
		list.add("<button type=\"submit\" class=\"btn btn-primary\">查询</button>&nbsp;&nbsp;");
		list.add("<button type=\"button\" class=\"btn btn-default\" onclick=\"cle()\">重置</button>");
		list.add("</form>");
		list.add("</div>");
		list.add("</div>");
		list.add("<!-- 列表 -->");
		list.add("<div class=\"col-infos\">");
		list.add("<h2>");
		list.add("<i class=\"fa fa-bar-chart-o\"></i>列表");
		list.add("</h2>");
		list.add("</div>");
		list.add("<form action=\"${ctx }/" + className + "/list\" id=\"mgFrom\" name=\"mgFrom\"");
		list.add("method=\"post\">");
		list.add("	<div class=\"table-responsive\">");
		list.add("<table class=\"table table-hover table-bordered\">");
		list.add("<tr class=\"active\">");
		list.add("<th style=\"width: 15%; text-align: center;\">ID</th>");
		list.add("<th style=\"width: 15%; text-align: center;\">名称</th>");
		list.add("<th style=\"width: 8%; text-align: center;\">状态</th>");
		list.add("<th style=\"width: 15%; text-align: center;\">时间</th>");
		list.add("<th style=\"width: 20%; text-align: center;\">操作</th>");
		list.add("</tr>");
		list.add("<c:forEach items=\"${pager.list }\" var=\"item\">");
		list.add("<tr align=\"center\">");
		list.add("<td>${item.id }</td>");
		list.add("<td></td>");
		list.add("<td></td>");
		list.add("<td><fmt:formatDate value=\"${item.createTime}\" pattern=\"yyyy-MM-dd HH:mm:ss\" /></td>");
		list.add("<td><input type=\"button\" onclick=\"edit('${item.id}')\" class=\"btn btn-primary btn-sm\" value=\"修改\" /></td>");
		list.add("</tr>");
		list.add("</c:forEach>");
		list.add("</table>");
		list.add("<div>");
		list.add(
				"<input type=\"button\" class=\"btn btn-primary btn-sm\" id=\"addBtn\" value=\" 添加  \" onclick=\"add();\" />");
		list.add("</div>");
		list.add("</div>");
		list.add("</form>");
		list.add("<!-- 分页 -->");
		list.add("<div class=\"pages\">");
		list.add("<%@ include file=\"/common/page.jsp\"%>");
		list.add("</div>");
		list.add("</body>");
		list.add("<form id=\"updatestateform\" action=\"${ctx }/" + className + "/updatestate\">");
		list.add("<input type=\"hidden\" id=\"editid\" name=\"id\">");
		list.add("</form>");
		list.add("<script type=\"text/javascript\">");
		list.add("$(document).ready(function() {");
		list.add("if ('${msg}' != '') {");
		list.add("cpp_alert('${msg}');");
		list.add("}");
		list.add("});");
		list.add("function add() {");
		list.add("var url = \"${ctx }/" + className + "/add\";");
		list.add("cpp_dialog(\"添加\", \"900px\", \"600px\", url);");
		list.add("}");
		list.add("function edit(id) {");
		list.add("var url = \"${ctx }/" + className + "/edit?id=\" + id;");
		list.add("cpp_dialog(\"修改\", \"600px\", \"300px\", url);");
		list.add("}");
		list.add("function cle() {");
		list.add("$(\"#templateTypeName\").val(\"\");");
		list.add("}");
		list.add("</script>");
		list.add("</html>");

		return list;
	}

}
