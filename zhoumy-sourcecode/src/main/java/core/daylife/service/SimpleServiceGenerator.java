package core.daylife.service;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import core.daylife.javamapper.SelectListMethodGenerator;
import core.daylife.javamapper.SelectPagerListMethodGenerator;
import core.daylife.service.elements.AbstractJavaServiceMethodGenerator;

/**
 * 简单的服务类生成
 * 
 * @author zhoumingyu
 * @since 2017年6月5日 上午10:55:30
 * @company 万顺叫车深圳云技术有限公司
 * @version 1.0
 */
public class SimpleServiceGenerator extends AbstractJavaGenerator {

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
				introspectedTable.getFullyQualifiedTable().toString()));
		CommentGenerator commentGenerator = context.getCommentGenerator();

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getServiceInterfaceType());
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		String rootInterface = introspectedTable.getTableConfigurationProperty(PropertyRegistry.SERVICE_ROOT_INTERFACE);
		if (!stringHasValue(rootInterface)) {
			rootInterface = context.getJavaServiceGeneratorConfiguration()
					.getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		}

		if (stringHasValue(rootInterface)) {
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
			FullyQualifiedJavaType tableType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
			fqjt.addTypeArgument(tableType);
			if (introspectedTable.getPrimaryKeyColumns().size() > 0) {
				fqjt.addTypeArgument(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType());
			}

			FullyQualifiedJavaType ShortType = new FullyQualifiedJavaType(fqjt.getShortName());
			interfaze.addSuperInterface(ShortType);

			interfaze.addSuperInterface(ShortType);
			interfaze.addImportedType(fqjt);
		}

		//addSelectMethod(interfaze);

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (context.getPlugins().clientGenerated(interfaze, null, introspectedTable)) {
			answer.add(interfaze);
		}

		// List<CompilationUnit> extraCompilationUnits =
		// getExtraCompilationUnits();
		// if (extraCompilationUnits != null) {
		// answer.addAll(extraCompilationUnits);
		// }

		return answer;
	}

	private void addSelectMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new SelectPagerListMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void initializeAndExecuteGenerator(AbstractJavaMapperMethodGenerator methodGenerator,
			Interface interfaze) {
		methodGenerator.setContext(context);
		methodGenerator.setIntrospectedTable(introspectedTable);
		methodGenerator.setProgressCallback(progressCallback);
		methodGenerator.setWarnings(warnings);
		methodGenerator.addInterfaceElements(interfaze);
	}
}
