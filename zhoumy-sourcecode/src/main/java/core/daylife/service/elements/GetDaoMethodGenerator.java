package core.daylife.service.elements;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import core.util.StringUtil;

public class GetDaoMethodGenerator extends AbstractJavaServiceMethodGenerator {

	@Override
	public void addInterfaceElements(TopLevelClass interfaze) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("@Override");
		importedTypes.add(type);
		importedTypes.add(FullyQualifiedJavaType.getNewListInstance());

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("BaseDao");
		importedTypes.add(returnType);
		FullyQualifiedJavaType tableType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		tableType = new FullyQualifiedJavaType(tableType.getShortName());
		returnType.addTypeArgument(tableType);
		if (introspectedTable.getPrimaryKeyColumns().size() > 0) {
			returnType.addTypeArgument(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType());
		}

		method.setReturnType(returnType);
		method.setName("getDao");
		method.addAnnotation("@Override");
		FullyQualifiedJavaType typeMapper = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
		String fildName = StringUtil.firstCharacterUppercase(typeMapper.getShortName());
		method.addBodyLine("return " + fildName + ";");
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

}
