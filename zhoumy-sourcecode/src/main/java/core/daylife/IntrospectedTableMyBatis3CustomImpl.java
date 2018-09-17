/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package core.daylife;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedJspFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.AnnotatedClientGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.MixedClientGenerator;
import org.mybatis.generator.codegen.mybatis3.model.BaseRecordGenerator;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.mybatis.generator.codegen.mybatis3.model.PrimaryKeyGenerator;
import org.mybatis.generator.codegen.mybatis3.model.RecordWithBLOBsGenerator;
import org.mybatis.generator.codegen.mybatis3.model.SimpleModelGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.ObjectFactory;

import core.daylife.controller.SimpleControllerImplGenerator;
import core.daylife.javamapper.JavaMapperCustomGenerator;
import core.daylife.jsp.AbstractJspGenerator;
import core.daylife.jsp.SimpleJspAddGenerator;
import core.daylife.jsp.SimpleJspGenerator;
import core.daylife.model.SimpleVoModelGenerator;
import core.daylife.service.SimpleServiceGenerator;
import core.daylife.service.SimpleServiceImplGenerator;

public class IntrospectedTableMyBatis3CustomImpl extends IntrospectedTable {

	/** The java model generators. */
	protected List<AbstractJavaGenerator> javaModelGenerators;

	/** The java model generators. */
	protected List<AbstractJavaGenerator> javaServiceGenerators;

	/** The java model generators. */
	protected List<AbstractJavaGenerator> javaControllerGenerators;

	/** The client generators. */
	protected List<AbstractJavaGenerator> clientGenerators;

	protected List<AbstractJspGenerator> jspGenerators;

	/** The xml mapper generator. */
	protected AbstractXmlGenerator xmlMapperGenerator;

	/**
	 * Instantiates a new introspected table my batis3 impl.
	 */
	public IntrospectedTableMyBatis3CustomImpl() {
		super(TargetRuntime.MYBATIS3);
		javaModelGenerators = new ArrayList<AbstractJavaGenerator>();
		clientGenerators = new ArrayList<AbstractJavaGenerator>();
		javaServiceGenerators = new ArrayList<AbstractJavaGenerator>();
		javaControllerGenerators = new ArrayList<AbstractJavaGenerator>();
		jspGenerators = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mybatis.generator.api.IntrospectedTable#calculateGenerators(java.util
	 * .List, org.mybatis.generator.api.ProgressCallback)
	 */
	@Override
	public void calculateGenerators(List<String> warnings, ProgressCallback progressCallback) {
		calculateJavaModelGenerators(warnings, progressCallback);

		// calculateJavaVoModelGenerators(warnings, progressCallback);

		calculateJavaServiceGenerators(warnings, progressCallback);

		calculateJavaControllerGenerators(warnings, progressCallback);

		calculateJspGenerators(warnings, progressCallback);

		AbstractJavaClientGenerator javaClientGenerator = calculateClientGenerators(warnings, progressCallback);

		calculateXmlMapperGenerator(javaClientGenerator, warnings, progressCallback);
	}

