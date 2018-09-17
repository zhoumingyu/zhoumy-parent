package core.daylife.service;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import core.daylife.service.elements.AbstractJavaServiceMethodGenerator;
import core.daylife.service.elements.GetDaoMethodGenerator;
import core.daylife.service.elements.SelectListMethodGenerator;
import core.util.StringUtil;

/**
 * 简单的服务类生成
 * 
 * @author zhoumingyu
 * @since 2017年6月5日 上午10:55:30
 * @company 万顺叫车深圳云技术有限公司
 * @version 1.0
 */
public class SimpleServiceImplGenerator extends AbstractJavaGenerator {

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
				introspectedTable.getFullyQualifiedTable().toString()));
		CommentGenerator commentGenerator = context.getCommentGenerator();

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getServiceImplementationType());
		FullyQualifiedJavaType typeSuper = new FullyQualifiedJavaType("BaseServiceImpl");
		FullyQualifiedJavaType fqfjt1 = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		typeSuper.addTypeArgument(fqfjt1);
		if (introspectedTable.getPrimaryKeyColumns().size() > 0) {
			FullyQualifiedJavaType fqfjt2 = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType();
			typeSuper.addTypeArgument(fqfjt2);
		}
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.addImportedType(fqfjt1);
		FullyQualifiedJavaType fqjtService = new FullyQualifiedJavaType("org.springframework.stereotype.Service");
		topLevelClass.addImportedType(fqjtService);

		String rootDaoInterface = introspectedTable.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		if (!stringHasValue(rootDaoInterface)) {
			rootDaoInterface = context.getJavaClientGeneratorConfiguration()
					.getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		}

		if (stringHasValue(rootDaoInterface)) {
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootDaoInterface);
			topLevelClass.addImportedType(fqjt);
		}

		topLevelClass.addAnnotation("@Service(\""
				+ StringUtil.firstCharacterUppercase(topLevelClass.getType().getShortName().replaceAll("Impl", ""))
				+ "\")");
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(topLevelClass);

		FullyQualifiedJavaType superClass = typeSuper;
		if (superClass != null) {
			topLevelClass.addImportedType(superClass);
			superClass = new FullyQualifiedJavaType(superClass.getShortName());
			topLevelClass.setSuperClass(superClass);

		}

		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(introspectedTable.getServiceInterfaceType());
		fqjt.getTypeArguments().clear();
		topLevelClass.addSuperInterface(fqjt);
		topLevelClass.addImportedType(fqjt);

		String rootInterface = introspectedTable.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		if (!stringHasValue(rootInterface)) {
			rootInterface = context.getJavaClientGeneratorConfiguration()
					.getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		}

		String rootServiceInterfaceImpl = introspectedTable
				.getTableConfigurationProperty(PropertyRegistry.SERVICE_ROOT_INTERFACE_IMPL);
		if (!stringHasValue(rootServiceInterfaceImpl)) {
			rootInterface = context.getJavaClientGeneratorConfiguration()
					.getProperty(PropertyRegistry.SERVICE_ROOT_INTERFACE_IMPL);
		}

		if (stringHasValue(rootServiceInterfaceImpl)) {
			fqjt = new FullyQualifiedJavaType(rootServiceInterfaceImpl);
			topLevelClass.addImportedType(fqjt);
		}

		if (stringHasValue(rootInterface)) {
			FullyQualifiedJavaType fqjtrootInterface = new FullyQualifiedJavaType(rootInterface);
			FullyQualifiedJavaType tableTyperootInterface = new FullyQualifiedJavaType(
					introspectedTable.getBaseRecordType());
			FullyQualifiedJavaType ShortType = new FullyQualifiedJavaType(fqjt.getShortName());
			topLevelClass.addImportedType(fqjt);
		}

		FullyQualifiedJavaType typeAutowired = new FullyQualifiedJavaType(
				"org.springframework.beans.factory.annotation.Autowired");
		topLevelClass.addImportedType(typeAutowired);

		FullyQualifiedJavaType typeMapper = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
		topLevelClass.addImportedType(typeMapper);
		String fildName = StringUtil.firstCharacterUppercase(typeMapper.getShortName());
		Field fild = new Field(fildName, typeMapper);
		fild.setVisibility(JavaVisibility.PRIVATE);
		fild.addAnnotation("@Autowired");
		topLevelClass.addField(fild);

		addGetDaoMethod(topLevelClass);
		// addSelectMethod(topLevelClass);

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (context.getPlugins().modelBaseRecordClassGenerated(topLevelClass, introspectedTable)) {
			answer.add(topLevelClass);
		}

		return answer;
	}

	private void addSelectMethod(TopLevelClass interfaze) {
		AbstractJavaServiceMethodGenerator methodGenerator = new SelectListMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	private void addGetDaoMethod(TopLevelClass interfaze) {
		AbstractJavaServiceMethodGenerator methodGenerator = new GetDaoMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void initializeAndExecuteGenerator(AbstractJavaServiceMethodGenerator methodGenerator,
			TopLevelClass interfaze) {
		methodGenerator.setContext(context);
		methodGenerator.setIntrospectedTable(introspectedTable);
		methodGenerator.setProgressCallback(progressCallback);
		methodGenerator.setWarnings(warnings);
		methodGenerator.addInterfaceElements(interfaze);
	}
}
