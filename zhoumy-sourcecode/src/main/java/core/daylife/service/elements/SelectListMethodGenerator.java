package core.daylife.service.elements;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import core.util.StringUtil;

public class SelectListMethodGenerator extends AbstractJavaServiceMethodGenerator {

	@Override
	public void addInterfaceElements(TopLevelClass interfaze) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("com.car.common.mybatis.Page");
		importedTypes.add(type);
		importedTypes.add(FullyQualifiedJavaType.getNewListInstance());

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		FullyQualifiedJavaType listType;
		if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
			listType = new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType());
		} else {
			// the blob fields must be rolled up into the base class
			listType = new FullyQualifiedJavaType(introspectedTable.getBaseQueryRecordType());
		}

		importedTypes.add(listType);
		returnType.addTypeArgument(listType);
		method.setReturnType(returnType);
		method.setName("getList");
		method.addAnnotation("@Override");
		FullyQualifiedJavaType typeMapper = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
		String param = StringUtil.firstCharacterUppercase(listType.getShortName());
		method.addParameter(new Parameter(listType, param)); // $NON-NLS-1$
		String fildName = StringUtil.firstCharacterUppercase(typeMapper.getShortName());
		method.addBodyLine("return " + fildName + ".getList(" + param + ",page);");
		FullyQualifiedJavaType param1 = new FullyQualifiedJavaType("Page");
		param1.addTypeArgument(listType);
		method.addParameter(new Parameter(param1, "page")); //$NON-NLS-1$
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

}
