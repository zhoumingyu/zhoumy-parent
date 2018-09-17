/**
 *    Copyright 2006-2016 the original author or authors.
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
package org.mybatis.generator.api;

import java.util.List;

import org.mybatis.generator.api.dom.java.CompilationUnit;

/**
 * The Class GeneratedJavaFile.
 *
 * @author Jeff Butler
 */
public class GeneratedJspFile extends GeneratedFile {

	/** The compilation unit. */
	private List<String> compilationUnit;

	/** The file encoding. */
	private String fileEncoding;

	/** The java formatter. */
	private JavaFormatter javaFormatter;

	private String fileName;

	private String dirName;

	/**
	 * Default constructor.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param targetProject
	 *            the target project
	 * @param fileEncoding
	 *            the file encoding
	 * @param javaFormatter
	 *            the java formatter
	 */
	public GeneratedJspFile(String dirName, String fileName, List<String> compilationUnit, String targetProject,
			String fileEncoding, JavaFormatter javaFormatter) {
		super(targetProject);
		this.dirName = dirName;
		this.fileName = fileName;
		this.compilationUnit = compilationUnit;
		this.fileEncoding = fileEncoding;
		this.javaFormatter = javaFormatter;
	}

	/**
	 * Instantiates a new generated java file.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param targetProject
	 *            the target project
	 * @param javaFormatter
	 *            the java formatter
	 */
	public GeneratedJspFile(String dirName, String fileName, List<String> compilationUnit, String targetProject,
			JavaFormatter javaFormatter) {
		this(dirName, fileName, compilationUnit, targetProject, null, javaFormatter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.generator.api.GeneratedFile#getFormattedContent()
	 */
	@Override
	public String getFormattedContent() {
		StringBuilder sb = new StringBuilder();
		for (String item : compilationUnit) {
			sb.append(item);
			sb.append("\n");
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.generator.api.GeneratedFile#getFileName()
	 */
	@Override
	public String getFileName() {
		return this.dirName + "/" + this.fileName + ".jsp"; //$NON-NLS-1$
	}

	public String getDirName() {
		return dirName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.generator.api.GeneratedFile#getTargetPackage()
	 */
	public String getTargetPackage() {
		return "";
	}

	/**
	 * This method is required by the Eclipse Java merger. If you are not
	 * running in Eclipse, or some other system that implements the Java merge
	 * function, you may return null from this method.
	 * 
	 * @return the CompilationUnit associated with this file, or null if the
	 *         file is not mergeable.
	 */
	public List<String> getCompilationUnit() {
		return compilationUnit;
	}

	/**
	 * A Java file is mergeable if the getCompilationUnit() method returns a
	 * valid compilation unit.
	 *
	 * @return true, if is mergeable
	 */
	@Override
	public boolean isMergeable() {
		return true;
	}

	/**
	 * Gets the file encoding.
	 *
	 * @return the file encoding
	 */
	public String getFileEncoding() {
		return fileEncoding;
	}
}
