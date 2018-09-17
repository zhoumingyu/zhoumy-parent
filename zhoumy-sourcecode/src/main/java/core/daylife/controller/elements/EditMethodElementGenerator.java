package core.daylife.controller.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import core.util.StringUtil;

public class EditMethodElementGenerator extends AbstractJavaControllerMethodGenerator {

	@Override
	public void addInterfaceElements(TopLevelClass interfaze) {

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();

		FullyQualifiedJavaType paramType = new FullyQualifiedJavaType("javax.servlet.http.HttpServletRequest");
		importedTypes.add(paramType);
		FullyQualifiedJavaType paramType1 = new FullyQualifiedJavaType(
				"org.springframework.web.bind.annotation.RequestMethod");
		importedTypes.add(paramType1);

		FullyQualifiedJavaType paramType2 = new FullyQualifiedJavaType("com.cpp.core.common.utils.WebUtil");
		importedTypes.add(paramType2);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		FullyQualifiedJavaType param1 = new FullyQualifiedJavaType("HttpServletRequest");
		method.addParameter(new Parameter(param1, "request")); //$NON-NLS-1$
		FullyQualifiedJavaType typeMapper = new FullyQualifiedJavaType("ModelMap");
		method.addParameter(new Parameter(typeMapper, "map")); // $NON-NLS-1$

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("String");
		method.setReturnType(returnType);
		method.setName("edit");
		method.addAnnotation("@RequestMapping(value = \"/edit\", method = RequestMethod.GET)");
		FullyQualifiedJavaType exception = new FullyQualifiedJavaType("GenericBusinessException");
		method.addException(exception);
		String className = interfaze.getType().getShortName().replaceAll("Controller", "");
		String serviceName = StringUtil.firstCharacterUppercase(className);
		method.addBodyLine("Long id = WebUtil.getLong(request, \"id\");");
		method.addBodyLine(className + " " + serviceName + "= " + serviceName + "Service.getByID(id);");
		method.addBodyLine("map.put(\"item\", " + serviceName + ");");
		method.addBodyLine("return \"" + serviceName.toLowerCase() + "/edit" + serviceName.toLowerCase() + "\";");

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

}
