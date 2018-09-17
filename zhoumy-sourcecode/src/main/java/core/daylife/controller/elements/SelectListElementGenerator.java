package core.daylife.controller.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import core.util.StringUtil;

public class SelectListElementGenerator extends AbstractJavaControllerMethodGenerator {

	@Override
	public void addInterfaceElements(TopLevelClass interfaze) {

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();

		FullyQualifiedJavaType paramType = new FullyQualifiedJavaType("javax.servlet.http.HttpServletRequest");
		importedTypes.add(paramType);
		FullyQualifiedJavaType paramType1 = new FullyQualifiedJavaType("org.springframework.ui.ModelMap");
		importedTypes.add(paramType1);

		FullyQualifiedJavaType paramType2 = new FullyQualifiedJavaType("com.cpp.core.common.utils.Pager");
		importedTypes.add(paramType2);
		FullyQualifiedJavaType paramType3 = new FullyQualifiedJavaType("java.util.HashMap");
		importedTypes.add(paramType3);
		FullyQualifiedJavaType paramType4 = new FullyQualifiedJavaType("java.util.Map");
		importedTypes.add(paramType4);
		String typeModel = interfaze.getType().getShortName().replaceAll("Controller", "");
		FullyQualifiedJavaType paramType5 = new FullyQualifiedJavaType("com.cpp.core.common.entity." + typeModel);
		importedTypes.add(paramType5);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("String");
		method.setReturnType(returnType);
		method.setName("list");
		method.addAnnotation("@RequestMapping(\"/list\")");
		FullyQualifiedJavaType exception = new FullyQualifiedJavaType("Exception");
		method.addException(exception);

		FullyQualifiedJavaType typeMapper = new FullyQualifiedJavaType("ModelMap");
		method.addParameter(new Parameter(typeMapper, "map")); // $NON-NLS-1$
		method.addBodyLine("String pageNoStr = request.getParameter(\"pageNo\");");

		method.addBodyLine("String pageSizeStr = request.getParameter(\"pageSize\");");
		method.addBodyLine("Integer pageNo = Integer.valueOf(pageNoStr != null ? pageNoStr : \"1\");");
		method.addBodyLine(
				"Integer pageSize = Integer.valueOf(pageSizeStr != null ? pageSizeStr : Pager.DEFAULT_COUNT_ON_EACH_PAGE + \"\");");
		method.addBodyLine("Map<String, Object> paramMap = new HashMap<String, Object>();");

		String serviceName = StringUtil
				.firstCharacterUppercase(interfaze.getType().getShortName().replaceAll("Controller", ""));
		method.addBodyLine("Pager<" + typeModel + "> page = " + serviceName + "Service"
				+ ".findPageByCondition(paramMap, pageNo,	pageSize);");

		method.addBodyLine(
				"page.setUrl(this.toPageUrl(request,\"/" + serviceName.toLowerCase() + "/list\", paramMap));");
		method.addBodyLine("map.put(\"pager\", page);");
		method.addBodyLine("return \"" + serviceName.toLowerCase() + "/" + serviceName.toLowerCase() + "list\";");
		FullyQualifiedJavaType param1 = new FullyQualifiedJavaType("HttpServletRequest");
		method.addParameter(new Parameter(param1, "request")); //$NON-NLS-1$
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

}
