package core.daylife.controller;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import core.daylife.controller.elements.AddMethodElementGenerator;
import core.daylife.controller.elements.EditMethodElementGenerator;
import core.daylife.controller.elements.SaveMethodElementGenerator;
import core.daylife.controller.elements.SelectListElementGenerator;
import core.util.StringUtil;

public class SimpleControllerImplGenerator extends AbstractJavaGenerator {

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
				introspectedTable.getFullyQualifiedTable().toString()));
		CommentGenerator commentGenerator = context.getCommentGenerator();

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getControllerImplementationType());
		TopLevelClass topLevelClass = new TopLevelClass(type);

		FullyQualifiedJavaType type2 = new FullyQualifiedJavaType(
				"org.springframework.web.bind.annotation.RequestMapping");
		topLevelClass.addImportedType(type2);

		topLevelClass.addAnnotation("@Controller");
		topLevelClass.addAnnotation("@RequestMapping(\"/"
				+ topLevelClass.getType().getShortName().toLowerCase().replace("controller", "") + "\")");
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		FullyQualifiedJavaType superClass = new FullyQualifiedJavaType("cpp.core.web.comm.baseweb.BaseController");
		topLevelClass.setSuperClass(superClass);
		topLevelClass.addImportedType(superClass);
		commentGenerator.addJavaFileComment(topLevelClass);
		type = new FullyQualifiedJavaType("org.springframework.stereotype.Controller");
		topLevelClass.addImportedType(type);

		FullyQualifiedJavaType typeAutowired = new FullyQualifiedJavaType(
				"org.springframework.beans.factory.annotation.Autowired");
		topLevelClass.addImportedType(typeAutowired);

		FullyQualifiedJavaType typeService = new FullyQualifiedJavaType(introspectedTable.getServiceInterfaceType());
		topLevelClass.addImportedType(typeService);
		String fildName = StringUtil.firstCharacterUppercase(typeService.getShortName());
		Field fild = new Field(fildName, typeService);
		fild.setVisibility(JavaVisibility.PRIVATE);
		fild.addAnnotation("@Autowired");
		topLevelClass.addField(fild);
		addListMethod(topLevelClass);

		addMethod(topLevelClass);

		saveMethod(topLevelClass);

		editMethod(topLevelClass);
		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (context.getPlugins().modelBaseRecordClassGenerated(topLevelClass, introspectedTable)) {
			answer.add(topLevelClass);
		}

		return answer;
	}

	private void addListMethod(TopLevelClass topLevelClass) {
		SelectListElementGenerator selectlist = new SelectListElementGenerator();
		selectlist.addInterfaceElements(topLevelClass);
	}

	private void addMethod(TopLevelClass topLevelClass) {
		AddMethodElementGenerator selectlist = new AddMethodElementGenerator();
		selectlist.addInterfaceElements(topLevelClass);
	}

	private void saveMethod(TopLevelClass topLevelClass) {
		SaveMethodElementGenerator selectlist = new SaveMethodElementGenerator();
		selectlist.addInterfaceElements(topLevelClass);
	}

	private void editMethod(TopLevelClass topLevelClass) {
		EditMethodElementGenerator selectlist = new EditMethodElementGenerator();
		selectlist.addInterfaceElements(topLevelClass);
	}
}
