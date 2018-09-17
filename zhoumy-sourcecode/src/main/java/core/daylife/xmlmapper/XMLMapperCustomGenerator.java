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
package core.daylife.xmlmapper;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BaseColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BlobColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.CountByExampleElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByExampleElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ExampleWhereClauseElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeySelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithoutBLOBsElementGenerator;

import core.daylife.javamapper.SelectListElementGenerator;
import core.daylife.xmlmapper.elements.FindNumberByCondition;
import core.daylife.xmlmapper.elements.FindPageBreakByCondition;
import core.daylife.xmlmapper.elements.WhereSql;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class XMLMapperCustomGenerator extends AbstractXmlGenerator {

	public XMLMapperCustomGenerator() {
		super();
	}

	protected XmlElement getSqlMapElement() {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		progressCallback.startTask(getString("Progress.12", table.toString())); //$NON-NLS-1$
		XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
		String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
		answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
				namespace));

		context.getCommentGenerator().addRootComment(answer);
		addResultMapWithoutBLOBsElement(answer);
		addBaseColumnListElement(answer);
		addSelectByPrimaryKeyElement(answer);
		addSelectListElement(answer);
		addDeleteByPrimaryKeyElement(answer);
		// addInsertElement(answer);
		addInsertSelectiveElement(answer);
		addUpdateByPrimaryKeySelectiveElement(answer);
		findNumberByCondition(answer);
		findPageBreakByCondition(answer);
		whereSql(answer);
		return answer;
	}

	protected void whereSql(XmlElement parentElement) {
		if (introspectedTable.getRules().generateBaseResultMap()) {
			AbstractXmlElementGenerator elementGenerator = new WhereSql();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void findNumberByCondition(XmlElement parentElement) {
		if (introspectedTable.getRules().generateBaseResultMap()) {
			AbstractXmlElementGenerator elementGenerator = new FindNumberByCondition();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void findPageBreakByCondition(XmlElement parentElement) {
		if (introspectedTable.getRules().generateBaseResultMap()) {
			AbstractXmlElementGenerator elementGenerator = new FindPageBreakByCondition();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addResultMapWithoutBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateBaseResultMap()) {
			AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator(false);
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addBaseColumnListElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateBaseColumnList()) {
			AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
			AbstractXmlElementGenerator elementGenerator = new SelectByPrimaryKeyElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addSelectListElement(XmlElement parentElement) {
		AbstractXmlElementGenerator elementGenerator = new SelectListElementGenerator();
		initializeAndExecuteGenerator(elementGenerator, parentElement);
	}

	protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
			AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(true);
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addInsertElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateInsert()) {
			AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator(true);
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addInsertSelectiveElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateInsertSelective()) {
			AbstractXmlElementGenerator elementGenerator = new InsertSelectiveElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addUpdateByPrimaryKeySelectiveElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
			AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void initializeAndExecuteGenerator(AbstractXmlElementGenerator elementGenerator,
			XmlElement parentElement) {
		elementGenerator.setContext(context);
		elementGenerator.setIntrospectedTable(introspectedTable);
		elementGenerator.setProgressCallback(progressCallback);
		elementGenerator.setWarnings(warnings);
		elementGenerator.addElements(parentElement);
	}

	@Override
	public Document getDocument() {
		Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
				XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
		document.setRootElement(getSqlMapElement());

		if (!context.getPlugins().sqlMapDocumentGenerated(document, introspectedTable)) {
			document = null;
		}

		return document;
	}
}