	/**
	 * Calculate xml mapper generator.
	 *
	 * @param javaClientGenerator
	 *            the java client generator
	 * @param warnings
	 *            the warnings
	 * @param progressCallback
	 *            the progress callback
	 */
	protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator, List<String> warnings,
			ProgressCallback progressCallback) {
		if (javaClientGenerator == null) {
			if (context.getSqlMapGeneratorConfiguration() != null) {
				xmlMapperGenerator = new XMLMapperGenerator();
			}
		} else {
			xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
		}

		initializeAbstractGenerator(xmlMapperGenerator, warnings, progressCallback);
	}

	/**
	 * Calculate client generators.
	 *
	 * @param warnings
	 *            the warnings
	 * @param progressCallback
	 *            the progress callback
	 * @return true if an XML generator is required
	 */
	protected AbstractJavaClientGenerator calculateClientGenerators(List<String> warnings,
			ProgressCallback progressCallback) {
		if (!rules.generateJavaClient()) {
			return null;
		}

		AbstractJavaClientGenerator javaGenerator = createJavaClientGenerator();
		if (javaGenerator == null) {
			return null;
		}

		initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
		clientGenerators.add(javaGenerator);

		return javaGenerator;
	}

	/**
	 * Creates the java client generator.
	 *
	 * @return the abstract java client generator
	 */
	protected AbstractJavaClientGenerator createJavaClientGenerator() {
		if (context.getJavaClientGeneratorConfiguration() == null) {
			return null;
		}

		String type = context.getJavaClientGeneratorConfiguration().getConfigurationType();

		AbstractJavaClientGenerator javaGenerator;
		if ("XMLMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
			javaGenerator = new JavaMapperGenerator();
		} else if ("MIXEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
			javaGenerator = new MixedClientGenerator();
		} else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
			javaGenerator = new AnnotatedClientGenerator();
		} else if ("MAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
			javaGenerator = new JavaMapperGenerator();
		} else if ("CUSTOMMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
			javaGenerator = new JavaMapperCustomGenerator();
		} else {
			javaGenerator = (AbstractJavaClientGenerator) ObjectFactory.createInternalObject(type);
		}

		return javaGenerator;
	}

	/**
	 * Calculate java model generators.
	 *
	 * @param warnings
	 *            the warnings
	 * @param progressCallback
	 *            the progress callback
	 */
	protected void calculateJavaModelGenerators(List<String> warnings, ProgressCallback progressCallback) {
		AbstractJavaGenerator javaGenerator = new SimpleModelGenerator();
		initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
		javaModelGenerators.add(javaGenerator);
	}

	protected void calculateJavaVoModelGenerators(List<String> warnings, ProgressCallback progressCallback) {
		AbstractJavaGenerator javaGenerator = new SimpleVoModelGenerator();
		initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
		javaModelGenerators.add(javaGenerator);
	}

	public void calculateJavaServiceGenerators(List<String> warnings, ProgressCallback progressCallback) {
		AbstractJavaGenerator javaGenerator = new SimpleServiceGenerator();
		initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
		javaServiceGenerators.add(javaGenerator);

		AbstractJavaGenerator javaImplGenerator = new SimpleServiceImplGenerator();
		initializeAbstractGenerator(javaImplGenerator, warnings, progressCallback);
		javaServiceGenerators.add(javaImplGenerator);
	}

	public void calculateJavaControllerGenerators(List<String> warnings, ProgressCallback progressCallback) {
		AbstractJavaGenerator javaImplGenerator = new SimpleControllerImplGenerator();
		initializeAbstractGenerator(javaImplGenerator, warnings, progressCallback);
		javaControllerGenerators.add(javaImplGenerator);
	}

	public void calculateJspGenerators(List<String> warnings, ProgressCallback progressCallback) {
		AbstractJspGenerator jspGenerator = new SimpleJspGenerator();
		initializeAbstractGenerator(jspGenerator, warnings, progressCallback);
		jspGenerators.add(jspGenerator);

		AbstractJspGenerator jspAddGenerator = new SimpleJspAddGenerator();
		initializeAbstractGenerator(jspAddGenerator, warnings, progressCallback);
		jspGenerators.add(jspAddGenerator);

	}

	/**
	 * Initialize abstract generator.
	 *
	 * @param abstractGenerator
	 *            the abstract generator
	 * @param warnings
	 *            the warnings
	 * @param progressCallback
	 *            the progress callback
	 */
	protected void initializeAbstractGenerator(AbstractGenerator abstractGenerator, List<String> warnings,
			ProgressCallback progressCallback) {
		if (abstractGenerator == null) {
			return;
		}

		abstractGenerator.setContext(context);
		abstractGenerator.setIntrospectedTable(this);
		abstractGenerator.setProgressCallback(progressCallback);
		abstractGenerator.setWarnings(warnings);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.generator.api.IntrospectedTable#getGeneratedJavaFiles()
	 */
	@Override
	public List<GeneratedJavaFile> getGeneratedJavaFiles() {
		List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

		for (AbstractJavaGenerator javaGenerator : javaModelGenerators) {
			List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
			for (CompilationUnit compilationUnit : compilationUnits) {
				GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
						context.getJavaModelGeneratorConfiguration().getTargetProject(),
						context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
				answer.add(gjf);
			}
		}

		for (AbstractJavaGenerator javaGenerator : javaServiceGenerators) {
			List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
			for (CompilationUnit compilationUnit : compilationUnits) {
				GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
						context.getJavaServiceGeneratorConfiguration().getTargetProject(),
						context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
				answer.add(gjf);
			}
		}

		for (AbstractJavaGenerator javaGenerator : javaControllerGenerators) {
			List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
			for (CompilationUnit compilationUnit : compilationUnits) {
				GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
						context.getJavaServiceGeneratorConfiguration().getTargetProject(),
						context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
				answer.add(gjf);
			}
		}

		for (AbstractJavaGenerator javaGenerator : clientGenerators) {
			List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
			for (CompilationUnit compilationUnit : compilationUnits) {
				GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
						context.getJavaClientGeneratorConfiguration().getTargetProject(),
						context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
				answer.add(gjf);
			}
		}

		return answer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.generator.api.IntrospectedTable#getGeneratedXmlFiles()
	 */
	@Override
	public List<GeneratedXmlFile> getGeneratedXmlFiles() {
		List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();

		if (xmlMapperGenerator != null) {
			Document document = xmlMapperGenerator.getDocument();
			GeneratedXmlFile gxf = new GeneratedXmlFile(document, getMyBatis3XmlMapperFileName(),
					getMyBatis3XmlMapperPackage(), context.getSqlMapGeneratorConfiguration().getTargetProject(), true,
					context.getXmlFormatter());
			if (context.getPlugins().sqlMapGenerated(gxf, this)) {
				answer.add(gxf);
			}
		}

		return answer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.generator.api.IntrospectedTable#getGenerationSteps()
	 */
	@Override
	public int getGenerationSteps() {
		return javaModelGenerators.size() + clientGenerators.size() + (xmlMapperGenerator == null ? 0 : 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.generator.api.IntrospectedTable#isJava5Targeted()
	 */
	@Override
	public boolean isJava5Targeted() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.generator.api.IntrospectedTable#requiresXMLGenerator()
	 */
	@Override
	public boolean requiresXMLGenerator() {
		AbstractJavaClientGenerator javaClientGenerator = createJavaClientGenerator();

		if (javaClientGenerator == null) {
			return false;
		} else {
			return javaClientGenerator.requiresXMLGenerator();
		}
	}

	@Override
	protected void calculateXmlAttributes() {
		super.calculateXmlAttributes();
		setUpdateByPrimaryKeySelectiveStatementId("update"); //$NON-NLS-1$
		setDeleteByPrimaryKeyStatementId("delete");
		setSelectByPrimaryKeyStatementId("getByID");
		setInsertSelectiveStatementId("save"); //$NON-NLS-1$
	}

	@Override
	public List<GeneratedJspFile> getGeneratedJspFiles() {
		List<GeneratedJspFile> answer = new ArrayList<GeneratedJspFile>();
		for (AbstractJspGenerator jspGenerator : jspGenerators) {
			List<String> compilationUnits = jspGenerator.getCompilationUnits();
			GeneratedJspFile gjf = new GeneratedJspFile(jspGenerator.dirName, jspGenerator.fileName, compilationUnits,
					context.getJavaJspGeneratorConfiguration().getTargetProject(),
					context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
			answer.add(gjf);
		}
		return answer;

	}

}
