package core.daylife.controller.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import core.util.StringUtil;

public class SaveMethodElementGenerator extends AbstractJavaControllerMethodGenerator {

	@Override
	public void addInterfaceElements(TopLevelClass interfaze) {

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();

		FullyQualifiedJavaType paramType = new FullyQualifiedJavaType("javax.servlet.http.HttpServletRequest");
		importedTypes.add(paramType);
		FullyQualifiedJavaType paramType1 = new FullyQualifiedJavaType("org.springframework.ui.ModelMap");
		importedTypes.add(paramType1);

		FullyQualifiedJavaType paramType2 = new FullyQualifiedJavaType("cpp.core.web.comm.constant.Constant");
		importedTypes.add(paramType2);
		FullyQualifiedJavaType paramType3 = new FullyQualifiedJavaType("java.util.HashMap");
		importedTypes.add(paramType3);
		FullyQualifiedJavaType paramType4 = new FullyQualifiedJavaType("java.util.Date");
		importedTypes.add(paramType4);
		String typeModel = interfaze.getType().getShortName().replaceAll("Controller", "");
		FullyQualifiedJavaType paramType5 = new FullyQualifiedJavaType("com.cpp.core.common.entity." + typeModel);
		importedTypes.add(paramType5);
		FullyQualifiedJavaType paramType6 = new FullyQualifiedJavaType(
				"com.cpp.core.common.exception.GenericBusinessException");
		importedTypes.add(paramType6);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("String");
		method.setReturnType(returnType);
		method.setName("save");
		method.addAnnotation("@RequestMapping(\"/save\")");
		FullyQualifiedJavaType exception = new FullyQualifiedJavaType("Exception");
		method.addException(exception);
		String classParam = StringUtil.firstCharacterUppercase(typeModel);
		FullyQualifiedJavaType typeMapper = new FullyQualifiedJavaType("ModelMap");
		method.addParameter(new Parameter(typeMapper, "map")); // $NON-NLS-1$
		method.addParameter(new Parameter(paramType5, classParam)); // $NON-NLS-1$

		method.addBodyLine("try {");
		method.addBodyLine("if (" + classParam + ".getId() == null) {");
		method.addBodyLine("	" + classParam + ".setCreateTime(new Date());");
		method.addBodyLine("		" + classParam + "Service.save(" + classParam + ");");
		method.addBodyLine("	} else {");
		method.addBodyLine("		" + classParam + "Service.update(" + classParam + ");");
		method.addBodyLine("	}");
		method.addBodyLine("	msg = Constant.SAVE_SUCCESS;");
		method.addBodyLine("}catch(");

		method.addBodyLine("Exception e)");
		method.addBodyLine("{");
		method.addBodyLine("msg = Constant.SAVE_FAILED;");
		method.addBodyLine("e.printStackTrace();");
		method.addBodyLine("throw new GenericBusinessException(e.getMessage(), e);");
		method.addBodyLine("}");

		method.addBodyLine("map.put(Constant.ATT_MSG,msg);");

		method.addBodyLine("return \"/" + classParam.toLowerCase() + "/edit" + classParam.toLowerCase() + "\";");

		FullyQualifiedJavaType param1 = new FullyQualifiedJavaType("HttpServletRequest");
		method.addParameter(new Parameter(param1, "request")); //$NON-NLS-1$
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

}
