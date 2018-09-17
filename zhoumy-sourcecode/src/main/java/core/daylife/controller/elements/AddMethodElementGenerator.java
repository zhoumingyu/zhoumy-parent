package core.daylife.controller.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import core.util.StringUtil;

public class AddMethodElementGenerator extends AbstractJavaControllerMethodGenerator {

	@Override
	public void addInterfaceElements(TopLevelClass interfaze) {

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();

		FullyQualifiedJavaType paramType = new FullyQualifiedJavaType("javax.servlet.http.HttpServletRequest");
		importedTypes.add(paramType);
		FullyQualifiedJavaType paramType1 = new FullyQualifiedJavaType(
				"org.springframework.web.bind.annotation.RequestMethod");
		importedTypes.add(paramType1);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("String");
		method.setReturnType(returnType);
		method.setName("add");
		method.addAnnotation("@RequestMapping(value = \"/add\", method = RequestMethod.GET)");
		FullyQualifiedJavaType exception = new FullyQualifiedJavaType(
				"com.cpp.core.common.exception.GenericBusinessException");
		method.addException(exception);
		String serviceName = StringUtil
				.firstCharacterUppercase(interfaze.getType().getShortName().replaceAll("Controller", ""));
		method.addBodyLine("return \"" + serviceName.toLowerCase() + "/edit" + serviceName.toLowerCase() + "\";");
		FullyQualifiedJavaType param1 = new FullyQualifiedJavaType("HttpServletRequest");
		method.addParameter(new Parameter(param1, "request")); //$NON-NLS-1$
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

}
